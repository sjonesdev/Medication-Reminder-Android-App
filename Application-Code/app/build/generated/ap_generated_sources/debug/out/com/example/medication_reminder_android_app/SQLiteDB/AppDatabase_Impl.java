package com.example.medication_reminder_android_app.SQLiteDB;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile DataAccessObject _dataAccessObject;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `MedicationTable` (`primaryKey` INTEGER PRIMARY KEY AUTOINCREMENT, `med_name` TEXT, `dosage` TEXT, `recurring` INTEGER, `time_rule` TEXT, `reminder_id` INTEGER, `acknowledgements` TEXT, `warnings` TEXT, `ingredients` TEXT, `tags` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `DoctorTable` (`primaryKey` INTEGER PRIMARY KEY AUTOINCREMENT, `doctor name` TEXT, `phone` TEXT, `office location` TEXT, `notes` TEXT, `tags` TEXT, `hours` TEXT, `hospital name` TEXT, `appointment id` INTEGER)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `AppointmentTable` (`primaryKey` INTEGER, `Appointment Location` TEXT, `Tags` TEXT, `Notes` TEXT, `TypeOfAppt` TEXT, `RemindTableID` INTEGER, `DoctorTableID` INTEGER, PRIMARY KEY(`primaryKey`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `ReminderTable` (`primaryKey` INTEGER PRIMARY KEY AUTOINCREMENT, `Classification` TEXT, `ApptTime` TEXT, `ApptDate` TEXT, `MedApptID` INTEGER, `TimeInterval` INTEGER)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '6937977007928a1a3be5204291f54980')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `MedicationTable`");
        _db.execSQL("DROP TABLE IF EXISTS `DoctorTable`");
        _db.execSQL("DROP TABLE IF EXISTS `AppointmentTable`");
        _db.execSQL("DROP TABLE IF EXISTS `ReminderTable`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsMedicationTable = new HashMap<String, TableInfo.Column>(10);
        _columnsMedicationTable.put("primaryKey", new TableInfo.Column("primaryKey", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMedicationTable.put("med_name", new TableInfo.Column("med_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMedicationTable.put("dosage", new TableInfo.Column("dosage", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMedicationTable.put("recurring", new TableInfo.Column("recurring", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMedicationTable.put("time_rule", new TableInfo.Column("time_rule", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMedicationTable.put("reminder_id", new TableInfo.Column("reminder_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMedicationTable.put("acknowledgements", new TableInfo.Column("acknowledgements", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMedicationTable.put("warnings", new TableInfo.Column("warnings", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMedicationTable.put("ingredients", new TableInfo.Column("ingredients", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMedicationTable.put("tags", new TableInfo.Column("tags", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMedicationTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMedicationTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMedicationTable = new TableInfo("MedicationTable", _columnsMedicationTable, _foreignKeysMedicationTable, _indicesMedicationTable);
        final TableInfo _existingMedicationTable = TableInfo.read(_db, "MedicationTable");
        if (! _infoMedicationTable.equals(_existingMedicationTable)) {
          return new RoomOpenHelper.ValidationResult(false, "MedicationTable(com.example.medication_reminder_android_app.SQLiteDB.MedicationEntity).\n"
                  + " Expected:\n" + _infoMedicationTable + "\n"
                  + " Found:\n" + _existingMedicationTable);
        }
        final HashMap<String, TableInfo.Column> _columnsDoctorTable = new HashMap<String, TableInfo.Column>(9);
        _columnsDoctorTable.put("primaryKey", new TableInfo.Column("primaryKey", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDoctorTable.put("doctor name", new TableInfo.Column("doctor name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDoctorTable.put("phone", new TableInfo.Column("phone", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDoctorTable.put("office location", new TableInfo.Column("office location", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDoctorTable.put("notes", new TableInfo.Column("notes", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDoctorTable.put("tags", new TableInfo.Column("tags", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDoctorTable.put("hours", new TableInfo.Column("hours", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDoctorTable.put("hospital name", new TableInfo.Column("hospital name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDoctorTable.put("appointment id", new TableInfo.Column("appointment id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDoctorTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDoctorTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDoctorTable = new TableInfo("DoctorTable", _columnsDoctorTable, _foreignKeysDoctorTable, _indicesDoctorTable);
        final TableInfo _existingDoctorTable = TableInfo.read(_db, "DoctorTable");
        if (! _infoDoctorTable.equals(_existingDoctorTable)) {
          return new RoomOpenHelper.ValidationResult(false, "DoctorTable(com.example.medication_reminder_android_app.SQLiteDB.DoctorEntity).\n"
                  + " Expected:\n" + _infoDoctorTable + "\n"
                  + " Found:\n" + _existingDoctorTable);
        }
        final HashMap<String, TableInfo.Column> _columnsAppointmentTable = new HashMap<String, TableInfo.Column>(7);
        _columnsAppointmentTable.put("primaryKey", new TableInfo.Column("primaryKey", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppointmentTable.put("Appointment Location", new TableInfo.Column("Appointment Location", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppointmentTable.put("Tags", new TableInfo.Column("Tags", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppointmentTable.put("Notes", new TableInfo.Column("Notes", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppointmentTable.put("TypeOfAppt", new TableInfo.Column("TypeOfAppt", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppointmentTable.put("RemindTableID", new TableInfo.Column("RemindTableID", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppointmentTable.put("DoctorTableID", new TableInfo.Column("DoctorTableID", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAppointmentTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAppointmentTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAppointmentTable = new TableInfo("AppointmentTable", _columnsAppointmentTable, _foreignKeysAppointmentTable, _indicesAppointmentTable);
        final TableInfo _existingAppointmentTable = TableInfo.read(_db, "AppointmentTable");
        if (! _infoAppointmentTable.equals(_existingAppointmentTable)) {
          return new RoomOpenHelper.ValidationResult(false, "AppointmentTable(com.example.medication_reminder_android_app.SQLiteDB.AppointmentEntity).\n"
                  + " Expected:\n" + _infoAppointmentTable + "\n"
                  + " Found:\n" + _existingAppointmentTable);
        }
        final HashMap<String, TableInfo.Column> _columnsReminderTable = new HashMap<String, TableInfo.Column>(6);
        _columnsReminderTable.put("primaryKey", new TableInfo.Column("primaryKey", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminderTable.put("Classification", new TableInfo.Column("Classification", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminderTable.put("ApptTime", new TableInfo.Column("ApptTime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminderTable.put("ApptDate", new TableInfo.Column("ApptDate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminderTable.put("MedApptID", new TableInfo.Column("MedApptID", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminderTable.put("TimeInterval", new TableInfo.Column("TimeInterval", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysReminderTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesReminderTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoReminderTable = new TableInfo("ReminderTable", _columnsReminderTable, _foreignKeysReminderTable, _indicesReminderTable);
        final TableInfo _existingReminderTable = TableInfo.read(_db, "ReminderTable");
        if (! _infoReminderTable.equals(_existingReminderTable)) {
          return new RoomOpenHelper.ValidationResult(false, "ReminderTable(com.example.medication_reminder_android_app.SQLiteDB.ReminderEntity).\n"
                  + " Expected:\n" + _infoReminderTable + "\n"
                  + " Found:\n" + _existingReminderTable);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "6937977007928a1a3be5204291f54980", "d02f73195637e03e606d4479e72ccf99");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "MedicationTable","DoctorTable","AppointmentTable","ReminderTable");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `MedicationTable`");
      _db.execSQL("DELETE FROM `DoctorTable`");
      _db.execSQL("DELETE FROM `AppointmentTable`");
      _db.execSQL("DELETE FROM `ReminderTable`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public DataAccessObject dataAccessObject() {
    if (_dataAccessObject != null) {
      return _dataAccessObject;
    } else {
      synchronized(this) {
        if(_dataAccessObject == null) {
          _dataAccessObject = new DataAccessObject_Impl(this);
        }
        return _dataAccessObject;
      }
    }
  }
}
