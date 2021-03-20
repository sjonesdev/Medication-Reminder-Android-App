//commented everything out because it was causing errors in the build

package com.example.medication_reminder_android_app.NotificationRelay;
import androidx.lifecycle.MutableLiveData;
import com.example.medication_reminder_android_app.SQLiteDB.*;

/*
This is the base class
This class handles formatting and sending notifications to the user
This handles external notifications

notes:
    -just doing medications; don't include any implementation code for appointment notifications

 */


/* @authors: Karley Waguespack and Aliza Siddiqui
   Last Modified by: Aliza Siddiqui 03/06/2021
*/

public class NotificationsTemp {

    /*
    //member variables
    private char typeNotif;
    private String doctorName;
    private String medicationName;
    private String notificationName; //This is if the notification does not fit the med or doctor appointment category
                                    // i.e. MRI, Blood donation, etc.

    private NotificationManager mManager;
    public static final String ANDROID_CHANNEL_ID = "com.example.medication_reminder_android_app.NotificationRelay";
    public static final String ANDROID_CHANNEL_NAME = "MEDICATION CHANNEL";
    */


    private DatabaseRepository db;

    public NotificationsTemp(DatabaseRepository repo){
        db = repo;

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

    /*
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
    */

   /*
   @author: Aliza Siddiqui
   Last Modified: 03/06/2021
   Sets appropriate member variables based on the type of Reminder
   */

    /*
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

     */

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

    /*
    private NotificationCompt.Builder buildNotification(){
        //Gets the information by calling the methods
        String ChannelID = createNotificationChannel() //Creates the channel for the notification
        String[] infoArray = this.getData(); //gets and sets member variable data
        setData(infoArray);

        //formats notification for user
        switch(typeNotif){
            case 'M': //Medication Notification
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this, ChannelID)
                        .setContentTitle("=========MEDICATION REMINDER===========")
                        .setContentText("Please take " + this.medicationName + " now!");
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                 break;
            case 'A': //Doctor Appointment Notification
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this, ChannelID)
                        .setContentTitle("=========DOCTOR APPOINTMENT REMINDER===========")
                        .setContentText("Meet with Dr. " + this.doctorName + " now!");
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                break;
            case 'E': //Miscellaneous health appointments
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this, ChannelID)
                        .setContentTitle("=======" + this.notificationName + " REMINDER========")
                        .setContentText("You need to do " + this.notificationName + " right now!");
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        }
        return builder;
    }

     */
    
    /* 
    @author: Aliza Siddiqui
    Last Modified: 03/06/2021
    Creates the notification channel for medications to be displayed in */

    /*
    private String createNotificationChannel() {
        // create android channel
        NotificationChannel androidChannel = new NotificationChannel(ANDROID_CHANNEL_ID,
                ANDROID_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
    
        // Sets whether notification posted to this channel should vibrate.
        androidChannel.enableVibration(true);
        // Sets the notification light color for notifications posted to this channel
        androidChannel.setLightColor(Color.GREEN);
        // Sets whether notifications posted to this channel appear on the lockscreen or not
        androidChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
 
         // Register the channel with the system; you can't change the importance
         // or other notification behaviors after this
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(androidChannel);

        return androidChannel.getId(); ///Returns the ID of the specific channel created 

        }

     */

    /*
    @class Method

     */
    /*
    @author: Aliza Siddiqui
    Last Modified: 03/06/2021
    This method is for generating a unique ID for a specific notification based on the timestamp year/month/day 
      hours:minutes: seconds */

    /*
    private int createNotificationID(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");  //yyyy/MM/dd HH:mm:ss
        LocalDateTime now = LocalDateTime.now();  
        return Integer.parseInt(dtf.format(now));
    }

     */
     
    /*
    @author: Aliza Siddiqui
    Last Modified: 03/06/2021
    This method will send/show the notification in the status bar for the android device
      TODO: how to connect this to the UI? Is it necessary?
    */

    /*
    public void sendNotification(String notif){
        //TODO
        //send notification to external.... user
        //Shows notification in android device status bar
        int notificationId = createNotificationID();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(notificationId, this.buildNotification.build());
    }

     */


    //need to handle timing ourselves? This code may go in sendNotification
    //need to get current time somehow and check it against next reminder time


}


