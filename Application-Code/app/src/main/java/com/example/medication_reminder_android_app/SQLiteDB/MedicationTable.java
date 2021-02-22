package com.example.medication_reminder_android_app.SQLiteDB;

import androidx.room.Entity;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

@Entity
public class MedicationTable {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "med_name")
    public String medName;

    @ColumnInfo(name = "warnings")
    public String warnings; //this is a comma delimited list of health warnings
}
