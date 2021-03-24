package com.example.medication_reminder_android_app;

/*
This class is the equivalent to the main class in Java.
This is the starting point of the code.

https://developer.android.com/guide/components/activities/activity-lifecycle#java
 */

import android.content.BroadcastReceiver;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.core.app.NotificationCompat;
import androidx.lifecycle.ViewModelProvider;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import com.example.medication_reminder_android_app.NotificationRelay.NotificationPublisher;
import com.example.medication_reminder_android_app.SQLiteDB.DatabaseRepository;
import com.example.medication_reminder_android_app.SQLiteDB.MainViewModel;
import com.example.medication_reminder_android_app.SQLiteDB.MedicationEntity;
import com.example.medication_reminder_android_app.SQLiteDB.ReminderEntity;
import com.example.medication_reminder_android_app.UserInputHandler.InputWrapper;

public class MainActivity extends AppCompatActivity{


    //member variables
    private char typeNotif;
    private String doctorName;
    private String medicationName;
    private String notificationName; //This is if the notification does not fit the med or doctor appointment category
                                      // i.e. MRI, Blood donation, etc.

    private final static String default_notification_channel_id = "default" ;
    public static final String NOTIFICATION_CHANNEL_ID = "10001" ;
    private DatabaseRepository db;
    NotificationPublisher publisher = new NotificationPublisher();
    Calendar myCalendar = Calendar.getInstance();
    MainViewModel model;
    InputWrapper input;

    public void setDatabase(DatabaseRepository repo){
        db = repo;

    }

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState) ;
        setContentView(R.layout.activity_main);
        Button btnIgnore = findViewById(R.id.btnIgnore);
        Button btnAcknowledge = findViewById(R.id.btnAcknowledge);
        model = new ViewModelProvider(this).get(MainViewModel.class);
        input = new InputWrapper(model);
        btnIgnore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notification myNotif = scheduleNotification(myCalendar); //Is there a better way to get a notif object then calling it?
                String medName = String.valueOf(NotificationCompat.getContentTitle(myNotif)).split(" ")[1];
                input.processAcknowledgementRequest(InputWrapper.InputType.Medication, db.getReminderByMedName(medName).getPrimaryKey(), true);
            }
        });
        btnAcknowledge.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });
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

        if(reminderType.equals("M")){
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


    private Notification startNotificationService(Notification notification, long delay) {
        //create a new intent to start the notification publisher
        Intent notificationIntent = new Intent(this, NotificationPublisher.class);

        //put some extra data in it: the notification's ID and the notification itself (built with notificationCompat)
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1 ) ;
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification) ;

        //create a new pendingIntent to pass onto alarm manager; alarm manager will be able to use the data to send the notif
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        //create a new alarm manager object
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        assert alarmManager != null;

        //send the notification using the specified delay; delay must be ms from now
        alarmManager.set(AlarmManager.RTC_WAKEUP, delay, pendingIntent) ;
        return notification;
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
        Intent ignoreIntent = new Intent(MainActivity.this, BroadcastReceiver.class); //Change the broadcast receiver to be specific
        ignoreIntent.setAction("Ignore");
        PendingIntent pintent = PendingIntent.getBroadcast(MainActivity.this, 0, ignoreIntent, 0);
        Intent acknowledgeIntent = new Intent(MainActivity.this, BroadcastReceiver.class); //Change the broadcast receiver to be specific
        ignoreIntent.setAction("Acknowledge");
        PendingIntent pintent2 = PendingIntent.getBroadcast(MainActivity.this, 0, acknowledgeIntent, 0);

        NotificationCompat.Builder builder = null;
        //formats notification for user
        switch(typeNotif){
            case 'M': //Medication Notification
                builder = new NotificationCompat.Builder(this, default_notification_channel_id)
                        .setContentTitle("========= " + this.medicationName + " MEDICATION REMINDER===========")
                        .setContentText("Please take " + this.medicationName + " now!")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setAutoCancel(true)
                        .addAction(R.drawable. ic_launcher_foreground , "Ignore" , pintent )
                        .addAction(R.drawable.ic_launcher_background, "Acknowledge", pintent2)
                        .setChannelId(NOTIFICATION_CHANNEL_ID);
                break;
            case 'A': //Doctor Appointment Notification
                builder = new NotificationCompat.Builder(this, default_notification_channel_id)
                        .setContentTitle("=========DOCTOR APPOINTMENT REMINDER===========")
                        .setContentText("Meet with Dr. " + this.doctorName + " now!")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setAutoCancel( true )
                        .setChannelId(NOTIFICATION_CHANNEL_ID);
                break;
            case 'E': //Miscellaneous health appointments
                builder = new NotificationCompat.Builder(this, default_notification_channel_id)
                        .setContentTitle("=======" + this.notificationName + " REMINDER========")
                        .setContentText("You need to do " + this.notificationName + " right now!")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setAutoCancel( true )
                        .setChannelId(NOTIFICATION_CHANNEL_ID);


        }
        return builder.build();
    }


    //TODO: create methods for parsing reminder times; create calendar object
    //Move all of the methods into out of app notifications


    //TODO: we need call this somewhere; need to figure out where
    private Notification scheduleNotification(Calendar myCalendar) {
            long chosenTime = myCalendar.getTimeInMillis();
            long currentTime = System.currentTimeMillis();
            long delay = chosenTime - currentTime;
            Notification myNotif = startNotificationService(buildNotification(), System.currentTimeMillis() + delay);
            return myNotif;
    }


}
