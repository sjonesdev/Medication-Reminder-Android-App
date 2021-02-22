package com.example.medication_reminder_android_app.SQLiteDB;

import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {MedicationTable.class, DoctorTable.class, AppointmentTable.class, RemindersTable.class}, version = 1)
public abstract class AppDatabase extends RoomDataBase{


}
