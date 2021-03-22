package com.example.medication_reminder_android_app.SQLiteDB;

import androidx.room.Entity;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;


/**
@author Aliza Siddiqui
 @lastModified 3/22/2021 by Hayley Roberts
 //Interval Array
 //Keeping track of the next index; will be given to you
*/

@Entity(tableName = "ReminderTable")
public class ReminderEntity{
    @PrimaryKey(autoGenerate = true)
    private long primaryKey;

    //This value is going to be a character so M for medication and A for appointment
    @ColumnInfo(name = "Classification")
    private String classification; // This categorizes the type of reminder (Medication or Appointment)

    @ColumnInfo(name = "ApptTime")
    private String time; //Format: HH:MM:SS

    @ColumnInfo(name= "ApptDate")
    private String date; //Format: YYYY-MM-DD

    @ColumnInfo(name = "MedApptID")
    private long medApptId; // Will help Reminders Table connect with Medication Table (if Medication Reminder) and
                                // Appointment Table (if Appointment Reminder)
    @ColumnInfo(name = "TimeInterval")
    private Integer timeIntervalIndex; //Will keep track of which timeInterval in the timeRule to use.
    
    public ReminderEntity(String classification, String time, String date, Integer timeIntervalIndex, long medApptId){
        this.classification = classification;
        this.time = time;
        this.date = date;
        this.timeIntervalIndex = timeIntervalIndex;
        this.medApptId = medApptId;
    }


    //getters
    public String getClassification(){
        return this.classification;
    }

    public String getTime(){
        return this.time;
    }

    public String getDate(){
        return this.date;
    }

    public long getPrimaryKey() { return this.primaryKey; }

    public long getMedApptId() { return this.medApptId; }

    public Integer getTimeIntervalIndex() { return this.timeIntervalIndex; }

    //setters
    public void setPrimaryKey(long primaryKey) { this.primaryKey = primaryKey; }

    public void setClassification(String classification) { this.classification = classification; }

    public void setTime(String time) { this.time = time; }

    public void setDate(String Date) { this.date = date; }

    public void setMedApptId(long medApptId) { this.medApptId = medApptId; }

    public void setTimeIntervalIndex(Integer timeIntervalIndex) { this.timeIntervalIndex = timeIntervalIndex; }


}
