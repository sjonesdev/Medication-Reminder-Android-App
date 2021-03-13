package com.example.medication_reminder_android_app.SQLiteDB;

/*
Data Access Object (DAO) for all entities
 */

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

/**
 * @author Hayley Roberts
 * @lastModified 3/6/2021 by Karley Waguespack
 */

@Dao
public interface DataAccessObject {

    //Queries on MedicationEntity
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long insertMedication(MedicationEntity medication);

    //selects any medications that have the inputted tag
    //this method should be called successively for each tag the user inputs
    //TODO see how to make sure the parameter is being used, not the string ":tag"
    @Query("SELECT * FROM MedicationTable WHERE :likeTags")
    public MedicationEntity[] loadFilteredMedications(String likeTags);

    //get a medication by name
    @Query("SELECT * FROM MedicationTable WHERE med_name LIKE :name")
    public MedicationEntity getMedicationByName(String name);

    //get a medication by id
    @Query("SELECT * FROM MedicationTable WHERE primaryKey LIKE :medId")
    public MedicationEntity getMedicationById(Integer medId);

    @Delete
    public int deleteMedication(MedicationEntity medication);

    @Query("DELETE FROM MedicationTable")
    public void clearAllMedications();





    //Queries on ReminderEntity
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long insertReminder(ReminderEntity reminder);

    //TODO complete in repository
    @Query("SELECT * FROM ReminderTable")
    public ReminderEntity[] loadAllReminders();

    //get a reminder
    @Query("SELECT * FROM ReminderTable WHERE rowid LIKE :primaryKey")
    public ReminderEntity getReminder(int primaryKey);

    //in-app and out-of-app notifications need diff number of reminders
    //LiveData here because we want the UI to update when there are new reminders.
    @Query("SELECT * FROM ReminderTable ORDER BY ApptDate, ApptTime LIMIT :numberOfReminders")
    public ReminderEntity[] selectNextReminders(int numberOfReminders);

    @Delete
    public int deleteReminder(ReminderEntity reminder);

    @Query("DELETE FROM ReminderTable")
    public void clearAllReminders();







    //TODO appt and dr queries
    //Queries on AppointmentEntity
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long insertAppointment(AppointmentEntity appointment);

    @Delete
    public int deleteAppointment(AppointmentEntity appointment);

    //Queries on DoctorEntity
}
