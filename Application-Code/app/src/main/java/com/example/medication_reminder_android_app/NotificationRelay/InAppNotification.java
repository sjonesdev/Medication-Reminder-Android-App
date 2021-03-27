package com.example.medication_reminder_android_app.NotificationRelay;

//THis class handles in app notifications

import com.example.medication_reminder_android_app.SQLiteDB.MainViewModel;

public class InAppNotification extends Notifications{ // extends Notifications{


    public InAppNotification(MainViewModel model){
        super(model);
    }


}
