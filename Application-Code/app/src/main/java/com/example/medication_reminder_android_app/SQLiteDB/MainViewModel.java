package com.example.medication_reminder_android_app.SQLiteDB;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

/**
 * @author Hayley Roberts
 * @lastModified 3/5/2021 by Hayley Roberts
 */

public class MainViewModel extends AndroidViewModel {
    private final DatabaseRepository repository;
    private final MutableLiveData<MedicationEntity[]> meds;
    private final MutableLiveData<ReminderEntity[]> reminders;

    public MainViewModel(Application application){
        super(application);
        repository = new DatabaseRepository(application);
        meds = repository.filterMedications(new String[] {""});
        reminders = repository.getReminders(5);
    }


    //Methods to be used in other places in the code like the UI and notification
    public MutableLiveData<MedicationEntity[]> getMeds(String[] tags){
        return repository.filterMedications(tags);
    }

    public MutableLiveData<MedicationEntity[]> getMeds(){
        return repository.filterMedications(new String[] {""});
    }

    public MutableLiveData<ReminderEntity[]> getReminders(int numOfReminders){
        return repository.getReminders(numOfReminders);
    }

    //methods to insert rows into tables
    public void insertMedication(String medicationName, String inputDosage, boolean ifRecurring, String firstDate,
                                 String inputTimeRule, int reminderId, String inputAcknowledgements,
                                 String inputWarnings, String inputIngredients, String inputTags){
        Integer recurringBool = ifRecurring? 1 : 0;
        MedicationEntity medication = new MedicationEntity(medicationName, inputDosage, recurringBool, firstDate,
                inputTimeRule, reminderId, inputAcknowledgements, inputWarnings, inputIngredients, inputTags);
        repository.insertMed(medication);
    }

    public void insertReminder(String classification, String time, String date, Integer timeIntervalIndex, Integer medApptId){
        ReminderEntity reminder = new ReminderEntity(classification, time, date, timeIntervalIndex, medApptId);
        repository.insertReminder(reminder);
    }


    //methods to delete rows from tables
    public void deleteReminder(MedicationEntity medEntity){
        ReminderEntity r = repository.getReminderByName(medEntity.getReminderID());
        repository.deleteReminder(r);
    }

    public void deleteReminder(AppointmentEntity apptEntity){
        ReminderEntity r = repository.getReminderByName(apptEntity.getRemindTabID());
        repository.deleteReminder(r);
    }

    public void deleteReminder(ReminderEntity r){
        repository.deleteReminder(r);
    }

    public void deleteMedication(MedicationEntity m){
        repository.deleteMed(m);
    }

    public void deleteMedication(String medName){
        MedicationEntity m = repository.getMedByName(medName);
        Integer medRid = m.getReminderID();
        repository.deleteReminder(repository.getReminderByName(medRid));
        repository.deleteMed(m);
    }


    //methods to clear tables of all values
    public void deleteAllMedications(){
        repository.deleteAllMeds();
    }

    public void deleteAllReminders(){
        repository.deleteAllReminders();
    }

}
