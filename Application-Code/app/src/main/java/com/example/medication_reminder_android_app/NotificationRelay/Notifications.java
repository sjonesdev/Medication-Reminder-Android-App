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

    /**
    @author: Karley Waguespack
    Last Modified: 03/22/2021

    Description: Gets data about the reminder that was inputted
                 returns a string array of information needed to build notification.

    @params: reminderID: the id for the reminder

    Array contents:
                0th element: medication or doctor name (or type of appointment if no doctor)
                1st element: notification type
                2nd element: the associated reminder id

    Notification type key:
                Medication = "MED"
                Doctor Appointment = "APPT"
                Extraneous Appointment = "EAPPT"
     */

    //should take either a reminder or a reminder ID
    protected String[] getData(long reminderID) {

        //string info array to be returned
        String[] infoArray = new String[2];

        ReminderEntity reminder = model.getReminderById(reminderID);

        //what type of reminder is it?
        String reminderType = reminder.getClassification();
        //store immediately in the info array.
        infoArray[0] = reminderType;

        if(reminderType.equals("M")){
            //retrieve the medication object from the reminder
            MedicationEntity med = model.getMedById(reminder.getMedApptId());
            //get the med name
            infoArray[1] = med.getMedName();
        } else{
            //otherwise, we have an appointment;
            //TODO create appointment entity from id
            //TODO regular appointments get APPT, Extraneous ones get EAPPT; check if it has a doctorId

        }

        infoArray[2] = Long.toString(reminderID);
        return infoArray;
    }


}
