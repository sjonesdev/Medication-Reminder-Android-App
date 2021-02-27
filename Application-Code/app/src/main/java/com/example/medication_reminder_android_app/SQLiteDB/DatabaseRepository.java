package com.example.medication_reminder_android_app.SQLiteDB;

import android.app.Application;

public class DatabaseRepository {  //extends

    private FilterDAO filterDAO;
    private NotificationDAO notificationDAO;
    private InputHandlerDAO inputHandlerDAO;
    private AppDatabase db;

    public DatabaseRepository(Application application){
        db = AppDatabase.getDatabase(application);
        //filterDAO = db.filterDao();
        //notificationDAO = db.notificationDAO();
        //inputHandlerDAO = db.inputHandlerDAO();
    }
}
