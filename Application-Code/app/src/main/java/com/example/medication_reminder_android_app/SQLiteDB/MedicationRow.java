package com.example.medication_reminder_android_app.SQLiteDB;

import androidx.room.Entity;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

@Entity
public class MedicationRow {

    @PrimaryKey (autoGenerate = true)
    private int id;

    @ColumnInfo (name = "med_name")
    private String medName;

    @ColumnInfo (name = "dosage")
    private String dosage;

    @ColumnInfo (name = "recurring")
    private boolean recurring;

    @ColumnInfo (name = "time_rule")
    private String timeRule;

    @ColumnInfo (name  = "reminder_id")
    private int reminderID;

    @ColumnInfo (name = "acknowledgements")
    private String acknowledgements;

    @ColumnInfo (name = "warnings")
    private String warnings; //this is a comma delimited list of health warnings

    @ColumnInfo (name = "ingredients")
    private String ingredients;

    @ColumnInfo (name = "tags")
    private String tags;

    //constructor
    public MedicationRow(){
        //TODO
    }

    public int getId(){
        return this.id;
    }

    public String getMedName(){
        return this.medName;
    }

    public String getDosage(){
        return this.dosage;
    }

    public boolean isRecurring(){
        return this.recurring;
    }

    public String getTimeRule(){
        return this.timeRule;
    }

    public int getReminderID(){
        return this.reminderID;
    }

    public String getAcknowledgements() {
        return this.acknowledgements;
    }

    public String getWarnings(){
        return this.warnings;
    }

    public String getIngredients(){
        return this.ingredients;
    }

    public String getTags(){
        return this.tags;
    }
}
