package com.example.medication_reminder_android_app.SQLiteDB;

import android.database.Cursor;
import androidx.room.EmptyResultSetException;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.RxRoom;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import io.reactivex.Single;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class DataAccessObject_Impl implements DataAccessObject {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<MedicationEntity> __insertionAdapterOfMedicationEntity;

  private final EntityInsertionAdapter<ReminderEntity> __insertionAdapterOfReminderEntity;

  private final EntityInsertionAdapter<AppointmentEntity> __insertionAdapterOfAppointmentEntity;

  private final EntityDeletionOrUpdateAdapter<MedicationEntity> __deletionAdapterOfMedicationEntity;

  private final EntityDeletionOrUpdateAdapter<ReminderEntity> __deletionAdapterOfReminderEntity;

  private final EntityDeletionOrUpdateAdapter<AppointmentEntity> __deletionAdapterOfAppointmentEntity;

  private final SharedSQLiteStatement __preparedStmtOfUpdateAcknowledgements;

  private final SharedSQLiteStatement __preparedStmtOfAddReminderID;

  private final SharedSQLiteStatement __preparedStmtOfClearAllMedications;

  private final SharedSQLiteStatement __preparedStmtOfDeleteMedicationByName;

  private final SharedSQLiteStatement __preparedStmtOfUpdateDateAndTime;

  private final SharedSQLiteStatement __preparedStmtOfDeleteReminderById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllMedicationReminders;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllAppointmentReminders;

  private final SharedSQLiteStatement __preparedStmtOfClearAllReminders;

  public DataAccessObject_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMedicationEntity = new EntityInsertionAdapter<MedicationEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `MedicationTable` (`primaryKey`,`med_name`,`dosage`,`recurring`,`first_date`,`end_date`,`time_rule`,`reminder_id`,`acknowledgements`,`warnings`,`ingredients`,`tags`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MedicationEntity value) {
        stmt.bindLong(1, value.getPrimaryKey());
        if (value.getMedName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getMedName());
        }
        if (value.getDosage() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDosage());
        }
        if (value.getRecurring() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, value.getRecurring());
        }
        if (value.getFirstDate() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getFirstDate());
        }
        if (value.getEndDate() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getEndDate());
        }
        if (value.getTimeRule() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getTimeRule());
        }
        stmt.bindLong(8, value.getReminderID());
        if (value.getAcknowledgements() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getAcknowledgements());
        }
        if (value.getWarnings() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getWarnings());
        }
        if (value.getIngredients() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getIngredients());
        }
        if (value.getTags() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getTags());
        }
      }
    };
    this.__insertionAdapterOfReminderEntity = new EntityInsertionAdapter<ReminderEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `ReminderTable` (`primaryKey`,`Classification`,`ApptTime`,`ApptDate`,`MedApptID`,`TimeInterval`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ReminderEntity value) {
        stmt.bindLong(1, value.getPrimaryKey());
        if (value.getClassification() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getClassification());
        }
        if (value.getTime() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getTime());
        }
        if (value.getDate() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDate());
        }
        stmt.bindLong(5, value.getMedApptId());
        if (value.getTimeIntervalIndex() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindLong(6, value.getTimeIntervalIndex());
        }
      }
    };
    this.__insertionAdapterOfAppointmentEntity = new EntityInsertionAdapter<AppointmentEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `AppointmentTable` (`primaryKey`,`Appointment Location`,`Tags`,`Notes`,`TypeOfAppt`,`RemindTableID`,`DoctorTableID`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, AppointmentEntity value) {
        stmt.bindLong(1, value.primaryKey);
        if (value.getLocation() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getLocation());
        }
        if (value.getTags() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getTags());
        }
        if (value.getNotes() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getNotes());
        }
        if (value.getTypeOfAppt() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getTypeOfAppt());
        }
        stmt.bindLong(6, value.getRemindTabID());
        stmt.bindLong(7, value.getDocTabID());
      }
    };
    this.__deletionAdapterOfMedicationEntity = new EntityDeletionOrUpdateAdapter<MedicationEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `MedicationTable` WHERE `primaryKey` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MedicationEntity value) {
        stmt.bindLong(1, value.getPrimaryKey());
      }
    };
    this.__deletionAdapterOfReminderEntity = new EntityDeletionOrUpdateAdapter<ReminderEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `ReminderTable` WHERE `primaryKey` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ReminderEntity value) {
        stmt.bindLong(1, value.getPrimaryKey());
      }
    };
    this.__deletionAdapterOfAppointmentEntity = new EntityDeletionOrUpdateAdapter<AppointmentEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `AppointmentTable` WHERE `primaryKey` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, AppointmentEntity value) {
        stmt.bindLong(1, value.primaryKey);
      }
    };
    this.__preparedStmtOfUpdateAcknowledgements = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE MedicationTable SET acknowledgements = ? WHERE primarykey LIKE ?";
        return _query;
      }
    };
    this.__preparedStmtOfAddReminderID = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE MedicationTable SET reminder_id = ? WHERE primarykey LIKE ?";
        return _query;
      }
    };
    this.__preparedStmtOfClearAllMedications = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM MedicationTable";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteMedicationByName = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM MedicationTable WHERE med_name LIKE ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateDateAndTime = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE ReminderTable SET ApptDate = ?, ApptTime = ?, TimeInterval = ? WHERE rowid LIKE ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteReminderById = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM ReminderTable WHERE primaryKey LIKE ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllMedicationReminders = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM ReminderTable WHERE Classification like 'M'";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllAppointmentReminders = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM ReminderTable WHERE Classification like 'A'";
        return _query;
      }
    };
    this.__preparedStmtOfClearAllReminders = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM ReminderTable";
        return _query;
      }
    };
  }

  @Override
  public long insertMedication(final MedicationEntity medication) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfMedicationEntity.insertAndReturnId(medication);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public long insertReminder(final ReminderEntity reminder) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfReminderEntity.insertAndReturnId(reminder);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public long insertAppointment(final AppointmentEntity appointment) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfAppointmentEntity.insertAndReturnId(appointment);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int deleteMedication(final MedicationEntity medication) {
    __db.assertNotSuspendingTransaction();
    int _total = 0;
    __db.beginTransaction();
    try {
      _total +=__deletionAdapterOfMedicationEntity.handle(medication);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int deleteReminder(final ReminderEntity reminder) {
    __db.assertNotSuspendingTransaction();
    int _total = 0;
    __db.beginTransaction();
    try {
      _total +=__deletionAdapterOfReminderEntity.handle(reminder);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int deleteAppointment(final AppointmentEntity appointment) {
    __db.assertNotSuspendingTransaction();
    int _total = 0;
    __db.beginTransaction();
    try {
      _total +=__deletionAdapterOfAppointmentEntity.handle(appointment);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateAcknowledgements(final long pk, final String a) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateAcknowledgements.acquire();
    int _argIndex = 1;
    if (a == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, a);
    }
    _argIndex = 2;
    _stmt.bindLong(_argIndex, pk);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateAcknowledgements.release(_stmt);
    }
  }

  @Override
  public void addReminderID(final long medPK, final long reminderPK) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfAddReminderID.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, reminderPK);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, medPK);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfAddReminderID.release(_stmt);
    }
  }

  @Override
  public void clearAllMedications() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfClearAllMedications.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfClearAllMedications.release(_stmt);
    }
  }

  @Override
  public void deleteMedicationByName(final String medName) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteMedicationByName.acquire();
    int _argIndex = 1;
    if (medName == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, medName);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteMedicationByName.release(_stmt);
    }
  }

  @Override
  public void updateDateAndTime(final long primaryKey, final String date, final String time,
      final int timeInterval) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateDateAndTime.acquire();
    int _argIndex = 1;
    if (date == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, date);
    }
    _argIndex = 2;
    if (time == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, time);
    }
    _argIndex = 3;
    _stmt.bindLong(_argIndex, timeInterval);
    _argIndex = 4;
    _stmt.bindLong(_argIndex, primaryKey);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateDateAndTime.release(_stmt);
    }
  }

  @Override
  public void deleteReminderById(final long pk) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteReminderById.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, pk);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteReminderById.release(_stmt);
    }
  }

  @Override
  public int deleteAllMedicationReminders() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllMedicationReminders.acquire();
    __db.beginTransaction();
    try {
      final int _result = _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllMedicationReminders.release(_stmt);
    }
  }

  @Override
  public int deleteAllAppointmentReminders() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllAppointmentReminders.acquire();
    __db.beginTransaction();
    try {
      final int _result = _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllAppointmentReminders.release(_stmt);
    }
  }

  @Override
  public void clearAllReminders() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfClearAllReminders.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfClearAllReminders.release(_stmt);
    }
  }

  @Override
  public Single<MedicationEntity[]> loadFilteredMedications(final String likeTags) {
    final String _sql = "SELECT * FROM MedicationTable WHERE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (likeTags == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, likeTags);
    }
    return RxRoom.createSingle(new Callable<MedicationEntity[]>() {
      @Override
      public MedicationEntity[] call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPrimaryKey = CursorUtil.getColumnIndexOrThrow(_cursor, "primaryKey");
          final int _cursorIndexOfMedName = CursorUtil.getColumnIndexOrThrow(_cursor, "med_name");
          final int _cursorIndexOfDosage = CursorUtil.getColumnIndexOrThrow(_cursor, "dosage");
          final int _cursorIndexOfRecurring = CursorUtil.getColumnIndexOrThrow(_cursor, "recurring");
          final int _cursorIndexOfFirstDate = CursorUtil.getColumnIndexOrThrow(_cursor, "first_date");
          final int _cursorIndexOfEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "end_date");
          final int _cursorIndexOfTimeRule = CursorUtil.getColumnIndexOrThrow(_cursor, "time_rule");
          final int _cursorIndexOfReminderID = CursorUtil.getColumnIndexOrThrow(_cursor, "reminder_id");
          final int _cursorIndexOfAcknowledgements = CursorUtil.getColumnIndexOrThrow(_cursor, "acknowledgements");
          final int _cursorIndexOfWarnings = CursorUtil.getColumnIndexOrThrow(_cursor, "warnings");
          final int _cursorIndexOfIngredients = CursorUtil.getColumnIndexOrThrow(_cursor, "ingredients");
          final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
          final MedicationEntity[] _result = new MedicationEntity[_cursor.getCount()];
          int _index = 0;
          while(_cursor.moveToNext()) {
            final MedicationEntity _item;
            final String _tmpMedName;
            _tmpMedName = _cursor.getString(_cursorIndexOfMedName);
            final String _tmpDosage;
            _tmpDosage = _cursor.getString(_cursorIndexOfDosage);
            final Integer _tmpRecurring;
            if (_cursor.isNull(_cursorIndexOfRecurring)) {
              _tmpRecurring = null;
            } else {
              _tmpRecurring = _cursor.getInt(_cursorIndexOfRecurring);
            }
            final String _tmpFirstDate;
            _tmpFirstDate = _cursor.getString(_cursorIndexOfFirstDate);
            final String _tmpEndDate;
            _tmpEndDate = _cursor.getString(_cursorIndexOfEndDate);
            final String _tmpTimeRule;
            _tmpTimeRule = _cursor.getString(_cursorIndexOfTimeRule);
            final long _tmpReminderID;
            _tmpReminderID = _cursor.getLong(_cursorIndexOfReminderID);
            final String _tmpAcknowledgements;
            _tmpAcknowledgements = _cursor.getString(_cursorIndexOfAcknowledgements);
            final String _tmpWarnings;
            _tmpWarnings = _cursor.getString(_cursorIndexOfWarnings);
            final String _tmpIngredients;
            _tmpIngredients = _cursor.getString(_cursorIndexOfIngredients);
            final String _tmpTags;
            _tmpTags = _cursor.getString(_cursorIndexOfTags);
            _item = new MedicationEntity(_tmpMedName,_tmpDosage,_tmpRecurring,_tmpFirstDate,_tmpEndDate,_tmpTimeRule,_tmpReminderID,_tmpAcknowledgements,_tmpWarnings,_tmpIngredients,_tmpTags);
            final long _tmpPrimaryKey;
            _tmpPrimaryKey = _cursor.getLong(_cursorIndexOfPrimaryKey);
            _item.setPrimaryKey(_tmpPrimaryKey);
            _result[_index] = _item;
            _index ++;
          }
          if(_result == null) {
            throw new EmptyResultSetException("Query returned empty result set: " + _statement.getSql());
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Single<MedicationEntity> getMedicationByName(final String name) {
    final String _sql = "SELECT * FROM MedicationTable WHERE med_name LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (name == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, name);
    }
    return RxRoom.createSingle(new Callable<MedicationEntity>() {
      @Override
      public MedicationEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPrimaryKey = CursorUtil.getColumnIndexOrThrow(_cursor, "primaryKey");
          final int _cursorIndexOfMedName = CursorUtil.getColumnIndexOrThrow(_cursor, "med_name");
          final int _cursorIndexOfDosage = CursorUtil.getColumnIndexOrThrow(_cursor, "dosage");
          final int _cursorIndexOfRecurring = CursorUtil.getColumnIndexOrThrow(_cursor, "recurring");
          final int _cursorIndexOfFirstDate = CursorUtil.getColumnIndexOrThrow(_cursor, "first_date");
          final int _cursorIndexOfEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "end_date");
          final int _cursorIndexOfTimeRule = CursorUtil.getColumnIndexOrThrow(_cursor, "time_rule");
          final int _cursorIndexOfReminderID = CursorUtil.getColumnIndexOrThrow(_cursor, "reminder_id");
          final int _cursorIndexOfAcknowledgements = CursorUtil.getColumnIndexOrThrow(_cursor, "acknowledgements");
          final int _cursorIndexOfWarnings = CursorUtil.getColumnIndexOrThrow(_cursor, "warnings");
          final int _cursorIndexOfIngredients = CursorUtil.getColumnIndexOrThrow(_cursor, "ingredients");
          final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
          final MedicationEntity _result;
          if(_cursor.moveToFirst()) {
            final String _tmpMedName;
            _tmpMedName = _cursor.getString(_cursorIndexOfMedName);
            final String _tmpDosage;
            _tmpDosage = _cursor.getString(_cursorIndexOfDosage);
            final Integer _tmpRecurring;
            if (_cursor.isNull(_cursorIndexOfRecurring)) {
              _tmpRecurring = null;
            } else {
              _tmpRecurring = _cursor.getInt(_cursorIndexOfRecurring);
            }
            final String _tmpFirstDate;
            _tmpFirstDate = _cursor.getString(_cursorIndexOfFirstDate);
            final String _tmpEndDate;
            _tmpEndDate = _cursor.getString(_cursorIndexOfEndDate);
            final String _tmpTimeRule;
            _tmpTimeRule = _cursor.getString(_cursorIndexOfTimeRule);
            final long _tmpReminderID;
            _tmpReminderID = _cursor.getLong(_cursorIndexOfReminderID);
            final String _tmpAcknowledgements;
            _tmpAcknowledgements = _cursor.getString(_cursorIndexOfAcknowledgements);
            final String _tmpWarnings;
            _tmpWarnings = _cursor.getString(_cursorIndexOfWarnings);
            final String _tmpIngredients;
            _tmpIngredients = _cursor.getString(_cursorIndexOfIngredients);
            final String _tmpTags;
            _tmpTags = _cursor.getString(_cursorIndexOfTags);
            _result = new MedicationEntity(_tmpMedName,_tmpDosage,_tmpRecurring,_tmpFirstDate,_tmpEndDate,_tmpTimeRule,_tmpReminderID,_tmpAcknowledgements,_tmpWarnings,_tmpIngredients,_tmpTags);
            final long _tmpPrimaryKey;
            _tmpPrimaryKey = _cursor.getLong(_cursorIndexOfPrimaryKey);
            _result.setPrimaryKey(_tmpPrimaryKey);
          } else {
            _result = null;
          }
          if(_result == null) {
            throw new EmptyResultSetException("Query returned empty result set: " + _statement.getSql());
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Single<MedicationEntity> getMedicationById(final long pk) {
    final String _sql = "SELECT * FROM MedicationTable WHERE primaryKey LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, pk);
    return RxRoom.createSingle(new Callable<MedicationEntity>() {
      @Override
      public MedicationEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPrimaryKey = CursorUtil.getColumnIndexOrThrow(_cursor, "primaryKey");
          final int _cursorIndexOfMedName = CursorUtil.getColumnIndexOrThrow(_cursor, "med_name");
          final int _cursorIndexOfDosage = CursorUtil.getColumnIndexOrThrow(_cursor, "dosage");
          final int _cursorIndexOfRecurring = CursorUtil.getColumnIndexOrThrow(_cursor, "recurring");
          final int _cursorIndexOfFirstDate = CursorUtil.getColumnIndexOrThrow(_cursor, "first_date");
          final int _cursorIndexOfEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "end_date");
          final int _cursorIndexOfTimeRule = CursorUtil.getColumnIndexOrThrow(_cursor, "time_rule");
          final int _cursorIndexOfReminderID = CursorUtil.getColumnIndexOrThrow(_cursor, "reminder_id");
          final int _cursorIndexOfAcknowledgements = CursorUtil.getColumnIndexOrThrow(_cursor, "acknowledgements");
          final int _cursorIndexOfWarnings = CursorUtil.getColumnIndexOrThrow(_cursor, "warnings");
          final int _cursorIndexOfIngredients = CursorUtil.getColumnIndexOrThrow(_cursor, "ingredients");
          final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
          final MedicationEntity _result;
          if(_cursor.moveToFirst()) {
            final String _tmpMedName;
            _tmpMedName = _cursor.getString(_cursorIndexOfMedName);
            final String _tmpDosage;
            _tmpDosage = _cursor.getString(_cursorIndexOfDosage);
            final Integer _tmpRecurring;
            if (_cursor.isNull(_cursorIndexOfRecurring)) {
              _tmpRecurring = null;
            } else {
              _tmpRecurring = _cursor.getInt(_cursorIndexOfRecurring);
            }
            final String _tmpFirstDate;
            _tmpFirstDate = _cursor.getString(_cursorIndexOfFirstDate);
            final String _tmpEndDate;
            _tmpEndDate = _cursor.getString(_cursorIndexOfEndDate);
            final String _tmpTimeRule;
            _tmpTimeRule = _cursor.getString(_cursorIndexOfTimeRule);
            final long _tmpReminderID;
            _tmpReminderID = _cursor.getLong(_cursorIndexOfReminderID);
            final String _tmpAcknowledgements;
            _tmpAcknowledgements = _cursor.getString(_cursorIndexOfAcknowledgements);
            final String _tmpWarnings;
            _tmpWarnings = _cursor.getString(_cursorIndexOfWarnings);
            final String _tmpIngredients;
            _tmpIngredients = _cursor.getString(_cursorIndexOfIngredients);
            final String _tmpTags;
            _tmpTags = _cursor.getString(_cursorIndexOfTags);
            _result = new MedicationEntity(_tmpMedName,_tmpDosage,_tmpRecurring,_tmpFirstDate,_tmpEndDate,_tmpTimeRule,_tmpReminderID,_tmpAcknowledgements,_tmpWarnings,_tmpIngredients,_tmpTags);
            final long _tmpPrimaryKey;
            _tmpPrimaryKey = _cursor.getLong(_cursorIndexOfPrimaryKey);
            _result.setPrimaryKey(_tmpPrimaryKey);
          } else {
            _result = null;
          }
          if(_result == null) {
            throw new EmptyResultSetException("Query returned empty result set: " + _statement.getSql());
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Single<ReminderEntity[]> loadAllReminders() {
    final String _sql = "SELECT * FROM ReminderTable";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return RxRoom.createSingle(new Callable<ReminderEntity[]>() {
      @Override
      public ReminderEntity[] call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPrimaryKey = CursorUtil.getColumnIndexOrThrow(_cursor, "primaryKey");
          final int _cursorIndexOfClassification = CursorUtil.getColumnIndexOrThrow(_cursor, "Classification");
          final int _cursorIndexOfTime = CursorUtil.getColumnIndexOrThrow(_cursor, "ApptTime");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "ApptDate");
          final int _cursorIndexOfMedApptId = CursorUtil.getColumnIndexOrThrow(_cursor, "MedApptID");
          final int _cursorIndexOfTimeIntervalIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "TimeInterval");
          final ReminderEntity[] _result = new ReminderEntity[_cursor.getCount()];
          int _index = 0;
          while(_cursor.moveToNext()) {
            final ReminderEntity _item;
            final String _tmpClassification;
            _tmpClassification = _cursor.getString(_cursorIndexOfClassification);
            final String _tmpTime;
            _tmpTime = _cursor.getString(_cursorIndexOfTime);
            final String _tmpDate;
            _tmpDate = _cursor.getString(_cursorIndexOfDate);
            final long _tmpMedApptId;
            _tmpMedApptId = _cursor.getLong(_cursorIndexOfMedApptId);
            final Integer _tmpTimeIntervalIndex;
            if (_cursor.isNull(_cursorIndexOfTimeIntervalIndex)) {
              _tmpTimeIntervalIndex = null;
            } else {
              _tmpTimeIntervalIndex = _cursor.getInt(_cursorIndexOfTimeIntervalIndex);
            }
            _item = new ReminderEntity(_tmpClassification,_tmpTime,_tmpDate,_tmpTimeIntervalIndex,_tmpMedApptId);
            final long _tmpPrimaryKey;
            _tmpPrimaryKey = _cursor.getLong(_cursorIndexOfPrimaryKey);
            _item.setPrimaryKey(_tmpPrimaryKey);
            _result[_index] = _item;
            _index ++;
          }
          if(_result == null) {
            throw new EmptyResultSetException("Query returned empty result set: " + _statement.getSql());
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Single<ReminderEntity> getReminder(final long primaryKey) {
    final String _sql = "SELECT * FROM ReminderTable WHERE rowid LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, primaryKey);
    return RxRoom.createSingle(new Callable<ReminderEntity>() {
      @Override
      public ReminderEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPrimaryKey = CursorUtil.getColumnIndexOrThrow(_cursor, "primaryKey");
          final int _cursorIndexOfClassification = CursorUtil.getColumnIndexOrThrow(_cursor, "Classification");
          final int _cursorIndexOfTime = CursorUtil.getColumnIndexOrThrow(_cursor, "ApptTime");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "ApptDate");
          final int _cursorIndexOfMedApptId = CursorUtil.getColumnIndexOrThrow(_cursor, "MedApptID");
          final int _cursorIndexOfTimeIntervalIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "TimeInterval");
          final ReminderEntity _result;
          if(_cursor.moveToFirst()) {
            final String _tmpClassification;
            _tmpClassification = _cursor.getString(_cursorIndexOfClassification);
            final String _tmpTime;
            _tmpTime = _cursor.getString(_cursorIndexOfTime);
            final String _tmpDate;
            _tmpDate = _cursor.getString(_cursorIndexOfDate);
            final long _tmpMedApptId;
            _tmpMedApptId = _cursor.getLong(_cursorIndexOfMedApptId);
            final Integer _tmpTimeIntervalIndex;
            if (_cursor.isNull(_cursorIndexOfTimeIntervalIndex)) {
              _tmpTimeIntervalIndex = null;
            } else {
              _tmpTimeIntervalIndex = _cursor.getInt(_cursorIndexOfTimeIntervalIndex);
            }
            _result = new ReminderEntity(_tmpClassification,_tmpTime,_tmpDate,_tmpTimeIntervalIndex,_tmpMedApptId);
            final long _tmpPrimaryKey;
            _tmpPrimaryKey = _cursor.getLong(_cursorIndexOfPrimaryKey);
            _result.setPrimaryKey(_tmpPrimaryKey);
          } else {
            _result = null;
          }
          if(_result == null) {
            throw new EmptyResultSetException("Query returned empty result set: " + _statement.getSql());
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Single<ReminderEntity[]> selectNextReminders(final int numberOfReminders) {
    final String _sql = "SELECT * FROM ReminderTable ORDER BY ApptDate, ApptTime LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, numberOfReminders);
    return RxRoom.createSingle(new Callable<ReminderEntity[]>() {
      @Override
      public ReminderEntity[] call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPrimaryKey = CursorUtil.getColumnIndexOrThrow(_cursor, "primaryKey");
          final int _cursorIndexOfClassification = CursorUtil.getColumnIndexOrThrow(_cursor, "Classification");
          final int _cursorIndexOfTime = CursorUtil.getColumnIndexOrThrow(_cursor, "ApptTime");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "ApptDate");
          final int _cursorIndexOfMedApptId = CursorUtil.getColumnIndexOrThrow(_cursor, "MedApptID");
          final int _cursorIndexOfTimeIntervalIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "TimeInterval");
          final ReminderEntity[] _result = new ReminderEntity[_cursor.getCount()];
          int _index = 0;
          while(_cursor.moveToNext()) {
            final ReminderEntity _item;
            final String _tmpClassification;
            _tmpClassification = _cursor.getString(_cursorIndexOfClassification);
            final String _tmpTime;
            _tmpTime = _cursor.getString(_cursorIndexOfTime);
            final String _tmpDate;
            _tmpDate = _cursor.getString(_cursorIndexOfDate);
            final long _tmpMedApptId;
            _tmpMedApptId = _cursor.getLong(_cursorIndexOfMedApptId);
            final Integer _tmpTimeIntervalIndex;
            if (_cursor.isNull(_cursorIndexOfTimeIntervalIndex)) {
              _tmpTimeIntervalIndex = null;
            } else {
              _tmpTimeIntervalIndex = _cursor.getInt(_cursorIndexOfTimeIntervalIndex);
            }
            _item = new ReminderEntity(_tmpClassification,_tmpTime,_tmpDate,_tmpTimeIntervalIndex,_tmpMedApptId);
            final long _tmpPrimaryKey;
            _tmpPrimaryKey = _cursor.getLong(_cursorIndexOfPrimaryKey);
            _item.setPrimaryKey(_tmpPrimaryKey);
            _result[_index] = _item;
            _index ++;
          }
          if(_result == null) {
            throw new EmptyResultSetException("Query returned empty result set: " + _statement.getSql());
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }
}
