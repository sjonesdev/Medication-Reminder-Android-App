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
  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `MedicationTable` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `med_name` TEXT, `warnings` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `DoctorTable` (`id` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `AppointmentTable` (`id` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `RemindersTable` (`id` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '71740aaf023512f3f6cb2e9d88c724a9')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `MedicationTable`");
        _db.execSQL("DROP TABLE IF EXISTS `DoctorTable`");
        _db.execSQL("DROP TABLE IF EXISTS `AppointmentTable`");
        _db.execSQL("DROP TABLE IF EXISTS `RemindersTable`");
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
        final HashMap<String, TableInfo.Column> _columnsMedicationTable = new HashMap<String, TableInfo.Column>(3);
        _columnsMedicationTable.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMedicationTable.put("med_name", new TableInfo.Column("med_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMedicationTable.put("warnings", new TableInfo.Column("warnings", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMedicationTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMedicationTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMedicationTable = new TableInfo("MedicationTable", _columnsMedicationTable, _foreignKeysMedicationTable, _indicesMedicationTable);
        final TableInfo _existingMedicationTable = TableInfo.read(_db, "MedicationTable");
        if (! _infoMedicationTable.equals(_existingMedicationTable)) {
          return new RoomOpenHelper.ValidationResult(false, "MedicationTable(com.example.medication_reminder_android_app.SQLiteDB.MedicationTable).\n"
                  + " Expected:\n" + _infoMedicationTable + "\n"
                  + " Found:\n" + _existingMedicationTable);
        }
        final HashMap<String, TableInfo.Column> _columnsDoctorTable = new HashMap<String, TableInfo.Column>(1);
        _columnsDoctorTable.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDoctorTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDoctorTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDoctorTable = new TableInfo("DoctorTable", _columnsDoctorTable, _foreignKeysDoctorTable, _indicesDoctorTable);
        final TableInfo _existingDoctorTable = TableInfo.read(_db, "DoctorTable");
        if (! _infoDoctorTable.equals(_existingDoctorTable)) {
          return new RoomOpenHelper.ValidationResult(false, "DoctorTable(com.example.medication_reminder_android_app.SQLiteDB.DoctorTable).\n"
                  + " Expected:\n" + _infoDoctorTable + "\n"
                  + " Found:\n" + _existingDoctorTable);
        }
        final HashMap<String, TableInfo.Column> _columnsAppointmentTable = new HashMap<String, TableInfo.Column>(1);
        _columnsAppointmentTable.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAppointmentTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAppointmentTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAppointmentTable = new TableInfo("AppointmentTable", _columnsAppointmentTable, _foreignKeysAppointmentTable, _indicesAppointmentTable);
        final TableInfo _existingAppointmentTable = TableInfo.read(_db, "AppointmentTable");
        if (! _infoAppointmentTable.equals(_existingAppointmentTable)) {
          return new RoomOpenHelper.ValidationResult(false, "AppointmentTable(com.example.medication_reminder_android_app.SQLiteDB.AppointmentTable).\n"
                  + " Expected:\n" + _infoAppointmentTable + "\n"
                  + " Found:\n" + _existingAppointmentTable);
        }
        final HashMap<String, TableInfo.Column> _columnsRemindersTable = new HashMap<String, TableInfo.Column>(1);
        _columnsRemindersTable.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRemindersTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesRemindersTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoRemindersTable = new TableInfo("RemindersTable", _columnsRemindersTable, _foreignKeysRemindersTable, _indicesRemindersTable);
        final TableInfo _existingRemindersTable = TableInfo.read(_db, "RemindersTable");
        if (! _infoRemindersTable.equals(_existingRemindersTable)) {
          return new RoomOpenHelper.ValidationResult(false, "RemindersTable(com.example.medication_reminder_android_app.SQLiteDB.RemindersTable).\n"
                  + " Expected:\n" + _infoRemindersTable + "\n"
                  + " Found:\n" + _existingRemindersTable);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "71740aaf023512f3f6cb2e9d88c724a9", "fb43859000121965d10f48ab39a56f4e");
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
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "MedicationTable","DoctorTable","AppointmentTable","RemindersTable");
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
      _db.execSQL("DELETE FROM `RemindersTable`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }
}
