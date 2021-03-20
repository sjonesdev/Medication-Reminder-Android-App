package com.example.medication_reminder_android_app.NotificationRelay;


//This handles out of app notifications

import com.example.medication_reminder_android_app.SQLiteDB.*;
import com.example.medication_reminder_android_app.NotificationRelay.*;

import java.util.Calendar;

public class OutOfAppNotifications extends Notifications{


    private DatabaseRepository db;

    public OutOfAppNotifications(DatabaseRepository repo){

          super(repo);
    }



    //will be used to create Calendar object for out of app notifications
    protected int[] getTimeAsInt(){

        //need to get time from sqlite db (stored as string) and parse each one to integer;

        //takes the top reminder from the db
        ReminderEntity[] reminderArray = db.getReminders(1).getValue();
        ReminderEntity reminder = reminderArray[0];

        String timeString = reminder.getTime();

        //splice the time by colons
        String timeStrings[] = timeString.split(":");
        //parse each result in the array to an integer
        int hour = Integer.parseInt(timeStrings[0]);
        int minute = Integer.parseInt(timeStrings[1]);

        int timeAsIntegers[] = {hour, minute};

        return timeAsIntegers;

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



    Calendar createCalendarObject(int month, int day, int year, int minute, int hour){
        Calendar myCalendar = Calendar.getInstance();
        myCalendar.set(Calendar.MONTH, month);
        myCalendar.set(Calendar.DAY_OF_MONTH, day);
        myCalendar.set(Calendar.YEAR, year);
        myCalendar.set(Calendar.MINUTE, minute);
        myCalendar.set(Calendar.HOUR, hour);

        return myCalendar;
    }


}
