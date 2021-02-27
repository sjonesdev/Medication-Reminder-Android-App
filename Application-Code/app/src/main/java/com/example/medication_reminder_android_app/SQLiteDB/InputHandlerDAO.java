package com.example.medication_reminder_android_app.SQLiteDB;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

/*
InputHandler Data Access Object.
This is the class for the InputHandler component to access the SQLite DB
 */

public interface InputHandlerDAO {

    //these methods only insert one item at a time; if we wanted the user to insert multiple items
    //at once, we could change these

    //insert single item
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long insertMedication(MedicationEntity medication);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long insertAppointment(AppointmentEntity appointment);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long insertReminder(/*ReminderEntity reminder*/);

    //delete single item
    @Delete
    public int deleteMedication(MedicationEntity medication);
    public int deleteAppointment(AppointmentEntity appointment);
    public int deleteReminder(ReminderEntity reminder);

    //delete all methods
    @Query("DELETE * FROM MedicationEntity")
    public void clearAllMedications();
    @Query("DELETE * FROM ReminderEntity")
    public void clearAllReminders();

    //sort all reminders by time









}
