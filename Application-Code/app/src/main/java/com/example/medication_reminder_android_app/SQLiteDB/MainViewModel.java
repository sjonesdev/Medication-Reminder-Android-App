package com.example.medication_reminder_android_app.SQLiteDB;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.medication_reminder_android_app.NotificationRelay.Notifications;
import com.example.medication_reminder_android_app.UserInterface.InfoInput;

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
    public final DatabaseRepository repository; //change back
    private final LiveData<MedicationEntity[]> meds;
    //private final MutableLiveData<ReminderEntity[]> reminders;

    public MainViewModel(Application application){
        super(application);
        repository = new DatabaseRepository(application);
        meds = repository.getAllMeds();
        //reminders = repository.getReminders(5);
    }


    //Methods to be used in other places in the code like the UI and notification
    public LiveData<MedicationEntity[]> getMeds(String[] tags){
        return repository.filterMedications(tags);
    }

    public LiveData<MedicationEntity[]> getMeds(){ return meds; }

    public Single<ReminderEntity[]> getReminders(int numOfReminders){
        return repository.getReminders(numOfReminders);
    }

//    public MutableLiveData<String[]> getAllMedNames(){
//        MutableLiveData<String[]> retVal = new MutableLiveData<>();
//        MedicationEntity[][] meds = new MedicationEntity[1][1];
//
//        getMeds().subscribeOn(Schedulers.io()).subscribe(new DisposableSingleObserver<MedicationEntity[]>() {
//            @Override
//            public void onSuccess(@NonNull MedicationEntity[] medicationEntities) {
//                meds[0] = medicationEntities;
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//                meds[0] = null;
//            }
//        });
//
//        if(meds[0] != null){
//            MedicationEntity[] myMeds = meds[0];
//            String[] medNames = new String[myMeds.length];
//
//            for(int i = 0; i < myMeds.length; i++){
//                medNames[i] = myMeds[i].getMedName();
//            }
//
//            retVal.setValue(medNames);
//        }
//        return retVal;
//    }

    public Single<MedicationEntity> getMedById(long medId){
        return repository.getMedById(medId);
    }

    public Single<MedicationEntity> getMedByName(String name) {
        return repository.getMedByName(name);
    }

    public void getReminderByIdForNotifRelay(long reminderId){
        repository.getReminderById(reminderId).subscribeOn(Schedulers.io()).subscribe(new DisposableSingleObserver<ReminderEntity>() {
            @Override
            public void onSuccess(@NonNull ReminderEntity reminderEntity) {

            }

            @Override
            public void onError(@NonNull Throwable e) {
            }
        });
    }

    public Single<ReminderEntity> getReminderById(long reminderId){
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

    //TODO try to get back to not repeatin code from insertMedication
    public long insertMedAndReminder(String medicationName, String inputDosage, boolean ifRecurring, String firstDate, String endDate,
                                     String inputTimeRule, String inputWarnings, String inputIngredients, String inputTags){
        Integer recurringBool = ifRecurring? 1 : 0;
        MedicationEntity medication = new MedicationEntity(medicationName, inputDosage, recurringBool, firstDate, endDate,
                inputTimeRule, 0, "", inputWarnings, inputIngredients, inputTags);
        long medPK = repository.insertMed(medication);
        String[] sepDate = firstDate.split(" ");

        long reminderPK = insertReminder("M", sepDate[1], sepDate[0], 0, medPK);
        //MedicationEntity m = getMedById(medPK);

        repository.addReminderID(medication, reminderPK);
        Log.d("debug", "insertMedAndReminder: reposited");
        return reminderPK;
    }

    public void updateAcknowledgements(MedicationEntity m, String newAcknowedgementList){
        repository.updateAcknowledgements(m, newAcknowedgementList);
    }

    //TODO handle with SIngle and threads
//    public void updateAcknowledgements(String medName, String newAcknowledgementList){
//        MedicationEntity m = getMedByName(medName);
//        repository.updateAcknowledgements(m, newAcknowledgementList);
//    }

//    public void updateAcknowledgements(MedicationEntity m, String newAcknowledgementList){
//        //MedicationEntity m = getMedById(MedPrimaryKey);
//        repository.updateAcknowledgements(m, newAcknowledgementList);
//    }

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
