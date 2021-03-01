package com.example.medication_reminder_android_app.SQLiteDB;

import android.app.Application;
import android.os.AsyncTask;

public class DatabaseRepository {  //extends

    private FilterDAO filterDAO;
    private NotificationDAO notificationDAO;
    private InputHandlerDAO inputHandlerDAO;
    private AppDatabase db;

    public DatabaseRepository(Application application){
        db = AppDatabase.getDatabase(application);
        filterDAO = db.filterDAO();
        notificationDAO = db.notificationDAO();
        inputHandlerDAO = db.inputHandlerDAO();
    }

    //Filter Methods DAO







    //TODO
    //Async classes so can do queries in background
    private class AsyncFilter extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }
    }
}
