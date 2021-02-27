package com.example.medication_reminder_android_app.SQLiteDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.Room;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {MedicationEntity.class, DoctorEntity.class, AppointmentTable.class}, version = 1)
public class AppDatabase extends SQLiteOpenHelper { //RoomDatabase {
    //Database Info
    private static final String DATABASE_NAME = "user_database";
    private static final int  DATABASE_VERSION = 1;

    public AppDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
