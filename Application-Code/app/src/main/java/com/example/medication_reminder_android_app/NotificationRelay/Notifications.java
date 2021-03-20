package com.example.medication_reminder_android_app.NotificationRelay;

/*
This is the base class
This class handles formatting and sending notifications to the user
This handles external notifications
 */

import com.example.medication_reminder_android_app.SQLiteDB.*;

public class Notifications {

    private DatabaseRepository db;

    public Notifications(DatabaseRepository repo){
        db = repo;

    }

    /*
    Gets data about the next upcoming reminder
    returns a string array of information needed to build notification.

    Array contents:
                0th element: medication or doctor name (or type of appointment if no doctor)
                1st element: notification type

    Notification type key:
                Medication = "MED"
                Doctor Appointment = "APPT"
                Extraneous Appointment = "EAPPT"
     */
    protected String[] getData() {

        //string info array to be returned
        String[] infoArray = new String[2];

        //gets the reminder entity object from livedata type
        ReminderEntity reminderArray[] = db.getReminders(1).getValue();
        //we just got one reminder, grab it
        ReminderEntity reminder = reminderArray[0];

        //what type of reminder is it?
        String reminderType = reminder.getClassification();
        //store immediately in the info array.
        infoArray[0] = reminderType;

        if(reminderType.equals("MED")){
            //retrieve the medication object from the reminder
            MedicationEntity med = db.getMedById(reminder.getMedApptId());
            //put the med name in the array
            infoArray[1] = med.getMedName();
        } else{
            //otherwise, we have an appointment; will be completed when we get to viable
            //TODO create appointment entity from id
            //TODO check if it has a doctorId and assign appropriate notification type

        }


        return infoArray;
    }


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


    protected int[] getDateAsInt(){

        //need to get date from sqlite db (stored as string) and parse each num to integer;

        //takes the top reminder from the db
        ReminderEntity[] reminderArray = db.getReminders(1).getValue();
        ReminderEntity reminder = reminderArray[0];

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
