package com.nahue.flickrapp.databd;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class DirectorioDao_Impl implements DirectorioDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Directorio> __insertionAdapterOfDirectorio;

  private final EntityDeletionOrUpdateAdapter<Directorio> __updateAdapterOfDirectorio;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public DirectorioDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDirectorio = new EntityInsertionAdapter<Directorio>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `directorio` (`pk`,`id`,`secret`,`primary`,`server`,`farm`,`photos`,`count_views`,`title`,`descripcion`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Directorio value) {
        stmt.bindLong(1, value.pk);
        if (value.id == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.id);
        }
        if (value.secret == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.secret);
        }
        if (value.primary == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.primary);
        }
        if (value.server == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.server);
        }
        if (value.farm == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.farm);
        }
        if (value.photos == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.photos);
        }
        if (value.count_views == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.count_views);
        }
        if (value.title == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.title);
        }
        if (value.descripcion == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.descripcion);
        }
      }
    };
    this.__updateAdapterOfDirectorio = new EntityDeletionOrUpdateAdapter<Directorio>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `directorio` SET `pk` = ?,`id` = ?,`secret` = ?,`primary` = ?,`server` = ?,`farm` = ?,`photos` = ?,`count_views` = ?,`title` = ?,`descripcion` = ? WHERE `pk` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Directorio value) {
        stmt.bindLong(1, value.pk);
        if (value.id == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.id);
        }
        if (value.secret == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.secret);
        }
        if (value.primary == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.primary);
        }
        if (value.server == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.server);
        }
        if (value.farm == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.farm);
        }
        if (value.photos == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.photos);
        }
        if (value.count_views == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.count_views);
        }
        if (value.title == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.title);
        }
        if (value.descripcion == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.descripcion);
        }
        stmt.bindLong(11, value.pk);
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM directorio";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Directorio directorio) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfDirectorio.insert(directorio);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Directorio... directorio) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfDirectorio.handleMultiple(directorio);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public LiveData<List<Directorio>> getDirectorios() {
    final String _sql = "SELECT * FROM directorio ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"directorio"}, false, new Callable<List<Directorio>>() {
      @Override
      public List<Directorio> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPk = CursorUtil.getColumnIndexOrThrow(_cursor, "pk");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSecret = CursorUtil.getColumnIndexOrThrow(_cursor, "secret");
          final int _cursorIndexOfPrimary = CursorUtil.getColumnIndexOrThrow(_cursor, "primary");
          final int _cursorIndexOfServer = CursorUtil.getColumnIndexOrThrow(_cursor, "server");
          final int _cursorIndexOfFarm = CursorUtil.getColumnIndexOrThrow(_cursor, "farm");
          final int _cursorIndexOfPhotos = CursorUtil.getColumnIndexOrThrow(_cursor, "photos");
          final int _cursorIndexOfCountViews = CursorUtil.getColumnIndexOrThrow(_cursor, "count_views");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescripcion = CursorUtil.getColumnIndexOrThrow(_cursor, "descripcion");
          final List<Directorio> _result = new ArrayList<Directorio>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Directorio _item;
            _item = new Directorio();
            _item.pk = _cursor.getInt(_cursorIndexOfPk);
            if (_cursor.isNull(_cursorIndexOfId)) {
              _item.id = null;
            } else {
              _item.id = _cursor.getString(_cursorIndexOfId);
            }
            if (_cursor.isNull(_cursorIndexOfSecret)) {
              _item.secret = null;
            } else {
              _item.secret = _cursor.getString(_cursorIndexOfSecret);
            }
            if (_cursor.isNull(_cursorIndexOfPrimary)) {
              _item.primary = null;
            } else {
              _item.primary = _cursor.getString(_cursorIndexOfPrimary);
            }
            if (_cursor.isNull(_cursorIndexOfServer)) {
              _item.server = null;
            } else {
              _item.server = _cursor.getString(_cursorIndexOfServer);
            }
            if (_cursor.isNull(_cursorIndexOfFarm)) {
              _item.farm = null;
            } else {
              _item.farm = _cursor.getString(_cursorIndexOfFarm);
            }
            if (_cursor.isNull(_cursorIndexOfPhotos)) {
              _item.photos = null;
            } else {
              _item.photos = _cursor.getString(_cursorIndexOfPhotos);
            }
            if (_cursor.isNull(_cursorIndexOfCountViews)) {
              _item.count_views = null;
            } else {
              _item.count_views = _cursor.getString(_cursorIndexOfCountViews);
            }
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _item.title = null;
            } else {
              _item.title = _cursor.getString(_cursorIndexOfTitle);
            }
            if (_cursor.isNull(_cursorIndexOfDescripcion)) {
              _item.descripcion = null;
            } else {
              _item.descripcion = _cursor.getString(_cursorIndexOfDescripcion);
            }
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

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
