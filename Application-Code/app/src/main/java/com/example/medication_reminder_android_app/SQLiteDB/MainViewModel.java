package com.example.medication_reminder_android_app.SQLiteDB;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
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
    //private final MutableLiveData<MedicationEntity[]> meds;
    //private final MutableLiveData<ReminderEntity[]> reminders;

    public MainViewModel(Application application){
        super(application);
        repository = new DatabaseRepository(application);
        //meds = repository.filterMedications(new String[] {""});
        //reminders = repository.getReminders(5);
    }


    //Methods to be used in other places in the code like the UI and notification
    public Single<MedicationEntity[]> getMeds(String[] tags){
        return repository.filterMedications(tags);
//        final MedicationEntity[][] meds = new MedicationEntity[1][1];
//        repository.filterMedications(tags).subscribeOn(Schedulers.io()).subscribe(new DisposableSingleObserver<MedicationEntity[]>() {
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
//        return meds[0];
    }

    public Single<MedicationEntity[]> getMeds(){
        return getMeds(new String[] {""});
    }

    public Single<ReminderEntity[]> getReminders(int numOfReminders){
        return repository.getReminders(numOfReminders);
//        final ReminderEntity[][] reminders = new ReminderEntity[1][1];
//        repository.getReminders(numOfReminders).subscribeOn(Schedulers.io()).subscribe(new DisposableSingleObserver<ReminderEntity[]>() {
//            @Override
//            public void onSuccess(@NonNull ReminderEntity[] reminderEntities) {
//                reminders[0] = reminderEntities;
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//                reminders[0] = null;
//            }
//        });
//        return reminders[0];
    }

    public MutableLiveData<String[]> getAllMedNames(){
        MutableLiveData<String[]> retVal = new MutableLiveData<>();
        MedicationEntity[][] meds = new MedicationEntity[1][1];

        getMeds().subscribeOn(Schedulers.io()).subscribe(new DisposableSingleObserver<MedicationEntity[]>() {
            @Override
            public void onSuccess(@NonNull MedicationEntity[] medicationEntities) {
                meds[0] = medicationEntities;
            }

            @Override
            public void onError(@NonNull Throwable e) {
                meds[0] = null;
            }
        });

        if(meds[0] != null){
            MedicationEntity[] myMeds = meds[0];
            String[] medNames = new String[myMeds.length];

            for(int i = 0; i < myMeds.length; i++){
                medNames[i] = myMeds[i].getMedName();
            }

            retVal.setValue(medNames);
        }
        return retVal;
    }

    public Single<MedicationEntity> getMedById(long medId){
        return repository.getMedById(medId);
//        final MedicationEntity[] med = new MedicationEntity[1];
//        repository.getMedById(medId).subscribeOn(Schedulers.io()).subscribe(new DisposableSingleObserver<MedicationEntity>() {
//            @Override
//            public void onSuccess(@NonNull MedicationEntity medicationEntity) {
//                med[0] = medicationEntity;
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//                med[0] = null;
//            }
//        });
//        return med[0];
    }

    public Single<MedicationEntity> getMedByName(String name) {
        return repository.getMedByName(name);
//        final MedicationEntity[] med = new MedicationEntity[1];
//        repository.getMedByName(name).subscribeOn(Schedulers.io()).subscribe(new DisposableSingleObserver<MedicationEntity>() {
//            @Override
//            public void onSuccess(@NonNull MedicationEntity medicationEntity) {
//                med[0] = medicationEntity;
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//                med[0] = null;
//            }
//        });
//        return med[0];
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
//        final ReminderEntity[] r = new ReminderEntity[1];
//        repository.getReminderById(reminderId).subscribeOn(Schedulers.io()).subscribe(new DisposableSingleObserver<ReminderEntity>() {
//            @Override
//            public void onSuccess(@NonNull ReminderEntity reminderEntity) {
//                r[0] = reminderEntity;
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//                r[0] = null;
//            }
//        });
//        return r[0];
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
    //TODO handle with SIngle and threads...
//    public void deleteReminder(MedicationEntity medEntity){
//        ReminderEntity r = getReminderById(medEntity.getReminderID());
//        repository.deleteReminder(r);
//    }
//
//    public void deleteReminder(AppointmentEntity apptEntity){
//        ReminderEntity r = getReminderById(apptEntity.getRemindTabID());
//        repository.deleteReminder(r);
//    }

    public void deleteReminder(ReminderEntity r){
        repository.deleteReminder(r);
    }

    public void deleteMedication(MedicationEntity m){
        repository.deleteMed(m);
    }

//    public void deleteMedication(String medName){
//        MedicationEntity m = getMedByName(medName);
//        long medRid = m.getReminderID();
//        repository.deleteReminder(getReminderById(medRid));
//        repository.deleteMed(m);
//    }


    //methods to clear tables of all values
    public void deleteAllMedications(){
        repository.deleteAllMeds();
        repository.deleteAllMedReminders();
    }

    public void deleteAllReminders(){
        repository.deleteAllReminders();
    }


}
