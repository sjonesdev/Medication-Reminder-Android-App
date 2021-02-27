package com.example.medication_reminder_android_app.SQLiteDB;

import android.os.AsyncTask;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.Room;
import androidx.sqlite.db.SupportSQLiteOpenHelper;


@Database(entities = {MedicationEntity.class, DoctorEntity.class, AppointmentTable.class}, version = 1)
public class AppDatabase extends RoomDatabase {
    //TODO remove the comments from the DAO's below
    //public abstract FilterDAO filterDAO();
    //public abstract InputHandlerDAO inputHandlerDAO();
    //public abstract NotificationDAO notificationDAO();

    private  static AppDatabase INSTANCE;

    static AppDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (AppDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database").build();
                }
            }
        }
        return INSTANCE;
    }


    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}
