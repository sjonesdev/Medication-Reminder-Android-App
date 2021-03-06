package com.example.medication_reminder_android_app.NotificationRelay;

/*
This is the base class
This class handles formatting and sending notifications to the user
This handles external notifications
 */


/*notes:
    -just doing medications; don't include any implementation code for appointment notifications

 */

import androidx.lifecycle.MutableLiveData;
import com.example.medication_reminder_android_app.SQLiteDB.*;

public class Notifications {

    private DatabaseRepository db;

    public Notifications(DatabaseRepository repo){
        db = repo;

    }

    protected String[] getData() {

        //string info array to be returned
        String[] infoArray = new String[2];

        //gets the reminder entity object from livedata type
        ReminderEntity[] reminderArray = db.getReminders(1).getValue();
        //we just got one reminder, grab it
        ReminderEntity reminder = reminderArray[0];

        //what type of reminder is it?
        String reminderType = reminder.getClassification();
        //store immediately in the info array. TODO: figure out what to do for extraneous appts
        infoArray[0] = reminderType;

        if(reminderType.equals("MED")){
            //retrieve the medication object from the reminder
            MedicationEntity med = db.getMedById(reminder.getMedApptId());
            //get the med name
            infoArray[1] = med.getMedName();
        } else{
            //otherwise, we have an appointment;
            //TODO create appointment entity from id
            //TODO regular appointments get APPT, Extraneous ones get EAPPT

        }


        return infoArray;
    }

    //need to handle timing ourselves? This code may go in sendNotification
    //need to get current time somehow and check it against next reminder time


}
