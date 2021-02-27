package com.example.medication_reminder_android_app.SQLiteDB;

import androidx.room.Entity;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

//doctor name
//doctor phone number

@Entity
/**
@author Karley Waguespack
@lastModified 2/27/2021 by Aliza Siddiqui
*/
public class DoctorEntity {

    public DoctorEntity(String drName, String phone, String officeLoc, String notes, String tags, String officeHrs, String hospitalName, int aptID){

        this.drName = drName;
        this.phone = phone;
        this.officeLoc = officeLoc;
        this.notes = notes;
        this.tags = tags;
        this.officeHrs = officeHrs;
        this.hospitalName = hospitalName;
        this.aptID = aptID;

    }

    public DoctorEntity(){
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


    //Getters
    public String getDoctorName(){    
        return this.drName;
    }

    public String getOffice(){
        return this.officeLoc;
    }

    public String getNotes(){
        return this.notes;
    }

    public String getTags(){
        return this.tags;
    }

    public String getOfficeHours(){
        return this.officeHrs;
    }

    public String getHospitalName(){
        return this.hospitalName;
    }
    
    public int getDoctorTableID(){
        return this.docID;                   //Returns the primary key of the specific Doctor Information entity
    }

    //setters

    public void setDoctorName(String Name){    
        this.drName = Name;
    }

    public void setOffice(String Location){
        this.officeLoc = Location;
    }

    public void setNotes(String Notes){
        this.notes = Notes;
    }

    public void setTags(String Tags){
        this.tags = Tags;
    }

    public void setOfficeHours(String Hours){
        this.officeHrs = Hours;
    }

    public void setHospitalName(String Hospital){
        this.hospitalName = Hospital;
    }
    
    




}
