package com.example.medication_reminder_android_app.SQLiteDB;

import androidx.room.Entity;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

//doctor name
//doctor phone number

@Entity
public class DoctorEntity {

    public Doctor(String drName, String phone, String officeLoc, String notes, String tags, String officeHrs, String hospitalName, int aptID){

        this.drName = drName;
        this.phone = phone;
        this.officeLoc = officeLoc;
        this.notes = notes;
        this.tags = tags;
        this.officeHrs = officeHrs;
        this.hospitalName = hospitalName;
        this.aptID = aptID;

    }

    public Doctor(){
    } //default constructor


    //need to add getter and setter methods for each attribute


    @PrimaryKey(autoGenerate = true)
    private int docID;

    @ColumnInfo(name = "doctor name")
    private String drName;

    @ColumnInfo(name = "phone")
    private String phone;

    @ColumnInfo(name = "office location")
    private String officeLoc;

    @ColumnInfo(name = "notes")
    private String notes;

    @ColumnInfo(name = "tags")
    private String tags;

    @ColumnInfo(name = "hours")
    private String officeHrs;

    @ColumnInfo(name = "hospital name")
    private String hospitalName;

    @ColumnInfo(name = "appointment id")
    private int aptID;


}
