package com.example.medication_reminder_android_app.NotificationRelay;


//This handles out of app notifications

import com.example.medication_reminder_android_app.SQLiteDB.DatabaseRepository;

public class OutOfAppNotificationsTemp extends NotificationsTemp {

    public OutOfAppNotificationsTemp(DatabaseRepository repo){
        super.Notifications(repo);
    }


}
