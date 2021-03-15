package com.example.medication_reminder_android_app.SQLiteDB;

import android.app.Application;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.renderscript.ScriptGroup;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

/**
 * @author Hayley Roberts
 * @lastModified 3/5/2021 by Hayley Roberts
 */


public class DatabaseRepository {  //extends

    private final DataAccessObject dao;
    private final AppDatabase db;

    //Want the reminder and medication entities to be live
    private final MutableLiveData<ReminderEntity[]> reminders = new MutableLiveData<>();
    private final MutableLiveData<MedicationEntity[]> meds = new MutableLiveData<>();

    private MedicationEntity singleMed;
    private ReminderEntity singleReminder;

    public DatabaseRepository(Application application){
        db = AppDatabase.getDatabase(application);
        dao = db.dataAccessObject();
    }

    private void reminderAsyncFinished(ReminderEntity[] r){ reminders.setValue(r); }
    private void medicationAsyncFilterFinished(MedicationEntity[] m) { meds.setValue(m); }
    private void getMedByNameAsyncFinished(MedicationEntity m) { singleMed = m; }
    private void getReminderAsyncFinished(ReminderEntity r) { singleReminder = r; }

    public MutableLiveData<MedicationEntity[]> filterMedications(String[] tags){
        new AsyncFilter(dao, this).execute(tags);
        return meds;
    }

    public MutableLiveData<ReminderEntity[]> getReminders(int numOfReminders){
        new AsyncNotificationReminder(dao, this).execute(numOfReminders);
        return reminders;
    }

    public MedicationEntity getMedByName(String medName){
        new AsyncGetMedByName(dao, this).execute(medName);
        return singleMed;
    }

    public MedicationEntity getMedById(int entityId){
        new AsyncGetMedById(dao, this).execute(entityId);
        return singleMed;
    }

    public ReminderEntity getReminderById(int entityId){
        new AsyncGetReminderById(dao, this).execute(String.valueOf(entityId));
        return singleReminder;
    }

    public void insertMed(MedicationEntity m){
        new AsyncInsertMedication(dao).execute(m);
    }

    public void insertReminder(ReminderEntity r){
        new AsyncInsertReminder(dao).execute(r);
    }

    public void updateAcknowledgements(MedicationEntity m, String acknowledgementList) {
        new AsyncUpdateAcknowledgement(dao, m).execute(acknowledgementList);
    }

    public void addReminderID(MedicationEntity m, Integer reminderPK){ new AsyncAddReminderID(dao, m).execute(String.valueOf(reminderPK)); }

    //update the ReminderEntity with next date and time.
    //Date should be formatted as YYYY-MM-DD, and time should be formatted as HH:MM
    public void updateReminderDateAndTime(ReminderEntity r, String date, String time, int timeIntervalIndex){
        String dateTime = date + " " + time + " " + timeIntervalIndex;
        new AsyncReminderUpdateDateTime(dao, r).execute(dateTime);
    }

    public void deleteMed(MedicationEntity m){
        new AsyncDeleteMedication(dao).execute(m);
    }

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



    //Async classes so can do queries in background
    private static class AsyncFilter extends AsyncTask<String, Void, MedicationEntity[]>{
        private final DataAccessObject dao;
        private final DatabaseRepository delegate;

        public AsyncFilter(DataAccessObject filter, DatabaseRepository repo){
            dao = filter;
            delegate = repo;
        }


        @Override
        protected MedicationEntity[] doInBackground(String... params){
            //because want to pull if have any tags, need to build the query WHERE clause as such
            String queryReq = ""; //to send to query so can get tags in any order
            if(params.length > 1){
                for(String tag: params){
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

        @Override
        protected void onPostExecute(MedicationEntity[] meds) {
            delegate.medicationAsyncFilterFinished(meds);
        }

    }

    private static class AsyncGetMedByName extends AsyncTask<String, Void, MedicationEntity>{
        private final DataAccessObject dao;
        private final DatabaseRepository delegate;

        public AsyncGetMedByName(DataAccessObject filter, DatabaseRepository repo){
            dao = filter;
            delegate = repo;
        }

        @Override
        protected MedicationEntity doInBackground(String... params){
            return dao.getMedicationByName(params[0]);
        }

        @Override
        protected void onPostExecute(MedicationEntity result){
            delegate.getMedByNameAsyncFinished(result);
        }
    }

    private static class AsyncGetMedById extends AsyncTask<Integer, Void, MedicationEntity>{
        private final DataAccessObject dao;
        private final DatabaseRepository delegate;

        public AsyncGetMedById(DataAccessObject filter, DatabaseRepository repo){
            dao = filter;
            delegate = repo;
        }

        @Override
        protected MedicationEntity doInBackground(Integer... params){
            return dao.getMedicationById(params[0]);
        }

        @Override
        protected void onPostExecute(MedicationEntity result){
            delegate.getMedByNameAsyncFinished(result);
        }
    }

    private static class AsyncGetReminderById extends AsyncTask<String, Void, ReminderEntity>{
        private final DatabaseRepository delegate;
        private final DataAccessObject dao;

        public AsyncGetReminderById(DataAccessObject filter, DatabaseRepository repo){
            dao = filter;
            delegate = repo;
        }

        @Override
        protected ReminderEntity doInBackground(String... params){
            return dao.getReminder(Integer.getInteger(params[0]));
        }

        @Override
        protected void onPostExecute(ReminderEntity r){
            delegate.getReminderAsyncFinished(r);
        }
    }


    private static class AsyncNotificationReminder extends AsyncTask<Integer, Void, ReminderEntity[]>{
        private final DatabaseRepository delegate;
        private final DataAccessObject dao;

        public AsyncNotificationReminder(DataAccessObject notif, DatabaseRepository repo){
            dao = notif;
            delegate = repo;
        }

        @Override
        protected ReminderEntity[] doInBackground(Integer... params){
            return dao.selectNextReminders(params[0]);
        }

        @Override
        protected void onPostExecute(ReminderEntity[] results) {
            delegate.reminderAsyncFinished(results);
        }

    }

    private static class AsyncInsertMedication extends AsyncTask<MedicationEntity, Void, Void>{
        private final DataAccessObject dao;

        public AsyncInsertMedication(DataAccessObject inputHandler){
            dao = inputHandler;
        }

        @Override
        protected Void doInBackground(MedicationEntity... meds){
            dao.insertMedication(meds[0]);
            return null;
        }

    }

    private static class AsyncInsertReminder extends AsyncTask<ReminderEntity, Void, Void>{
        private final DataAccessObject dao;

        public AsyncInsertReminder(DataAccessObject inputHandler){
            dao = inputHandler;
        }

        @Override
        protected Void doInBackground(ReminderEntity... reminders){
            dao.insertReminder(reminders[0]);
            return null;
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

    private static class AsyncAddReminderID extends AsyncTask<String, Void, Void>{
        private final DataAccessObject dao;
        private final MedicationEntity m;

        public AsyncAddReminderID(DataAccessObject d, MedicationEntity medEntity){
            dao = d;
            m = medEntity;
        }

        @Override
        protected Void doInBackground(String... params){
            dao.addReminderID(m.getPrimaryKey(), Integer.parseInt(params[0]));
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
