package com.example.medication_reminder_android_app.SQLiteDB;

import androidx.room.Entity;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

@Entity
public class AppointmentEntity{
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "Appointment Location")
    private String location; //Location of the appointment

    @ColumnInfo(name = "Tags")
    private String tags; //Categorizes the type of appointment

    @ColumnInfo(name = "Notes")
    private String notes; //Any notes that the user wants to add to the appointment information

    @ColumnInfo(name = "TypeOfAppt")
    private String typeOfAppt; //Classifies specifically what the appointment will consist of if not Doctor (i.e MRI, blood donation, etc.)
    
    @ColumnInfo(name = "RemindTableID")
    private int remindTabID = new RemindersRow().getReminderTableID();

    @ColumnInfo(name = "DoctorTableID")
    private int docTabID = new DoctorTable().getDoctorTableID();

    public AppointmentEntity(String Location, String Tags, String Notes, String Type){
       this.location = Location;
       this.tags = Tags;
       this.notes = Notes;
       this.typeOfAppt = Type;
    }

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

    public int getApptTableID(){
        return this.id;           //Returns the primary key of the specific Appointment entity
    }

    
}


