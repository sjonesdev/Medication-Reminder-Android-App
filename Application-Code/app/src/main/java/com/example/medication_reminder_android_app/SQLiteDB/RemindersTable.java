package com.example.medication_reminder_android_app.SQLiteDB;

import androidx.room.Entity;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

@Entity
/**
@author Aliza Siddiqui
@lastModified 2/24/2021
*/
public class ReminderRows {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "Classification")
    private String classification; // This categorizes the type of reminder (Medication or Appointment)

    @ColumnInfo(name = "ApptTime")
    private String time; //Format: HH:MM:SS

    @ColumnInfo(name= "ApptDate")
    private String date; //Format: YYYY-MM-DD

    @ColumnInfo(autoGenerate = true)
    private int med_appt_ident; // Will help Reminders Table connect with Medication Table (if Medication Reminder) and
                                // Doctor Table (if Appointment Reminder)
    
    public ReminderRows(String Class, String Time, String Date){
        this.classification = Class;
        this.time = Time;
        this.date = Date;

    }

    public String getClassification(){
        return this.classification;
    }

    public int getTime(){
        return this.time;
    }

    public int getDate(){
        return this.date;
    }

}
