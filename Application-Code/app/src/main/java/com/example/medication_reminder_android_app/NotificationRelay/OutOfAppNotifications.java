package com.example.medication_reminder_android_app.NotificationRelay;


//This handles out of app notifications

import com.example.medication_reminder_android_app.R;
import com.example.medication_reminder_android_app.SQLiteDB.*;

import java.util.Calendar;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import java.util.Locale;

import com.example.medication_reminder_android_app.NotificationRelay.AcknowledgeReceiver;
import com.example.medication_reminder_android_app.NotificationRelay.IgnoreReceiver;
import com.example.medication_reminder_android_app.NotificationRelay.NotificationPublisher;
import com.example.medication_reminder_android_app.SQLiteDB.DatabaseRepository;
import com.example.medication_reminder_android_app.SQLiteDB.MainViewModel;
import com.example.medication_reminder_android_app.SQLiteDB.MedicationEntity;
import com.example.medication_reminder_android_app.SQLiteDB.ReminderEntity;

public class OutOfAppNotifications extends Notifications{

    private Context context;

    public OutOfAppNotifications(MainViewModel model, Context context){
        super(model);
        this. context = context;
    }

    //member variables pertaining to notification; these will get populated as we receive notifiction information
    private char typeNotif;
    private String doctorName;
    private String medicationName;
    private String notificationName; //This is if the notification does not fit the med or doctor appointment category
    // i.e. MRI, Blood donation, etc.


    //notificatiion channel information
    private final static String default_notification_channel_id = "default" ;
    public static final String NOTIFICATION_CHANNEL_ID = "10001" ;

    //instantiate a notification publisher
    NotificationPublisher publisher = new NotificationPublisher();
    //create a calendar object
    Calendar myCalendar = Calendar.getInstance();


     /*
    @author: Karley Waguespack
    Last Modified: 03/22/2021

    Description: Gets data about the reminder that was inputted
                 returns a string array of information needed to build notification.

    @params: reminderID: the id for the reminder

    Array contents:
                0th element: medication or doctor name (or type of appointment if no doctor)
                1st element: notification type
                2nd element: the associated reminder id

    Notification type key:
                Medication = "MED"
                Doctor Appointment = "APPT"
                Extraneous Appointment = "EAPPT"
     */

    //should take either a reminder or a reminder ID
    protected String[] getData(Integer reminderID) {

        //string info array to be returned
        String[] infoArray = new String[2];

        ReminderEntity reminder = model.getReminderById(reminderID);

        //what type of reminder is it?
        String reminderType = reminder.getClassification();
        //store immediately in the info array.
        infoArray[0] = reminderType;

        if(reminderType.equals("M")){
            //retrieve the medication object from the reminder
            MedicationEntity med = model.getMedById(reminder.getMedApptId());
            //get the med name
            infoArray[1] = med.getMedName();
        } else{
            //otherwise, we have an appointment;
            //TODO create appointment entity from id
            //TODO regular appointments get APPT, Extraneous ones get EAPPT; check if it has a doctorId

        }

        infoArray[2] = Integer.toString(reminderID);
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



    /*
    @author: Karley Waguespack
    Last Modified: 03/11/2021

    Description: sends an intent to the NotificationPublisher class to start up the notification service

    @params: Notification: the notificaation to be sent.
             delay: the time in miliseconds to send the notif

    return value: the notification to be sent
     */
    private Notification startNotificationService(Notification notification, long delay) {
        //create a new intent to start the notification publisher
        Intent notificationIntent = new Intent(context, NotificationPublisher.class);

        //put some extra data in it: the notification's ID and the notification itself (built with notificationCompat)
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1 ) ;
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification) ;

        //create a new pendingIntent to pass onto alarm manager; alarm manager will be able to use the data to send the notif
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        //create a new alarm manager object
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        assert alarmManager != null;

        //send the notification using the specified delay; delay must be ms from now
        alarmManager.set(AlarmManager.RTC_WAKEUP, delay, pendingIntent) ;
        return notification;
    }


    /*
    @author: Aliza Siddiqui
    Last Modified: 03/24/2021 by Karley
    MAIN PROCESSING METHOD:
      Will:
      - create the notification channel for medications (you can create multiple channels for each type of notif)
      - get data from Reminders Table and set appropriate data member variables in the class
      - build the notification and return it
      - TODO: Add action buttons (snooze/acknowledge (dismiss))
      - TODO: Action when user clicks on notification and not on an action button (will lead to the notification
               in the app with all the extra info about it i.e. dosage, ingredients, etc.)
    */
    private Notification buildNotification(Integer reminderID){

        //Gets the information by calling the methods
        String[] infoArray = this.getData(reminderID); //gets and sets member variable data
        Integer reminderId = Integer.parseInt(infoArray[2]);
        setData(infoArray);

        //create intents for the acknowledge and ignore button receivers; bundle the reminderId
        Intent acknowledgeIntent = new Intent(context, AcknowledgeReceiver.class);
        acknowledgeIntent.putExtra("reminderID", reminderId);
        PendingIntent acknowledge_pintent = PendingIntent.getBroadcast(context, 0, acknowledgeIntent, 0);

        Intent ignoreIntent = new Intent(context, IgnoreReceiver.class);
        ignoreIntent.putExtra("reminderID", reminderId);
        PendingIntent ignore_pintent = PendingIntent.getBroadcast(context, 0, ignoreIntent, 0);

        //build the calendar object for sending out the notification; stored globally
        myCalendar = createCalendarObject(reminderId);

        NotificationCompat.Builder builder = null;
        //formats notification for user
        switch(typeNotif){
            case 'M': //Medication Notification
                builder = new NotificationCompat.Builder(context, default_notification_channel_id)
                        .setContentTitle("========= " + this.medicationName + " MEDICATION REMINDER===========")
                        .setContentText("Please take " + this.medicationName + " now!")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setAutoCancel(true)
                        .addAction(R.drawable. ic_launcher_foreground , "Acknowledge" , acknowledge_pintent)
                        .addAction(R.drawable.ic_launcher_background, "Ignore", ignore_pintent)
                        .setChannelId(NOTIFICATION_CHANNEL_ID);
                break;
            case 'A': //Doctor Appointment Notification
                builder = new NotificationCompat.Builder(context, default_notification_channel_id)
                        .setContentTitle("=========DOCTOR APPOINTMENT REMINDER===========")
                        .setContentText("Meet with Dr. " + this.doctorName + " now!")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setAutoCancel( true )
                        .setChannelId(NOTIFICATION_CHANNEL_ID);
                break;
            case 'E': //Miscellaneous health appointments
                builder = new NotificationCompat.Builder(context, default_notification_channel_id)
                        .setContentTitle("=======" + this.notificationName + " REMINDER========")
                        .setContentText("You need to do " + this.notificationName + " right now!")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setAutoCancel( true )
                        .setChannelId(NOTIFICATION_CHANNEL_ID);


        }

        return builder.build();
    }



    /*
    @author: Karley Waguespack
    Last Modified: 03/24/2021

    Description: gets time from the database and converts to an array of integers

    @params: reminderID: the ID of the reminder

    return value: the array of integers
     */
    protected int[] getTimeAsInt(Integer reminderID){

        //takes the top reminder from the db
        ReminderEntity reminder = model.getReminderById(reminderID);

        String timeString = reminder.getTime();

        //splice the time by colons
        String timeStrings[] = timeString.split(":");
        //parse each result in the array to an integer
        int hour = Integer.parseInt(timeStrings[0]);
        int minute = Integer.parseInt(timeStrings[1]);

        int timeAsIntegers[] = {hour, minute};

        return timeAsIntegers;

    }



    /*
    @author: Karley Waguespack
    Last Modified: 03/11/2021

    Description: gets the date from the database and converts it to an array of integers

    @params: reminderID: the Id of the reminder

    return value: the array of integers
     */
    protected int[] getDateAsInt(MainViewModel model, Integer reminderID){

        //need to get date from sqlite db (stored as string) and parse each num to integer;

        //takes the top reminder from the db
        ReminderEntity reminder = model.getReminderById(reminderID);

        String dateString = reminder.getDate();

        //splice the date by dashes
        String dateStrings[] = dateString.split("-");
        //parse each result in the array to an integer
        int month = Integer.parseInt(dateStrings[0]);
        int day = Integer.parseInt(dateStrings[1]);
        int year = Integer.parseInt(dateStrings[2]);

        int dateAsIntegers[] = {month, day, year};

        return dateAsIntegers;
    }



    /*
    @author: Karley Waguespack
    Last Modified: 03/11/2021

    Description: creates a calendar object; sets all variables (MONTH, DAY, MINUTE, etc..) to the
    correct value according to time information in the db

    @params: reminder ID: the reminder associated with the notification

    return value: the calendar object
     */
    Calendar createCalendarObject(Integer reminderID){

        int date[] = getDateAsInt(model, reminderID);
        int time[] = getTimeAsInt(reminderID);

        Calendar myCalendar = Calendar.getInstance();
        myCalendar.set(Calendar.MONTH, date[0]);
        myCalendar.set(Calendar.DAY_OF_MONTH, date[1]);
        myCalendar.set(Calendar.YEAR, date[2]);
        myCalendar.set(Calendar.MINUTE, time[0]);
        myCalendar.set(Calendar.HOUR, time[1]);

        return myCalendar;
    }



    /*
    @author: Karley Waguespack
    Last Modified: 03/11/2021

    Description: schedules and sends the notification to the user's device

    @params: reminder ID: the reminder associated with the notification
             myCalendar: the calendar object containing all of the timing information

    return value: the notification
     */
    private Notification scheduleNotification(Calendar myCalendar, Integer reminderID) {
        long chosenTime = myCalendar.getTimeInMillis();
        long currentTime = System.currentTimeMillis();
        long delay = chosenTime - currentTime;
        Notification myNotif = startNotificationService(buildNotification(reminderID), System.currentTimeMillis() + delay);
        return myNotif;
    }



}
