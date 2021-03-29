package com.example.medication_reminder_android_app.SQLiteDB;

/*
Data Access Object (DAO) for all entities
 */

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Single;

/**
 * @author Hayley Roberts
 * @lastModified 3/22/2021 by Hayley Roberts
 */

@Dao
public interface DataAccessObject {

    //Queries on MedicationEntity

    /**
     * @author Hayley Roberts
     * @param medication
     * @return long: primary key of the medication
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long insertMedication(MedicationEntity medication);

    /**
     * @author Hayley Roberts
     * selecs any medications that have the inputted tag
     * @param likeTags
     * @return
     */
    @Query("SELECT * FROM MedicationTable WHERE :likeTags")
    public LiveData<List<MedicationEntity>> loadFilteredMedications(String likeTags);

    /**
     * @auhor Hayley Roberts
     * @return LiveData<List<MedicationEntity>> to send to UI
     */
    @Query("SELECT * FROM MedicationTable")
    public LiveData<List<MedicationEntity>> getAllMeds();

    /**
     * @author Hayley Roberts
     * Get medication by Name
     * @param name
     * @return Single<MedicationEntity>
     */
    @Query("SELECT * FROM MedicationTable WHERE med_name LIKE :name")
    public Single<MedicationEntity> getMedicationByName(String name);

    /**
     * @author Hayley Roberts
     * @param pk
     * @return Single<MedicationEntity>
     */
    @Query("SELECT * FROM MedicationTable WHERE primaryKey LIKE :pk")
    public Single<MedicationEntity> getMedicationById(long pk);

    /**
     * @author Hayley Roberts
     * @param pk
     * @param a
     */
    @Query("UPDATE MedicationTable SET acknowledgements = :a WHERE primarykey LIKE :pk")
    public void updateAcknowledgements(long pk, String a);

    /**
     * @author Hayley Roberts
     * @param medPK
     * @param reminderPK
     */
    @Query("UPDATE MedicationTable SET reminder_id = :reminderPK WHERE primarykey LIKE :medPK")
    public void addReminderID(long medPK, long reminderPK);

    //Deletions

    /**
     * @author Hayley Roberts
     * @param medication
     * @return
     */
    @Delete
    public int deleteMedication(MedicationEntity medication);


    /**
     * @author Hayley Roberts
     */
    @Query("DELETE FROM MedicationTable")
    public void clearAllMedications();

    /**
     * @author Hayley Roberts
     * @param medName
     */
    @Query("DELETE FROM MedicationTable WHERE med_name LIKE :medName")
    public void deleteMedicationByName(String medName);





    //Queries on ReminderEntity

    /**
     * @author Hayley Roberts
     * @param reminder
     * @return
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long insertReminder(ReminderEntity reminder);

    /**
     * @author Hayley Roberts
     * @return
     */
    //TODO complete in Repository
    @Query("SELECT * FROM ReminderTable")
    public Single<ReminderEntity[]> loadAllReminders();

    //get a reminder

    /**
     * @author Hayley Roberts
     * @param primaryKey
     * @return
     */
    @Query("SELECT * FROM ReminderTable WHERE rowid LIKE :primaryKey")
    public Single<ReminderEntity> getReminder(long primaryKey);

    /**
     * @author Hayley Roberts
     * in-app and out-of-app notifs need diff num of reminders
     * @param numberOfReminders
     * @return
     */
    @Query("SELECT * FROM ReminderTable ORDER BY ApptDate, ApptTime LIMIT :numberOfReminders")
    public Single<ReminderEntity[]> selectNextReminders(int numberOfReminders);

    /**
     * @author Hayley Roberts
     * @param primaryKey
     * @param date
     * @param time
     * @param timeInterval
     */
    @Query("UPDATE ReminderTable SET ApptDate = :date, ApptTime = :time, TimeInterval = :timeInterval WHERE rowid LIKE :primaryKey")
    public void updateDateAndTime(long primaryKey, String date, String time, int timeInterval);

    /**
     * @author Hayley Roberts
     * @param reminder
     * @return
     */
    @Delete
    public int deleteReminder(ReminderEntity reminder);

    /**
     * @author Hayley Roberts
     * @param pk
     */
    @Query("DELETE FROM ReminderTable WHERE primaryKey LIKE :pk")
    public void deleteReminderById(long pk);

    /**
     * @author Hayley Roberts
     * @return
     */
    @Query("DELETE FROM ReminderTable WHERE Classification like 'M'")
    public int deleteAllMedicationReminders();

    /**
     * @author Hayley Roberts
     * @return
     */
    @Query("DELETE FROM ReminderTable WHERE Classification like 'A'")
    public int deleteAllAppointmentReminders();

    /**
     * @author Hayley Roberts
     */
    @Query("DELETE FROM ReminderTable")
    public void clearAllReminders();







    //TODO appt and dr queries
    //Queries on AppointmentEntity

    /**
     * @author Hayley Roberts
     * @param appointment
     * @return
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long insertAppointment(AppointmentEntity appointment);

    /**
     * @author Hayley Roberts
     * @param appointment
     * @return
     */
    @Delete
    public int deleteAppointment(AppointmentEntity appointment);

    //Queries on DoctorEntity
}
