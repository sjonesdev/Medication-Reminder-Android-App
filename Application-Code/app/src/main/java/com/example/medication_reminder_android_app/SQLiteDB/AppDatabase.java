package com.example.medication_reminder_android_app.SQLiteDB;

import androidx.room.Database;

@Database(entities = {MedicationEntity.class, DoctorTable.class, AppointmentTable.class, RemindersTable.class}, version = 1)
public abstract class AppDatabase extends RoomDataBase{


}
