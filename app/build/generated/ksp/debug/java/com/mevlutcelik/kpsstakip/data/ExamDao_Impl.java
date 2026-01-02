package com.mevlutcelik.kpsstakip.data;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class ExamDao_Impl implements ExamDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ExamEntity> __insertionAdapterOfExamEntity;

  private final EntityDeletionOrUpdateAdapter<ExamEntity> __deletionAdapterOfExamEntity;

  public ExamDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfExamEntity = new EntityInsertionAdapter<ExamEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `exams` (`id`,`date`,`name`,`turkceDogru`,`turkceYanlis`,`matDogru`,`matYanlis`,`tarihDogru`,`tarihYanlis`,`cografyaDogru`,`cografyaYanlis`,`vatandaslikDogru`,`vatandaslikYanlis`,`guncelDogru`,`guncelYanlis`,`totalNet`,`turkceTime`,`matTime`,`tarihTime`,`cografyaTime`,`vatandaslikTime`,`guncelTime`,`totalExamTime`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ExamEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getDate());
        statement.bindString(3, entity.getName());
        statement.bindLong(4, entity.getTurkceDogru());
        statement.bindLong(5, entity.getTurkceYanlis());
        statement.bindLong(6, entity.getMatDogru());
        statement.bindLong(7, entity.getMatYanlis());
        statement.bindLong(8, entity.getTarihDogru());
        statement.bindLong(9, entity.getTarihYanlis());
        statement.bindLong(10, entity.getCografyaDogru());
        statement.bindLong(11, entity.getCografyaYanlis());
        statement.bindLong(12, entity.getVatandaslikDogru());
        statement.bindLong(13, entity.getVatandaslikYanlis());
        statement.bindLong(14, entity.getGuncelDogru());
        statement.bindLong(15, entity.getGuncelYanlis());
        statement.bindDouble(16, entity.getTotalNet());
        statement.bindLong(17, entity.getTurkceTime());
        statement.bindLong(18, entity.getMatTime());
        statement.bindLong(19, entity.getTarihTime());
        statement.bindLong(20, entity.getCografyaTime());
        statement.bindLong(21, entity.getVatandaslikTime());
        statement.bindLong(22, entity.getGuncelTime());
        statement.bindLong(23, entity.getTotalExamTime());
      }
    };
    this.__deletionAdapterOfExamEntity = new EntityDeletionOrUpdateAdapter<ExamEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `exams` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ExamEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
  }

  @Override
  public Object insertExam(final ExamEntity exam, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfExamEntity.insert(exam);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteExam(final ExamEntity exam, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfExamEntity.handle(exam);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<ExamEntity>> getAllExams() {
    final String _sql = "SELECT * FROM exams ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"exams"}, new Callable<List<ExamEntity>>() {
      @Override
      @NonNull
      public List<ExamEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfTurkceDogru = CursorUtil.getColumnIndexOrThrow(_cursor, "turkceDogru");
          final int _cursorIndexOfTurkceYanlis = CursorUtil.getColumnIndexOrThrow(_cursor, "turkceYanlis");
          final int _cursorIndexOfMatDogru = CursorUtil.getColumnIndexOrThrow(_cursor, "matDogru");
          final int _cursorIndexOfMatYanlis = CursorUtil.getColumnIndexOrThrow(_cursor, "matYanlis");
          final int _cursorIndexOfTarihDogru = CursorUtil.getColumnIndexOrThrow(_cursor, "tarihDogru");
          final int _cursorIndexOfTarihYanlis = CursorUtil.getColumnIndexOrThrow(_cursor, "tarihYanlis");
          final int _cursorIndexOfCografyaDogru = CursorUtil.getColumnIndexOrThrow(_cursor, "cografyaDogru");
          final int _cursorIndexOfCografyaYanlis = CursorUtil.getColumnIndexOrThrow(_cursor, "cografyaYanlis");
          final int _cursorIndexOfVatandaslikDogru = CursorUtil.getColumnIndexOrThrow(_cursor, "vatandaslikDogru");
          final int _cursorIndexOfVatandaslikYanlis = CursorUtil.getColumnIndexOrThrow(_cursor, "vatandaslikYanlis");
          final int _cursorIndexOfGuncelDogru = CursorUtil.getColumnIndexOrThrow(_cursor, "guncelDogru");
          final int _cursorIndexOfGuncelYanlis = CursorUtil.getColumnIndexOrThrow(_cursor, "guncelYanlis");
          final int _cursorIndexOfTotalNet = CursorUtil.getColumnIndexOrThrow(_cursor, "totalNet");
          final int _cursorIndexOfTurkceTime = CursorUtil.getColumnIndexOrThrow(_cursor, "turkceTime");
          final int _cursorIndexOfMatTime = CursorUtil.getColumnIndexOrThrow(_cursor, "matTime");
          final int _cursorIndexOfTarihTime = CursorUtil.getColumnIndexOrThrow(_cursor, "tarihTime");
          final int _cursorIndexOfCografyaTime = CursorUtil.getColumnIndexOrThrow(_cursor, "cografyaTime");
          final int _cursorIndexOfVatandaslikTime = CursorUtil.getColumnIndexOrThrow(_cursor, "vatandaslikTime");
          final int _cursorIndexOfGuncelTime = CursorUtil.getColumnIndexOrThrow(_cursor, "guncelTime");
          final int _cursorIndexOfTotalExamTime = CursorUtil.getColumnIndexOrThrow(_cursor, "totalExamTime");
          final List<ExamEntity> _result = new ArrayList<ExamEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ExamEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final int _tmpTurkceDogru;
            _tmpTurkceDogru = _cursor.getInt(_cursorIndexOfTurkceDogru);
            final int _tmpTurkceYanlis;
            _tmpTurkceYanlis = _cursor.getInt(_cursorIndexOfTurkceYanlis);
            final int _tmpMatDogru;
            _tmpMatDogru = _cursor.getInt(_cursorIndexOfMatDogru);
            final int _tmpMatYanlis;
            _tmpMatYanlis = _cursor.getInt(_cursorIndexOfMatYanlis);
            final int _tmpTarihDogru;
            _tmpTarihDogru = _cursor.getInt(_cursorIndexOfTarihDogru);
            final int _tmpTarihYanlis;
            _tmpTarihYanlis = _cursor.getInt(_cursorIndexOfTarihYanlis);
            final int _tmpCografyaDogru;
            _tmpCografyaDogru = _cursor.getInt(_cursorIndexOfCografyaDogru);
            final int _tmpCografyaYanlis;
            _tmpCografyaYanlis = _cursor.getInt(_cursorIndexOfCografyaYanlis);
            final int _tmpVatandaslikDogru;
            _tmpVatandaslikDogru = _cursor.getInt(_cursorIndexOfVatandaslikDogru);
            final int _tmpVatandaslikYanlis;
            _tmpVatandaslikYanlis = _cursor.getInt(_cursorIndexOfVatandaslikYanlis);
            final int _tmpGuncelDogru;
            _tmpGuncelDogru = _cursor.getInt(_cursorIndexOfGuncelDogru);
            final int _tmpGuncelYanlis;
            _tmpGuncelYanlis = _cursor.getInt(_cursorIndexOfGuncelYanlis);
            final float _tmpTotalNet;
            _tmpTotalNet = _cursor.getFloat(_cursorIndexOfTotalNet);
            final int _tmpTurkceTime;
            _tmpTurkceTime = _cursor.getInt(_cursorIndexOfTurkceTime);
            final int _tmpMatTime;
            _tmpMatTime = _cursor.getInt(_cursorIndexOfMatTime);
            final int _tmpTarihTime;
            _tmpTarihTime = _cursor.getInt(_cursorIndexOfTarihTime);
            final int _tmpCografyaTime;
            _tmpCografyaTime = _cursor.getInt(_cursorIndexOfCografyaTime);
            final int _tmpVatandaslikTime;
            _tmpVatandaslikTime = _cursor.getInt(_cursorIndexOfVatandaslikTime);
            final int _tmpGuncelTime;
            _tmpGuncelTime = _cursor.getInt(_cursorIndexOfGuncelTime);
            final int _tmpTotalExamTime;
            _tmpTotalExamTime = _cursor.getInt(_cursorIndexOfTotalExamTime);
            _item = new ExamEntity(_tmpId,_tmpDate,_tmpName,_tmpTurkceDogru,_tmpTurkceYanlis,_tmpMatDogru,_tmpMatYanlis,_tmpTarihDogru,_tmpTarihYanlis,_tmpCografyaDogru,_tmpCografyaYanlis,_tmpVatandaslikDogru,_tmpVatandaslikYanlis,_tmpGuncelDogru,_tmpGuncelYanlis,_tmpTotalNet,_tmpTurkceTime,_tmpMatTime,_tmpTarihTime,_tmpCografyaTime,_tmpVatandaslikTime,_tmpGuncelTime,_tmpTotalExamTime);
            _result.add(_item);
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
  public Object getExamById(final int id, final Continuation<? super ExamEntity> $completion) {
    final String _sql = "SELECT * FROM exams WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ExamEntity>() {
      @Override
      @Nullable
      public ExamEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfTurkceDogru = CursorUtil.getColumnIndexOrThrow(_cursor, "turkceDogru");
          final int _cursorIndexOfTurkceYanlis = CursorUtil.getColumnIndexOrThrow(_cursor, "turkceYanlis");
          final int _cursorIndexOfMatDogru = CursorUtil.getColumnIndexOrThrow(_cursor, "matDogru");
          final int _cursorIndexOfMatYanlis = CursorUtil.getColumnIndexOrThrow(_cursor, "matYanlis");
          final int _cursorIndexOfTarihDogru = CursorUtil.getColumnIndexOrThrow(_cursor, "tarihDogru");
          final int _cursorIndexOfTarihYanlis = CursorUtil.getColumnIndexOrThrow(_cursor, "tarihYanlis");
          final int _cursorIndexOfCografyaDogru = CursorUtil.getColumnIndexOrThrow(_cursor, "cografyaDogru");
          final int _cursorIndexOfCografyaYanlis = CursorUtil.getColumnIndexOrThrow(_cursor, "cografyaYanlis");
          final int _cursorIndexOfVatandaslikDogru = CursorUtil.getColumnIndexOrThrow(_cursor, "vatandaslikDogru");
          final int _cursorIndexOfVatandaslikYanlis = CursorUtil.getColumnIndexOrThrow(_cursor, "vatandaslikYanlis");
          final int _cursorIndexOfGuncelDogru = CursorUtil.getColumnIndexOrThrow(_cursor, "guncelDogru");
          final int _cursorIndexOfGuncelYanlis = CursorUtil.getColumnIndexOrThrow(_cursor, "guncelYanlis");
          final int _cursorIndexOfTotalNet = CursorUtil.getColumnIndexOrThrow(_cursor, "totalNet");
          final int _cursorIndexOfTurkceTime = CursorUtil.getColumnIndexOrThrow(_cursor, "turkceTime");
          final int _cursorIndexOfMatTime = CursorUtil.getColumnIndexOrThrow(_cursor, "matTime");
          final int _cursorIndexOfTarihTime = CursorUtil.getColumnIndexOrThrow(_cursor, "tarihTime");
          final int _cursorIndexOfCografyaTime = CursorUtil.getColumnIndexOrThrow(_cursor, "cografyaTime");
          final int _cursorIndexOfVatandaslikTime = CursorUtil.getColumnIndexOrThrow(_cursor, "vatandaslikTime");
          final int _cursorIndexOfGuncelTime = CursorUtil.getColumnIndexOrThrow(_cursor, "guncelTime");
          final int _cursorIndexOfTotalExamTime = CursorUtil.getColumnIndexOrThrow(_cursor, "totalExamTime");
          final ExamEntity _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final int _tmpTurkceDogru;
            _tmpTurkceDogru = _cursor.getInt(_cursorIndexOfTurkceDogru);
            final int _tmpTurkceYanlis;
            _tmpTurkceYanlis = _cursor.getInt(_cursorIndexOfTurkceYanlis);
            final int _tmpMatDogru;
            _tmpMatDogru = _cursor.getInt(_cursorIndexOfMatDogru);
            final int _tmpMatYanlis;
            _tmpMatYanlis = _cursor.getInt(_cursorIndexOfMatYanlis);
            final int _tmpTarihDogru;
            _tmpTarihDogru = _cursor.getInt(_cursorIndexOfTarihDogru);
            final int _tmpTarihYanlis;
            _tmpTarihYanlis = _cursor.getInt(_cursorIndexOfTarihYanlis);
            final int _tmpCografyaDogru;
            _tmpCografyaDogru = _cursor.getInt(_cursorIndexOfCografyaDogru);
            final int _tmpCografyaYanlis;
            _tmpCografyaYanlis = _cursor.getInt(_cursorIndexOfCografyaYanlis);
            final int _tmpVatandaslikDogru;
            _tmpVatandaslikDogru = _cursor.getInt(_cursorIndexOfVatandaslikDogru);
            final int _tmpVatandaslikYanlis;
            _tmpVatandaslikYanlis = _cursor.getInt(_cursorIndexOfVatandaslikYanlis);
            final int _tmpGuncelDogru;
            _tmpGuncelDogru = _cursor.getInt(_cursorIndexOfGuncelDogru);
            final int _tmpGuncelYanlis;
            _tmpGuncelYanlis = _cursor.getInt(_cursorIndexOfGuncelYanlis);
            final float _tmpTotalNet;
            _tmpTotalNet = _cursor.getFloat(_cursorIndexOfTotalNet);
            final int _tmpTurkceTime;
            _tmpTurkceTime = _cursor.getInt(_cursorIndexOfTurkceTime);
            final int _tmpMatTime;
            _tmpMatTime = _cursor.getInt(_cursorIndexOfMatTime);
            final int _tmpTarihTime;
            _tmpTarihTime = _cursor.getInt(_cursorIndexOfTarihTime);
            final int _tmpCografyaTime;
            _tmpCografyaTime = _cursor.getInt(_cursorIndexOfCografyaTime);
            final int _tmpVatandaslikTime;
            _tmpVatandaslikTime = _cursor.getInt(_cursorIndexOfVatandaslikTime);
            final int _tmpGuncelTime;
            _tmpGuncelTime = _cursor.getInt(_cursorIndexOfGuncelTime);
            final int _tmpTotalExamTime;
            _tmpTotalExamTime = _cursor.getInt(_cursorIndexOfTotalExamTime);
            _result = new ExamEntity(_tmpId,_tmpDate,_tmpName,_tmpTurkceDogru,_tmpTurkceYanlis,_tmpMatDogru,_tmpMatYanlis,_tmpTarihDogru,_tmpTarihYanlis,_tmpCografyaDogru,_tmpCografyaYanlis,_tmpVatandaslikDogru,_tmpVatandaslikYanlis,_tmpGuncelDogru,_tmpGuncelYanlis,_tmpTotalNet,_tmpTurkceTime,_tmpMatTime,_tmpTarihTime,_tmpCografyaTime,_tmpVatandaslikTime,_tmpGuncelTime,_tmpTotalExamTime);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<ExamEntity>> getRecentExams() {
    final String _sql = "SELECT * FROM exams ORDER BY date DESC LIMIT 5";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"exams"}, new Callable<List<ExamEntity>>() {
      @Override
      @NonNull
      public List<ExamEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfTurkceDogru = CursorUtil.getColumnIndexOrThrow(_cursor, "turkceDogru");
          final int _cursorIndexOfTurkceYanlis = CursorUtil.getColumnIndexOrThrow(_cursor, "turkceYanlis");
          final int _cursorIndexOfMatDogru = CursorUtil.getColumnIndexOrThrow(_cursor, "matDogru");
          final int _cursorIndexOfMatYanlis = CursorUtil.getColumnIndexOrThrow(_cursor, "matYanlis");
          final int _cursorIndexOfTarihDogru = CursorUtil.getColumnIndexOrThrow(_cursor, "tarihDogru");
          final int _cursorIndexOfTarihYanlis = CursorUtil.getColumnIndexOrThrow(_cursor, "tarihYanlis");
          final int _cursorIndexOfCografyaDogru = CursorUtil.getColumnIndexOrThrow(_cursor, "cografyaDogru");
          final int _cursorIndexOfCografyaYanlis = CursorUtil.getColumnIndexOrThrow(_cursor, "cografyaYanlis");
          final int _cursorIndexOfVatandaslikDogru = CursorUtil.getColumnIndexOrThrow(_cursor, "vatandaslikDogru");
          final int _cursorIndexOfVatandaslikYanlis = CursorUtil.getColumnIndexOrThrow(_cursor, "vatandaslikYanlis");
          final int _cursorIndexOfGuncelDogru = CursorUtil.getColumnIndexOrThrow(_cursor, "guncelDogru");
          final int _cursorIndexOfGuncelYanlis = CursorUtil.getColumnIndexOrThrow(_cursor, "guncelYanlis");
          final int _cursorIndexOfTotalNet = CursorUtil.getColumnIndexOrThrow(_cursor, "totalNet");
          final int _cursorIndexOfTurkceTime = CursorUtil.getColumnIndexOrThrow(_cursor, "turkceTime");
          final int _cursorIndexOfMatTime = CursorUtil.getColumnIndexOrThrow(_cursor, "matTime");
          final int _cursorIndexOfTarihTime = CursorUtil.getColumnIndexOrThrow(_cursor, "tarihTime");
          final int _cursorIndexOfCografyaTime = CursorUtil.getColumnIndexOrThrow(_cursor, "cografyaTime");
          final int _cursorIndexOfVatandaslikTime = CursorUtil.getColumnIndexOrThrow(_cursor, "vatandaslikTime");
          final int _cursorIndexOfGuncelTime = CursorUtil.getColumnIndexOrThrow(_cursor, "guncelTime");
          final int _cursorIndexOfTotalExamTime = CursorUtil.getColumnIndexOrThrow(_cursor, "totalExamTime");
          final List<ExamEntity> _result = new ArrayList<ExamEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ExamEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final int _tmpTurkceDogru;
            _tmpTurkceDogru = _cursor.getInt(_cursorIndexOfTurkceDogru);
            final int _tmpTurkceYanlis;
            _tmpTurkceYanlis = _cursor.getInt(_cursorIndexOfTurkceYanlis);
            final int _tmpMatDogru;
            _tmpMatDogru = _cursor.getInt(_cursorIndexOfMatDogru);
            final int _tmpMatYanlis;
            _tmpMatYanlis = _cursor.getInt(_cursorIndexOfMatYanlis);
            final int _tmpTarihDogru;
            _tmpTarihDogru = _cursor.getInt(_cursorIndexOfTarihDogru);
            final int _tmpTarihYanlis;
            _tmpTarihYanlis = _cursor.getInt(_cursorIndexOfTarihYanlis);
            final int _tmpCografyaDogru;
            _tmpCografyaDogru = _cursor.getInt(_cursorIndexOfCografyaDogru);
            final int _tmpCografyaYanlis;
            _tmpCografyaYanlis = _cursor.getInt(_cursorIndexOfCografyaYanlis);
            final int _tmpVatandaslikDogru;
            _tmpVatandaslikDogru = _cursor.getInt(_cursorIndexOfVatandaslikDogru);
            final int _tmpVatandaslikYanlis;
            _tmpVatandaslikYanlis = _cursor.getInt(_cursorIndexOfVatandaslikYanlis);
            final int _tmpGuncelDogru;
            _tmpGuncelDogru = _cursor.getInt(_cursorIndexOfGuncelDogru);
            final int _tmpGuncelYanlis;
            _tmpGuncelYanlis = _cursor.getInt(_cursorIndexOfGuncelYanlis);
            final float _tmpTotalNet;
            _tmpTotalNet = _cursor.getFloat(_cursorIndexOfTotalNet);
            final int _tmpTurkceTime;
            _tmpTurkceTime = _cursor.getInt(_cursorIndexOfTurkceTime);
            final int _tmpMatTime;
            _tmpMatTime = _cursor.getInt(_cursorIndexOfMatTime);
            final int _tmpTarihTime;
            _tmpTarihTime = _cursor.getInt(_cursorIndexOfTarihTime);
            final int _tmpCografyaTime;
            _tmpCografyaTime = _cursor.getInt(_cursorIndexOfCografyaTime);
            final int _tmpVatandaslikTime;
            _tmpVatandaslikTime = _cursor.getInt(_cursorIndexOfVatandaslikTime);
            final int _tmpGuncelTime;
            _tmpGuncelTime = _cursor.getInt(_cursorIndexOfGuncelTime);
            final int _tmpTotalExamTime;
            _tmpTotalExamTime = _cursor.getInt(_cursorIndexOfTotalExamTime);
            _item = new ExamEntity(_tmpId,_tmpDate,_tmpName,_tmpTurkceDogru,_tmpTurkceYanlis,_tmpMatDogru,_tmpMatYanlis,_tmpTarihDogru,_tmpTarihYanlis,_tmpCografyaDogru,_tmpCografyaYanlis,_tmpVatandaslikDogru,_tmpVatandaslikYanlis,_tmpGuncelDogru,_tmpGuncelYanlis,_tmpTotalNet,_tmpTurkceTime,_tmpMatTime,_tmpTarihTime,_tmpCografyaTime,_tmpVatandaslikTime,_tmpGuncelTime,_tmpTotalExamTime);
            _result.add(_item);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
