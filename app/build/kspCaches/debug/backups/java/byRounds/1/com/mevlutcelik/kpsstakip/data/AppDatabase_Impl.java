package com.mevlutcelik.kpsstakip.data;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile ExamDao _examDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(3) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `exams` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `date` INTEGER NOT NULL, `name` TEXT NOT NULL, `turkceDogru` INTEGER NOT NULL, `turkceYanlis` INTEGER NOT NULL, `matDogru` INTEGER NOT NULL, `matYanlis` INTEGER NOT NULL, `tarihDogru` INTEGER NOT NULL, `tarihYanlis` INTEGER NOT NULL, `cografyaDogru` INTEGER NOT NULL, `cografyaYanlis` INTEGER NOT NULL, `vatandaslikDogru` INTEGER NOT NULL, `vatandaslikYanlis` INTEGER NOT NULL, `guncelDogru` INTEGER NOT NULL, `guncelYanlis` INTEGER NOT NULL, `totalNet` REAL NOT NULL, `turkceTime` INTEGER NOT NULL, `matTime` INTEGER NOT NULL, `tarihTime` INTEGER NOT NULL, `cografyaTime` INTEGER NOT NULL, `vatandaslikTime` INTEGER NOT NULL, `guncelTime` INTEGER NOT NULL, `totalExamTime` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '0a128a490f975905fe704b04e33efd2f')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `exams`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsExams = new HashMap<String, TableInfo.Column>(23);
        _columnsExams.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExams.put("date", new TableInfo.Column("date", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExams.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExams.put("turkceDogru", new TableInfo.Column("turkceDogru", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExams.put("turkceYanlis", new TableInfo.Column("turkceYanlis", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExams.put("matDogru", new TableInfo.Column("matDogru", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExams.put("matYanlis", new TableInfo.Column("matYanlis", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExams.put("tarihDogru", new TableInfo.Column("tarihDogru", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExams.put("tarihYanlis", new TableInfo.Column("tarihYanlis", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExams.put("cografyaDogru", new TableInfo.Column("cografyaDogru", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExams.put("cografyaYanlis", new TableInfo.Column("cografyaYanlis", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExams.put("vatandaslikDogru", new TableInfo.Column("vatandaslikDogru", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExams.put("vatandaslikYanlis", new TableInfo.Column("vatandaslikYanlis", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExams.put("guncelDogru", new TableInfo.Column("guncelDogru", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExams.put("guncelYanlis", new TableInfo.Column("guncelYanlis", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExams.put("totalNet", new TableInfo.Column("totalNet", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExams.put("turkceTime", new TableInfo.Column("turkceTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExams.put("matTime", new TableInfo.Column("matTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExams.put("tarihTime", new TableInfo.Column("tarihTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExams.put("cografyaTime", new TableInfo.Column("cografyaTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExams.put("vatandaslikTime", new TableInfo.Column("vatandaslikTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExams.put("guncelTime", new TableInfo.Column("guncelTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExams.put("totalExamTime", new TableInfo.Column("totalExamTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysExams = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesExams = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoExams = new TableInfo("exams", _columnsExams, _foreignKeysExams, _indicesExams);
        final TableInfo _existingExams = TableInfo.read(db, "exams");
        if (!_infoExams.equals(_existingExams)) {
          return new RoomOpenHelper.ValidationResult(false, "exams(com.mevlutcelik.kpsstakip.data.ExamEntity).\n"
                  + " Expected:\n" + _infoExams + "\n"
                  + " Found:\n" + _existingExams);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "0a128a490f975905fe704b04e33efd2f", "bada17873c71a07e3d16a55abb90ecca");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "exams");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `exams`");
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
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(ExamDao.class, ExamDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public ExamDao examDao() {
    if (_examDao != null) {
      return _examDao;
    } else {
      synchronized(this) {
        if(_examDao == null) {
          _examDao = new ExamDao_Impl(this);
        }
        return _examDao;
      }
    }
  }
}
