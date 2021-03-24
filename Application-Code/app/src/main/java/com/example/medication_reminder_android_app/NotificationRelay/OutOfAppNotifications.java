package com.example.medication_reminder_android_app.NotificationRelay;


//This handles out of app notifications

import com.example.medication_reminder_android_app.SQLiteDB.*;
import com.example.medication_reminder_android_app.NotificationRelay.*;

import java.util.Calendar;

public class OutOfAppNotifications extends Notifications{

    //todo: figure out how to access database methods

    private DatabaseRepository db;

    public OutOfAppNotifications(DatabaseRepository repo){

          super(repo);
    }







    //will be used to create Calendar object for out of app notifications
    protected int[] getDateAsInt(Integer reminderID){

        //need to get date from sqlite db (stored as string) and parse each num to integer;

        //takes the top reminder from the db
        ReminderEntity reminder = db.getReminderById(reminderID);

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



}
