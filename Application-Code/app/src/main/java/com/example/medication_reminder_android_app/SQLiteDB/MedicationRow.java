package com.example.medication_reminder_android_app.SQLiteDB;

import androidx.room.Entity;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

@Entity
public class MedicationRow {
    //Variables and columns
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

    //Constructor
    public MedicationRow(String medicationName, String inputDosage, boolean ifRecurring, String inputTimeRule, int reminderId,
                         String inputAcknowledgements, String inputWarnings, String inputIngredients, String inputTags){
        this.medName = medicationName;
        this.dosage = inputDosage;
        this.recurring = ifRecurring;
        this.timeRule = inputTimeRule;
        this.reminderID = reminderId;
        this.acknowledgements = inputAcknowledgements;
        this.warnings = inputWarnings;
        this.ingredients = inputIngredients;
        this.tags = inputTags;
    }

    //Getter Methods
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


    //Setter Methods
    public void setId(int inputId){
        this.id = inputId;
    }

    public void setMedName(String medicationName){
        this.medName = medicationName;
    }

    public void setDosage(String inputDosage){
        this.dosage = inputDosage;
    }

    public void setRecurring(boolean ifRecurring){
        this.recurring = ifRecurring;
    }

    public void setTimeRule(String recurringRule){
        this.timeRule = recurringRule;
    }

    public void setReminderID(int reminderId){
        this.reminderID = reminderId;
    }

    public void setAcknowledgements(String acknowledgement){
        //TODO might need to handle the list of acknowledgements here
        this.acknowledgements = acknowledgement;
    }

    public void setWarnings(String inputWarnings){
        this.warnings = inputWarnings;
    }

    public void setIngredients(String inputIngredients){
        this.ingredients = inputIngredients;
    }

    public void setTags(String inputTags){
        this.tags = inputTags;
    }
}
