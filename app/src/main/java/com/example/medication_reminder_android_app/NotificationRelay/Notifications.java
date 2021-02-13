package com.example.medication_reminder_android_app.NotificationRelay;

/*
This class handles formatting and sending notifications to the user
This handles external notifications
 */

public class Notifications {
    //member variables

    public Notifications(){

    }

    public String getInformation() {
        //TODO
        //connects to Notification DAO in SQLiteDB to get relevant info
        return "";
    }

    private String buildNotification(){
        //TODO
        getInformation();
        //formats notification for user
        return "";
    }

    private void sendNotification(String notif){
        //TODO
        //send notification to external.... user
    }

}
