package com.example.medication_reminder_android_app.NotificationRelay;


//This handles out of app notifications

import com.example.medication_reminder_android_app.SQLiteDB.DatabaseRepository;

public class OutOfAppNotifications extends Notifications{

      public OutOfAppNotifications(DatabaseRepository repo){
          super(repo);
      }

}
