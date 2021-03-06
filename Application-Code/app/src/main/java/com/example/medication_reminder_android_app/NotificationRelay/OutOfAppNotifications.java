package com.example.medication_reminder_android_app.NotificationRelay;

//This handles out of app notifications

public class OutOfAppNotifications extends Notifications{

    //this method should get data from the reminders table
    public void getData(){
        //type of reminder (doc, appointment, med), (int: 0 for medication, 1 for apt, 2 for extraneous)
        //content of reminder (medication name), (string)
        //need to handle timing ourselves

    }

}
