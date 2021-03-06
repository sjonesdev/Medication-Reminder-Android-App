package com.example.medication_reminder_android_app.NotificationRelay;

/*
This is the base class
This class handles formatting and sending notifications to the user
This handles external notifications
 */


/*notes:
    -particular reason for wanting classification as an int? unnecessary conversion involved
    -just doing medications; don't include any implementation code for appointment notifications

    -how do we retrieve an entity from a table given its id?
    -what class fromSQLite db should be imported?
    -what method to use to retrieve data

 */

import com.example.medication_reminder_android_app.SQLiteDB.ReminderEntity;
import com.example.medication_reminder_android_app.SQLiteDB.MedicationEntity;
import com.example.medication_reminder_android_app.SQLiteDB.*;

public class Notifications {

    public Notifications(){

    }

    protected String[] getData() {

        //need to figure out how to use these methods
        ReminderEntity reminder = MainViewModel.getReminders()

        //need to get the reminder then use the medication ID to get the medication
        MedicationEntity med = new MedicationEntity("Advil", "10mg", 1, "10:00", 1, "-", "-", "-", "-");



        //create a string array for storing all info
        String[] infoArray = new String[2];
        //type of reminder (med = MED, appt = APPT, extraneous ??);
        infoArray[0] = reminder.getClassification();
        //medication name
        infoArray[1] = med.getMedName();

        return infoArray;
    }

    //need to handle timing ourselves? This code may go in sendNotification
    //need to get current time somehow and check it against next reminder time


}
