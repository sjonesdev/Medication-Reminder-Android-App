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

    //out of app notifications needs to be able to get all reminder information
    @Query(
            "WITH OrderedTable AS (" +
                "SELECT * FROM ReminderEntity" +
                "ORDER BY date, time" +
            ")" +
            "SELECT TOP 1 * FROM OrderedTable"
    )
    public selectNextReminder();

    //in app notifications only needs top five notifications
    @Query(
            "WITH OrderedTable AS (" +
                    "SELECT * FROM ReminderEntity" +
                    "ORDER BY date, time" +
                    ")" +
                    "SELECT TOP 5 * FROM OrderedTable"
    )
    public selectNextFiveReminders();

}
