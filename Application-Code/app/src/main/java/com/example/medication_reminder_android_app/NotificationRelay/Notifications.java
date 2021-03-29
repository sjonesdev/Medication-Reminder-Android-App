package com.example.medication_reminder_android_app.NotificationRelay;

/*
This is the base class
This class handles formatting and sending notifications to the user
This handles external notifications
 */

import android.util.Log;

import com.example.medication_reminder_android_app.SQLiteDB.*;

import io.reactivex.annotations.NonNull;
import io.reactivex.internal.functions.Functions;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

public abstract class Notifications {

    protected MainViewModel model;
    protected String[] info;

    public Notifications(MainViewModel model){
        this.model = model;
        this.info = new String[3];

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

    protected abstract void sendInfoArray(String[] info, ReminderEntity reminder);

    //should take either a reminder or a reminder ID
    protected void getData(long reminderID) {

        model.getReminderById(reminderID).subscribeOn(Schedulers.io()).subscribe(new DisposableSingleObserver<ReminderEntity>() {
            @Override
            public void onSuccess(@NonNull ReminderEntity reminderEntity) {
                getDataHelper1(reminderEntity);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });


    }

    private void getDataHelper1(ReminderEntity reminder){
        String reminderType = reminder.getClassification();

        if(reminderType.equals("M")){
            //retrieve the medication object from the reminder
            long medPk = reminder.getMedApptId();
            model.getMedById(medPk).subscribeOn(Schedulers.io()).subscribe(new DisposableSingleObserver<MedicationEntity>() {
                @Override
                public void onSuccess(@NonNull MedicationEntity medicationEntity) {
                    getDataHelperMed(medicationEntity, reminder);
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    RxJavaPlugins.setErrorHandler(Functions.<Throwable>emptyConsumer());
                }
            });

        } else{
            //otherwise, we have an appointment;
            //TODO create appointment entity from id
            //TODO regular appointments get APPT, Extraneous ones get EAPPT; check if it has a doctorId

        }
    }

    private void getDataHelperMed(MedicationEntity med, ReminderEntity r){
        if(r == null){
            Log.d("app-notif-debug", "Reminder is null. line 100");
        }

        if(med == null){
            Log.d("app-notif-debug", "Med is null. line 104");
        }
        //string info array to be returned
        String[] infoArray = new String[3];

        infoArray[0] = "M";
        //get the med name
        infoArray[1] = med.getMedName();
        infoArray[2] = Long.toString(med.getReminderID());
        sendInfoArray(infoArray, r);
        //return infoArray;
    }

    private void getDataHelperDr(){
        //otherwise, we have an appointment;
        //TODO create appointment entity from id
        //TODO regular appointments get APPT, Extraneous ones get EAPPT; check if it has a doctorId
    }


}
