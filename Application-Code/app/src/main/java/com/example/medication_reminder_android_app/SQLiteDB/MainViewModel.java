package com.example.medication_reminder_android_app.SQLiteDB;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

/**
 * @author Hayley Roberts
 * @lastModified 3/22/2021 by Hayley Roberts
 */

public class MainViewModel extends AndroidViewModel {
    public final DatabaseRepository repository; //change back
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

    public MutableLiveData<String[]> getAllMedNames(){
        MutableLiveData<String[]> retVal = new MutableLiveData<>();
        MedicationEntity[] meds = getMeds().getValue();
        String[] medNames = new String[meds.length];

        for(int i = 0; i < meds.length; i++){
            medNames[i] = meds[i].getMedName();
        }

        retVal.setValue(medNames);
        return retVal;
    }

    public MedicationEntity getMedById(long medId){
        return repository.getMedById(medId);
    }

    public ReminderEntity getReminderById(long reminderId){
        return repository.getReminderById(reminderId);
    }

    //methods to insert rows into tables
    public long insertMedication(String medicationName, String inputDosage, boolean ifRecurring, String firstDate, String endDate,
                                 String inputTimeRule, String inputWarnings, String inputIngredients, String inputTags){
        Integer recurringBool = ifRecurring? 1 : 0;
        MedicationEntity medication = new MedicationEntity(medicationName, inputDosage, recurringBool, firstDate, endDate,
                inputTimeRule, 0, "", inputWarnings, inputIngredients, inputTags);
        return repository.insertMed(medication);
    }

    public long insertReminder(String classification, String time, String date, Integer timeIntervalIndex, long medApptId){
        ReminderEntity reminder = new ReminderEntity(classification, time, date, timeIntervalIndex, medApptId);
        return repository.insertReminder(reminder);
    }

    public long insertMedAndReminder(String medicationName, String inputDosage, boolean ifRecurring, String firstDate, String endDate,
                                     String inputTimeRule, String inputWarnings, String inputIngredients, String inputTags){
        long medPK = insertMedication(medicationName, inputDosage, ifRecurring, firstDate, endDate,
                inputTimeRule, inputWarnings, inputIngredients, inputTags);
        String[] sepDate = firstDate.split(" ");

        long reminderPK = insertReminder("M", sepDate[1], sepDate[0], 0, medPK);
        MedicationEntity m = repository.getMedById(medPK);
        repository.addReminderID(m, reminderPK);
        return reminderPK;
    }

    public void updateAcknowledgements(MedicationEntity m, String newAcknowedgementList){
        repository.updateAcknowledgements(m, newAcknowedgementList);
    }

    public void updateAcknowledgements(String medName, String newAcknowledgementList){
        MedicationEntity m = repository.getMedByName(medName);
        repository.updateAcknowledgements(m, newAcknowledgementList);
    }

    public void updateAcknowledgements(long MedPrimaryKey, String newAcknowledgementList){
        MedicationEntity m = repository.getMedById(MedPrimaryKey);
        repository.updateAcknowledgements(m, newAcknowledgementList);
    }

    public void updateReminderDateAndTime(ReminderEntity r, String date, String time, int timeIntervalIndex){
        repository.updateReminderDateAndTime(r, date, time, timeIntervalIndex);
    }


    //methods to delete rows from tables
    public void deleteReminder(MedicationEntity medEntity){
        ReminderEntity r = repository.getReminderById(medEntity.getReminderID());
        repository.deleteReminder(r);
    }

    public void deleteReminder(AppointmentEntity apptEntity){
        ReminderEntity r = repository.getReminderById(apptEntity.getRemindTabID());
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
        long medRid = m.getReminderID();
        repository.deleteReminder(repository.getReminderById(medRid));
        repository.deleteMed(m);
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
