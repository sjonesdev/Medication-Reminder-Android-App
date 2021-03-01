package com.example.medication_reminder_android_app.SQLiteDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

/*
Filter Data Access Object.
This is the class for the Filter component to access the SQLite DB
 */


public interface FilterDAO {

    //selects any medications that have the inputted tag
    //this method should be called successively for each tag the user inputs
    //TODO see how to make sure the parameter is being used, not the string ":tag"
    @Query("SELECT * FROM MedicationEntity WHERE tags LIKE '%:tag%'")
    public MedicationEntity[] loadFilteredMedications(String tag);

}
