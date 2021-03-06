package com.example.medication_reminder_android_app.SQLiteDB;

import androidx.room.Entity;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

/**
  @author Aliza Siddiqui
 @lastModified 3/5/2021 by Hayley Roberts
*/

@Entity(tableName = "AppointmentTable")
public class AppointmentEntity{
    @PrimaryKey
    public Integer primaryKey;

    @ColumnInfo(name = "Appointment Location")
    private String location; //Location of the appointment

    @ColumnInfo(name = "Tags")
    private String tags; //Categorizes the type of appointment

    @ColumnInfo(name = "Notes")
    private String notes; //Any notes that the user wants to add to the appointment information

    @ColumnInfo(name = "TypeOfAppt")
    private String typeOfAppt; //Classifies specifically what the appointment will consist of if not Doctor (i.e MRI, blood donation, etc.)
    
    @ColumnInfo(name = "RemindTableID")
    private Integer remindTabID;

    @ColumnInfo(name = "DoctorTableID")
    private Integer docTabID;

    public AppointmentEntity(String location, String tags, String notes, String typeOfAppt, Integer remindTabID, Integer docTabID){
       this.location = location;
       this.tags = tags;
       this.notes = notes;
       this.typeOfAppt = typeOfAppt;
       this.remindTabID = remindTabID;
       this.docTabID = docTabID;
    }

    //Getters
    public String getLocation(){
        return this.location;
    }

    public String getTags(){
        return this.tags;
    }

    public String getNotes(){
        return this.notes;
    }

    public String getTypeOfAppt(){
        return this.typeOfAppt;
    }

    public Integer getRemindTabID() { return this.remindTabID; }

    public Integer getDocTabID() { return this.docTabID; }

    public Integer getPrimaryKey(){
        return this.primaryKey;           //Returns the primary key of the specific Appointment entity
    }

    //Setters
    public void setLocation(String Location){
        this.location = Location;
    }

    public void setTags(String Tags){
       this.tags = Tags;
    }

    public void setNotes(String Notes){
        this.notes = Notes;
    }

    public void setTypeOfAppt(String ApptType){
        this.typeOfAppt = ApptType;
    }

    public void setRemindTabID(Integer remindTabID) { this.remindTabID = remindTabID; }

    public void setDocTabID(Integer docTabID) { this.docTabID = docTabID; }

}


