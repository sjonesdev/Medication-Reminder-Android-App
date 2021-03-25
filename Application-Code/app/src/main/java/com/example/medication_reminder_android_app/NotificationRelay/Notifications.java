package com.example.medication_reminder_android_app.NotificationRelay;

/*
This is the base class
This class handles formatting and sending notifications to the user
This handles external notifications
 */

import com.example.medication_reminder_android_app.SQLiteDB.*;

public class Notifications {

    protected MainViewModel model;

    public Notifications(MainViewModel model){
        this.model = model;

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
        ReminderEntity reminderArray[] = model.getReminders(1).getValue();
        //we just got one reminder, grab it
        ReminderEntity reminder = reminderArray[0];

        //what type of reminder is it?
        String reminderType = reminder.getClassification();
        //store immediately in the info array.
        infoArray[0] = reminderType;

        if(reminderType.equals("MED")){
            //retrieve the medication object from the reminder
            MedicationEntity med = model.getMedById(reminder.getMedApptId());
            //put the med name in the array
            infoArray[1] = med.getMedName();
        } else{
            //otherwise, we have an appointment; will be completed when we get to viable
            //TODO create appointment entity from id
            //TODO check if it has a doctorId and assign appropriate notification type

        }


        return infoArray;
    }


}
