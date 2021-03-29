package com.example.medication_reminder_android_app.SQLiteDB;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.medication_reminder_android_app.NotificationRelay.Notifications;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Hayley Roberts
 * @lastModified 3/22/2021 by Hayley Roberts
 */

public class MainViewModel extends AndroidViewModel {
    public final DatabaseRepository repository;
    private final LiveData<List<MedicationEntity>> meds;

    public MainViewModel(Application application){
        super(application);
        repository = new DatabaseRepository(application);
        meds = repository.getAllMeds();
    }

    /**
     * @author Hayley Roberts
     * Return all of the medications in the Medication Table
     * @return
     */
    public LiveData<List<MedicationEntity>> getMeds(){ return meds; }

    /**
     * @author Hayley Roberts
     * Get a certain number of reminders from the Reminder Table
     * @param numOfReminders
     * @return
     */
    public Single<ReminderEntity[]> getReminders(int numOfReminders){
        return repository.getReminders(numOfReminders);
    }

    /**
     * @author Hayley Roberts
     * Get a medication by primary key
     * @param medId
     * @return
     */
    public Single<MedicationEntity> getMedById(long medId){
        return repository.getMedById(medId);
    }

    /**
     * @author Hayley Roberts
     * Get a medication by medication name
     * @param name
     * @return
     */
    public Single<MedicationEntity> getMedByName(String name) {
        return repository.getMedByName(name);
    }

    /**
     * @author Hayley Roberts
     * Get a reminder by primary key
     * @param reminderId
     * @return
     */
    public Single<ReminderEntity> getReminderById(long reminderId){
        return repository.getReminderById(reminderId);
    }

    /**
     * @author Hayley Roberts
     * Insert a medication into Medication table
     * @param medicationName
     * @param inputDosage
     * @param ifRecurring
     * @param firstDate
     * @param endDate
     * @param inputTimeRule
     * @param inputWarnings
     * @param inputIngredients
     * @param inputTags
     */
    public void insertMedication(String medicationName, String inputDosage, boolean ifRecurring, String firstDate, String endDate,
                                 String inputTimeRule, String inputWarnings, String inputIngredients, String inputTags){
        Integer recurringBool = ifRecurring? 1 : 0;
        MedicationEntity medication = new MedicationEntity(medicationName, inputDosage, recurringBool, firstDate, endDate,
                inputTimeRule, 0, "", inputWarnings, inputIngredients, inputTags);
        repository.insertMed(medication);
    }

    /**
     * @author Hayley Roberts
     * Insert Reminder into reminder Table
     * @param classification
     * @param time
     * @param date
     * @param timeIntervalIndex
     * @param medApptId
     */
    public void insertReminder(String classification, String time, String date, Integer timeIntervalIndex, long medApptId){
        ReminderEntity reminder = new ReminderEntity(classification, time, date, timeIntervalIndex, medApptId);
        repository.insertReminder(reminder);
    }

    /**
     * @author Hayley Roberts
     * Insert a medication and the associated reminder into the respective tables
     * @param medicationName
     * @param inputDosage
     * @param ifRecurring
     * @param firstDate
     * @param endDate
     * @param inputTimeRule
     * @param inputWarnings
     * @param inputIngredients
     * @param inputTags
     */
    //TODO try to get back to not repeatin code from insertMedication
    public void insertMedAndReminder(String medicationName, String inputDosage, boolean ifRecurring, String firstDate, String endDate,
                                     String inputTimeRule, String inputWarnings, String inputIngredients, String inputTags){
        Integer recurringBool = ifRecurring? 1 : 0;
        MedicationEntity medication = new MedicationEntity(medicationName, inputDosage, recurringBool, firstDate, endDate,
                inputTimeRule, 0, "", inputWarnings, inputIngredients, inputTags);
        repository.insertMedAndReminder(medication);
    }

    /**
     * @author Hayley Roberts
     * Update acknowledgement list for a medicatoin entity
     * @param m
     * @param newAcknowedgementList
     */
    public void updateAcknowledgements(MedicationEntity m, String newAcknowedgementList){
        repository.updateAcknowledgements(m, newAcknowedgementList);
    }


    /**
     * @author Hayley Roberts
     * Update the date and time of a reminder entity
     * @param r
     * @param date
     * @param time
     * @param timeIntervalIndex
     */
    public void updateReminderDateAndTime(ReminderEntity r, String date, String time, int timeIntervalIndex){
        repository.updateReminderDateAndTime(r, date, time, timeIntervalIndex);
    }


    //methods to delete rows from tables

    /**
     * @author Hayley Roberts
     * Delete a reminder from the Reminder Table
     * @param medEntity
     */
    public void deleteReminder(MedicationEntity medEntity){
        repository.deleteReminderById(medEntity.getReminderID());
    }

    //TODO handle with SIngle and threads...
//    public void deleteReminder(AppointmentEntity apptEntity){
//        ReminderEntity r = getReminderById(apptEntity.getRemindTabID());
//        repository.deleteReminder(r);
//    }

    public void deleteReminder(ReminderEntity r){
        repository.deleteReminder(r);
    }

    /**
     * @author Hayley Roberts
     * Delete a medication from the Medication Table, and its associated reminders
     * @param m
     */
    public void deleteMedication(MedicationEntity m){
        repository.deleteMed(m);
        repository.deleteReminderById(m.getReminderID());
    }

    /**
     * @author Hayley Roberts
     * Delete a medication from MEdication Table, and its associated reminder
     * @param medName
     */
    //TODO make this delete the reminderEntity associated with med
    public void deleteMedication(String medName){
        repository.deleteMedByName(medName);
        //repository.deleteReminder(m);
    }


    //methods to clear tables of all values

    /**
     * @author Hayley Roberts
     * Delete all Medications from the Medication Table
     */
    public void deleteAllMedications(){
        repository.deleteAllMeds();
        repository.deleteAllMedReminders();
    }

    /**
     * @author Hayley Roberts
     * Delete all Remidners from the Reminder Table
     */
    public void deleteAllReminders(){
        repository.deleteAllReminders();
    }


}
