package com.example.medication_reminder_android_app.NotificationRelay;

//THis class handles in app notifications


import com.example.medication_reminder_android_app.SQLiteDB.DatabaseRepository;

public class InAppNotification extends Notifications{


    public InAppNotification(DatabaseRepository repo) {
        super(repo);
    }
}
