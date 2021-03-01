package com.example.medication_reminder_android_app.SQLiteDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

/*
Notification Data Access Object.
This is the class for the Notification component to access the SQLite DB
 */

public interface NotificationDAO {

    //in-app and out-of-app notifications need diff number of reminders
    @Query("SELECT * FROM ReminderEntity ORDER BY ApptDate, ApptTime LIMIT :numberOfReminders")
    public ReminderEntity[] selectNextReminders(int numberOfReminders);

}
