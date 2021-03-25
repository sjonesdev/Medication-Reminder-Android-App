package com.example.medication_reminder_android_app.SQLiteDB;

import androidx.room.Entity;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

/**
 * @author Hayley Roberts
 * @lastModified 3/22/2021 by Hayley Roberts
 */

@Entity(tableName = "MedicationTable")
public class MedicationEntity {
    //Variables and columns
    @PrimaryKey (autoGenerate = true)
    private long primaryKey;

    @ColumnInfo (name = "med_name")
    private String medName;

    @ColumnInfo (name = "dosage")
    private String dosage;

    @ColumnInfo (name = "recurring")
    private Integer recurring; //technically boolean but sqlite doesnt support

    @ColumnInfo (name = "first_date")
    private String firstDate;

    @ColumnInfo (name = "end_date")
    private String endDate;

    @ColumnInfo (name = "time_rule")
    private String timeRule;

    @ColumnInfo (name  = "reminder_id")
    private long reminderID;

    @ColumnInfo (name = "acknowledgements")
    private String acknowledgements;

    @ColumnInfo (name = "warnings")
    private String warnings; //this is a comma delimited list of health warnings

    @ColumnInfo (name = "ingredients")
    private String ingredients;

    @ColumnInfo (name = "tags")
    private String tags;

    //Constructor
    //TODO start date and end date,
    public MedicationEntity(String medName, String dosage, Integer recurring, String firstDate, String endDate,
                            String timeRule, long reminderID, String acknowledgements, String warnings,
                            String ingredients, String tags){
        this.medName = medName;
        this.dosage = dosage;
        this.recurring = recurring;
        this.firstDate = firstDate;
        this.endDate = endDate;
        this.timeRule = timeRule;
        this.reminderID = reminderID;
        this.acknowledgements = acknowledgements;
        this.warnings = warnings;
        this.ingredients = ingredients;
        this.tags = tags;
    }

    //Getter Methods
    public long getPrimaryKey(){
        return this.primaryKey;
    }

    public String getMedName(){
        return this.medName;
    }

    public String getDosage(){
        return this.dosage;
    }

    public Integer getRecurring(){
        return this.recurring;
    }

    public String getFirstDate() { return this.firstDate; }

    public String getEndDate() { return this.endDate; }

    public String getTimeRule(){
        return this.timeRule;
    }

    public long getReminderID(){
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
    public void setPrimaryKey(long primaryKey){ this.primaryKey = primaryKey; }

    public void setMedName(String medicationName){
        this.medName = medicationName;
    }

    public void setDosage(String inputDosage){
        this.dosage = inputDosage;
    }

    public void setRecurring(Integer ifRecurring){
        this.recurring = ifRecurring;
    }

    public void setFirstDate(String firstDate) { this.firstDate = firstDate; }

    public void setEndDate(String endDate) { this.endDate = endDate; }

    public void setTimeRule(String recurringRule){
        this.timeRule = recurringRule;
    }

    public void setReminderID(long reminderId){
        this.reminderID = reminderId;
    }

    public void setAcknowledgements(String acknowledgement){ this.acknowledgements = acknowledgement; }

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
