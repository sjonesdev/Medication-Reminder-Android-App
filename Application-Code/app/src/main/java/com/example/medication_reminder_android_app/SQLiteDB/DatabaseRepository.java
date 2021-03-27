package com.example.medication_reminder_android_app.SQLiteDB;

import android.app.Application;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.renderscript.ScriptGroup;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import io.reactivex.Single;

/**
 * @author Hayley Roberts
 * @lastModified 3/22/2021 by Hayley Roberts
 */


public class DatabaseRepository {  //extends

    private final DataAccessObject dao;
    private final AppDatabase db;

    //Want the reminder and medication entities to be live
    private final MutableLiveData<ReminderEntity[]> reminders = new MutableLiveData<>();
    private final MutableLiveData<MedicationEntity[]> meds = new MutableLiveData<>();

    private MedicationEntity singleMed;
    private ReminderEntity singleReminder;
    private long lastMedPk; // keep track of medication primary keys. Use bc pk auto-incr
    private long lastReminderPk; //leep track of last reminder pk. Use bc pk auto-incr

    public DatabaseRepository(Application application){
        db = AppDatabase.getDatabase(application);
        dao = db.dataAccessObject();
        lastMedPk = 0; //pk's techincally start with 1.
        lastReminderPk = 0;
    }

    private void reminderAsyncFinished(ReminderEntity[] r){ reminders.setValue(r); }
    private void medicationAsyncFilterFinished(MedicationEntity[] m) { meds.setValue(m); }
    private void getMedByNameAsyncFinished(MedicationEntity m) { singleMed = m; }
    private void getReminderAsyncFinished(ReminderEntity r) { singleReminder = r; }
    private void insertMedAsyncFinished(long medPk) { lastMedPk = medPk; }
    private void insertReminderAsyncFinished(long reminderPk) { lastReminderPk = reminderPk; }

    public Single<MedicationEntity[]> filterMedications(String[] tags){
        //because want to pull if have any tags, need to build the query WHERE clause as such
        String queryReq = ""; //to send to query so can get tags in any order
        if(tags.length > 1){
            for(String tag: tags){
                queryReq += " tags LIKE %" + tag + "% OR";
            }
            if(queryReq.length() > 4){
                queryReq = queryReq.substring(0, queryReq.length() - 4); //Remove last OR
            }
        } else{
            queryReq = "tags LIKE %%";
        }

        return dao.loadFilteredMedications(queryReq);
    }

    public Single<ReminderEntity[]> getReminders(int numOfReminders){
        //new AsyncNotificationReminder(dao, this).execute(numOfReminders);
        return dao.selectNextReminders(numOfReminders);
    }

    public Single<MedicationEntity> getMedByName(String medName){
        //new AsyncGetMedByName(dao, this).execute(medName);
        return dao.getMedicationByName(medName);
    }

    public Single<MedicationEntity> getMedById(long entityId){
        //new AsyncGetMedById(dao, this).execute(entityId);
        return dao.getMedicationById(entityId);
    }

    public Single<ReminderEntity> getReminderById(long entityId){
        //new AsyncGetReminderById(dao, this).execute(String.valueOf(entityId));
        return dao.getReminder(entityId);
    }

//    public Single<ReminderEntity> getReminderByMedName(String medName){
//        long reminderId = getMedByName(medName).getReminderID();
//        return getReminderById(reminderId);
//    }

    public long insertMed(MedicationEntity m){
        new AsyncInsertMedication(dao, this).execute(m);
        return lastMedPk + 1;
    }

    public long insertReminder(ReminderEntity r){
        new AsyncInsertReminder(dao, this).execute(r);
        return lastReminderPk + 1;
    }

    public void updateAcknowledgements(MedicationEntity m, String acknowledgementList) {
        new AsyncUpdateAcknowledgement(dao, m).execute(acknowledgementList);
    }

    public void addReminderID(MedicationEntity m, long reminderPK){ new AsyncAddReminderID(dao, m).execute(reminderPK); }

    //update the ReminderEntity with next date and time.
    //Date should be formatted as YYYY-MM-DD, and time should be formatted as HH:MM
    public void updateReminderDateAndTime(ReminderEntity r, String date, String time, int timeIntervalIndex){
        String dateTime = date + " " + time + " " + timeIntervalIndex;
        new AsyncReminderUpdateDateTime(dao, r).execute(dateTime);
    }

    public void deleteMed(MedicationEntity m){
        new AsyncDeleteMedication(dao).execute(m);
    }

    public void deleteMedByName(String medName) { new AsyncDeleteMedByName(dao).execute(medName); }

    public void deleteReminder(ReminderEntity r){
        new AsyncDeleteReminder(dao).execute(r);
    }

    public void deleteAllMedReminders() { new AsyncDeleteAllMedReminders(dao).execute(); }

    public void deleteAllApptReminders() { new AsyncDeleteAllApptReminders(dao).execute(); }

    public void deleteAllMeds(){
        new AsyncDeleteAllMeds(dao).execute();
    }

    public void deleteAllReminders(){
        new AsyncDeleteAllReminders(dao).execute();
    }




    private static class AsyncInsertMedication extends AsyncTask<MedicationEntity, Void, Long>{
        private final DataAccessObject dao;
        private final DatabaseRepository delegate;

        public AsyncInsertMedication(DataAccessObject inputHandler, DatabaseRepository repo){
            dao = inputHandler;
            delegate = repo;
        }

        @Override
        protected Long doInBackground(MedicationEntity... meds){
            Long pk = dao.insertMedication(meds[0]);
            meds[0].setPrimaryKey(pk);
            return pk;
        }

        @Override
        protected void onPostExecute(Long pk){
            delegate.insertMedAsyncFinished(pk);
        }

    }

    private static class AsyncInsertReminder extends AsyncTask<ReminderEntity, Void, Long>{
        private final DataAccessObject dao;
        private final DatabaseRepository delegate;

        public AsyncInsertReminder(DataAccessObject inputHandler, DatabaseRepository repo){
            dao = inputHandler;
            delegate = repo;
        }

        @Override
        protected Long doInBackground(ReminderEntity... reminders){
            Long pk = dao.insertReminder(reminders[0]);
            reminders[0].setPrimaryKey(pk);
            return pk;
        }

        @Override
        protected void onPostExecute(Long pk){
            delegate.insertReminderAsyncFinished(pk);
        }
    }

    private static class AsyncUpdateAcknowledgement extends AsyncTask<String, Void, Void>{
        private final DataAccessObject dao;
        private final MedicationEntity m;

        public AsyncUpdateAcknowledgement(DataAccessObject inputHandler, MedicationEntity medEntity){
            dao = inputHandler;
            m = medEntity;
        }

        @Override
        protected Void doInBackground(String... params){
            dao.updateAcknowledgements(m.getPrimaryKey(), params[0]);
            return null;
        }

    }

    private static class AsyncAddReminderID extends AsyncTask<Long, Void, Void>{
        private final DataAccessObject dao;
        private final MedicationEntity m;

        public AsyncAddReminderID(DataAccessObject d, MedicationEntity medEntity){
            dao = d;
            m = medEntity;
        }

        @Override
        protected Void doInBackground(Long... params){
            dao.addReminderID(m.getPrimaryKey(), params[0]);
            return null;
        }
    }

    private static class AsyncReminderUpdateDateTime extends AsyncTask<String, Void, Void>{
        private final DataAccessObject dao;
        private final ReminderEntity r;

        public AsyncReminderUpdateDateTime(DataAccessObject d, ReminderEntity reminderEntity){
            dao = d;
            r = reminderEntity;
        }

        @Override
        protected Void doInBackground(String... params){
            String[] dateTime = params[0].split(" ");
            dao.updateDateAndTime(r.getPrimaryKey(), dateTime[0], dateTime[1], Integer.parseInt(dateTime[2]));
            return null;
        }

    }

    private static class AsyncDeleteMedication extends AsyncTask<MedicationEntity, Void, Void>{
        private final DataAccessObject dao;

        public AsyncDeleteMedication(DataAccessObject inputHandler){
            dao = inputHandler;
        }

        @Override
        protected Void doInBackground(MedicationEntity... meds){
            dao.deleteMedication(meds[0]);
            return null;
        }
    }

    private static class AsyncDeleteMedByName extends AsyncTask<String, Void, Void>{
        private final DataAccessObject dao;

        public AsyncDeleteMedByName(DataAccessObject d){ dao = d; }

        @Override
        protected Void doInBackground(String... params){
            dao.deleteMedicationByName(params[0]);
            return null;
        }
    }

    private static class AsyncDeleteReminder extends AsyncTask<ReminderEntity, Void, Void>{
        private final DataAccessObject dao;

        public AsyncDeleteReminder(DataAccessObject inputHandler){
            dao = inputHandler;
        }

        @Override
        protected Void doInBackground(ReminderEntity... reminders){
            dao.deleteReminder(reminders[0]);
            return null;
        }
    }

    private static class AsyncDeleteAllMedReminders extends AsyncTask<Void, Void, Void>{
        private final DataAccessObject dao;

        public AsyncDeleteAllMedReminders(DataAccessObject d){
            dao = d;
        }

        @Override
        protected Void doInBackground(Void... params){
            dao.deleteAllMedicationReminders();
            return null;
        }
    }

    private static class AsyncDeleteAllApptReminders extends AsyncTask<Void, Void, Void>{
        private final DataAccessObject dao;

        public AsyncDeleteAllApptReminders(DataAccessObject d){
            dao = d;
        }

        @Override
        protected Void doInBackground(Void... params){
            dao.deleteAllAppointmentReminders();
            return null;
        }
    }


    private static class AsyncDeleteAllMeds extends AsyncTask<Void, Void, Void>{
        private final DataAccessObject dao;

        public AsyncDeleteAllMeds(DataAccessObject inputHandler){
            dao = inputHandler;
        }

        @Override
        protected Void doInBackground(Void... voids){
            dao.clearAllMedications();
            return null;
        }
    }

    private static class AsyncDeleteAllReminders extends AsyncTask<Void, Void, Void>{
        private final DataAccessObject dao;

        public AsyncDeleteAllReminders(DataAccessObject inputHandler){
            dao = inputHandler;
        }

        @Override
        protected Void doInBackground(Void... voids){
            dao.clearAllReminders();
            return null;
        }
    }

}
