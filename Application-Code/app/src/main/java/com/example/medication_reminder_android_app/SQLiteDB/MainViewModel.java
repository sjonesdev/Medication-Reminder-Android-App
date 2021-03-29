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

    public LiveData<List<MedicationEntity>> getMeds(){ return meds; }

    public Single<ReminderEntity[]> getReminders(int numOfReminders){
        return repository.getReminders(numOfReminders);
    }


    public Single<MedicationEntity> getMedById(long medId){
        return repository.getMedById(medId);
    }

    public Single<MedicationEntity> getMedByName(String name) {
        return repository.getMedByName(name);
    }


    public Single<ReminderEntity> getReminderById(long reminderId){
        return repository.getReminderById(reminderId);
    }


    //methods to insert rows into tables
    //insertMed and insertReminder can prob be removed.
    public void insertMedication(String medicationName, String inputDosage, boolean ifRecurring, String firstDate, String endDate,
                                 String inputTimeRule, String inputWarnings, String inputIngredients, String inputTags){
        Integer recurringBool = ifRecurring? 1 : 0;
        MedicationEntity medication = new MedicationEntity(medicationName, inputDosage, recurringBool, firstDate, endDate,
                inputTimeRule, 0, "", inputWarnings, inputIngredients, inputTags);
        repository.insertMed(medication);
    }

    public void insertReminder(String classification, String time, String date, Integer timeIntervalIndex, long medApptId){
        ReminderEntity reminder = new ReminderEntity(classification, time, date, timeIntervalIndex, medApptId);
        repository.insertReminder(reminder);
    }

    //TODO try to get back to not repeatin code from insertMedication
    public void insertMedAndReminder(String medicationName, String inputDosage, boolean ifRecurring, String firstDate, String endDate,
                                     String inputTimeRule, String inputWarnings, String inputIngredients, String inputTags){
        Integer recurringBool = ifRecurring? 1 : 0;
        MedicationEntity medication = new MedicationEntity(medicationName, inputDosage, recurringBool, firstDate, endDate,
                inputTimeRule, 0, "", inputWarnings, inputIngredients, inputTags);
        repository.insertMedAndReminder(medication);
    }

    public void updateAcknowledgements(MedicationEntity m, String newAcknowedgementList){
        repository.updateAcknowledgements(m, newAcknowedgementList);
    }


    public void updateReminderDateAndTime(ReminderEntity r, String date, String time, int timeIntervalIndex){
        repository.updateReminderDateAndTime(r, date, time, timeIntervalIndex);
    }


    //methods to delete rows from tables

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

    public void deleteMedication(MedicationEntity m){
        repository.deleteMed(m);
        repository.deleteReminderById(m.getReminderID());
    }

    //TODO make this delete the reminderEntity associated with med
    public void deleteMedication(String medName){
        repository.deleteMedByName(medName);
        //repository.deleteReminder(m);
    }


    //methods to clear tables of all values
    public void deleteAllMedications(){
        repository.deleteAllMeds();
        repository.deleteAllMedReminders();
    }

    public void deleteAllReminders(){
        repository.deleteAllReminders();
    }


}
