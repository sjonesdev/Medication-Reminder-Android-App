package com.example.medication_reminder_android_app;

/*
This class is the equivalent to the main class in Java.
This is the starting point of the code.

https://developer.android.com/guide/components/activities/activity-lifecycle#java
 */

import android.app.NotificationManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import android.widget.*;

import com.example.medication_reminder_android_app.NotificationRelay.NotificationPublisher;
import com.example.medication_reminder_android_app.SQLiteDB.DatabaseRepository;
import com.example.medication_reminder_android_app.SQLiteDB.MedicationEntity;
import com.example.medication_reminder_android_app.SQLiteDB.ReminderEntity;

public class MainActivity extends AppCompatActivity{
    //member variables
    private char typeNotif;
    private String doctorName;
    private String medicationName;
    private String notificationName; //This is if the notification does not fit the med or doctor appointment category
                                      // i.e. MRI, Blood donation, etc.

    public static final String ANDROID_CHANNEL_ID = "com.example.medication_reminder_android_app.NotificationRelay";
    public static final String ANDROID_CHANNEL_NAME = "MEDICATION CHANNEL";
    private final static String default_notification_channel_id = "default" ;
    final Calendar myCalendar = Calendar.getInstance () ;
    private DatabaseRepository db;
    NotificationPublisher publisher = new NotificationPublisher();

    public void setDatabase(DatabaseRepository repo){
        db = repo;

    }

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState) ;
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onStop(){
        super.onStop();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }


    /*
    @author: Karley Waguespack
    Last Modified: 03/06/2021
    Gets data about the next upcoming reminder
    returns a string array of information needed to build notification.

    Array contents:
                0th element: medication or doctor name (or type of appointment if no doctor)
                1st element: notification type

    Notification type key:
                Medication = "MED"
                Doctor Appointment = "APPT"
                Extraneous Appointment = "EAPPT"
     */
    protected String[] getData() {

        //string info array to be returned
        String[] infoArray = new String[2];

        //gets the reminder entity object from livedata type
        ReminderEntity[] reminderArray = db.getReminders(1).getValue();
        //we just got one reminder, grab it
        ReminderEntity reminder = reminderArray[0];

        //what type of reminder is it?
        String reminderType = reminder.getClassification();
        //store immediately in the info array.
        infoArray[0] = reminderType;

        if(reminderType.equals("MED")){
            //retrieve the medication object from the reminder
            MedicationEntity med = db.getMedById(reminder.getMedApptId());
            //get the med name
            infoArray[1] = med.getMedName();
        } else{
            //otherwise, we have an appointment;
            //TODO create appointment entity from id
            //TODO regular appointments get APPT, Extraneous ones get EAPPT; check if it has a doctorId

        }
        return infoArray;
    }

    /*
   @author: Aliza Siddiqui
   Last Modified: 03/06/2021
   Sets appropriate member variables based on the type of Reminder
   */
    public void setData(String[] infoArray){
        this.typeNotif = infoArray[1].charAt(0);
        switch (typeNotif){
            case 'M':
                this.medicationName = infoArray[0]; //If it is a medication notification, only the medication name
                //variable will be assigned a value and the others will be null
                break;
            case 'A':
                this.doctorName = infoArray[0];
                break;
            case 'E':
                this.notificationName = infoArray[0];
                break;
        }
    }


    private void scheduleNotification (Notification notification, long delay) {
        //create a new intent to start the notification publisher
        Intent notificationIntent = new Intent(this, NotificationPublisher.class);

        //put some extra data in it: the notification's ID and the notification itself (built with notificationCompat)
        notificationIntent.putExtra(publisher.createNotificationID() , 1 ) ;
        notificationIntent.putExtra(publisher.getNotificationName() , notification) ;

        //create a new pendingIntent to pass onto alarm manager; alarm manager will be able to use the data to send the notif
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0 , notificationIntent , PendingIntent. FLAG_UPDATE_CURRENT );

        //create a new alarm manager object
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        assert alarmManager != null;

        //send the notification using the specified delay; delay must be ms from now
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP , delay , pendingIntent) ;
    }


    /*
    @author: Aliza Siddiqui
    Last Modified: 03/06/2021
    MAIN PROCESSING METHOD:
      Will:
      - create the notification channel for medications (you can create multiple channels for each type of notif)
      - get data from Reminders Table and set appropriate data member variables in the class
      - build the notification and return it
      - TODO: Add action buttons (snooze/acknowledge (dismiss))
      - TODO: Action when user clicks on notification and not on an action button (will lead to the notification
               in the app with all the extra info about it i.e. dosage, ingredients, etc.)
    */
    private Notification buildNotification(){
        //Gets the information by calling the methods
        String[] infoArray = this.getData(); //gets and sets member variable data
        setData(infoArray);

        NotificationCompat.Builder builder = null;
        //formats notification for user
        switch(typeNotif){
            case 'M': //Medication Notification
                builder = new NotificationCompat.Builder(this, default_notification_channel_id)
                        .setContentTitle("=========MEDICATION REMINDER===========")
                        .setContentText("Please take " + this.medicationName + " now!")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setAutoCancel( true )
                        .setChannelId(ANDROID_CHANNEL_ID);
                break;
            case 'A': //Doctor Appointment Notification
                builder = new NotificationCompat.Builder(this, default_notification_channel_id)
                        .setContentTitle("=========DOCTOR APPOINTMENT REMINDER===========")
                        .setContentText("Meet with Dr. " + this.doctorName + " now!")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setAutoCancel( true )
                        .setChannelId(ANDROID_CHANNEL_ID);
                break;
            case 'E': //Miscellaneous health appointments
                builder = new NotificationCompat.Builder(this, default_notification_channel_id)
                        .setContentTitle("=======" + this.notificationName + " REMINDER========")
                        .setContentText("You need to do " + this.notificationName + " right now!")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setAutoCancel( true )
                        .setChannelId(ANDROID_CHANNEL_ID);

        }
        return builder.build();
    }

    //when the user has finished selecting a date, set the the calendar variables to that date
    DatePickerDialog.OnDateSetListener setDateVariables = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet (DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year) ;
            myCalendar.set(Calendar.MONTH, monthOfYear) ;
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth) ;
        }
    };

    TimePickerDialog.OnTimeSetListener setTimeVariables = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hour, int minute) {
            myCalendar.set(Calendar.HOUR, hour) ;
            myCalendar.set(Calendar.MINUTE, minute);
            myCalendar.set(Calendar.SECOND, 0);
            myCalendar.set(Calendar.MILLISECOND, 0);
            updateLabel();
        }
    };

    //create a datepicker dialog; use the setCalendarVariables listener; set the current date
    //to the current year, month, and day.
    public void setDate (View view) {
        new DatePickerDialog(MainActivity.this, setDateVariables,
                myCalendar.get(Calendar.YEAR ),
                myCalendar.get(Calendar.MONTH ),
                myCalendar.get(Calendar.DAY_OF_MONTH )
        ).show();
    }

    //do the same for the timepicker
    public void setTime(View view){

        new TimePickerDialog(MainActivity.this, setTimeVariables,
                myCalendar.get(Calendar.HOUR),
                myCalendar.get(Calendar.MINUTE),
                true

        ).show();

    }

    private void updateLabel() {
        String myFormat = "dd/MM/yy" ; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat , Locale. getDefault ());

        //this is the chosenTime from the user (TODO: need to get specified time from user)
        long chosenTime = myCalendar.getTimeInMillis();
        long currentTime = System.currentTimeMillis();
        long delay = chosenTime - currentTime;
        System.out.println(delay);
        //String date = sdf.format(chosenTime);
        scheduleNotification(buildNotification(), delay);
    }






}
