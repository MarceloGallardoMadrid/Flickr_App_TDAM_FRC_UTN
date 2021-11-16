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
public final class DetalleDirectorioDao_Impl implements DetalleDirectorioDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DetalleDirectorio> __insertionAdapterOfDetalleDirectorio;

  private final EntityDeletionOrUpdateAdapter<DetalleDirectorio> __updateAdapterOfDetalleDirectorio;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public DetalleDirectorioDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDetalleDirectorio = new EntityInsertionAdapter<DetalleDirectorio>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `detalle_directorio` (`pk`,`photoset_id`,`photo_id`,`secret`,`server_id`,`title`,`isprimary`,`path_disco_big`,`path_disco_small`,`path_url`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DetalleDirectorio value) {
        stmt.bindLong(1, value.pk);
        if (value.photoset_id == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.photoset_id);
        }
        if (value.photo_id == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.photo_id);
        }
        if (value.secret == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.secret);
        }
        if (value.server_id == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.server_id);
        }
        if (value.title == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.title);
        }
        stmt.bindLong(7, value.isprimary);
        if (value.path_disco_big == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.path_disco_big);
        }
        if (value.path_disco_small == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.path_disco_small);
        }
        if (value.path_url == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.path_url);
        }
      }
    };
    this.__updateAdapterOfDetalleDirectorio = new EntityDeletionOrUpdateAdapter<DetalleDirectorio>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `detalle_directorio` SET `pk` = ?,`photoset_id` = ?,`photo_id` = ?,`secret` = ?,`server_id` = ?,`title` = ?,`isprimary` = ?,`path_disco_big` = ?,`path_disco_small` = ?,`path_url` = ? WHERE `pk` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DetalleDirectorio value) {
        stmt.bindLong(1, value.pk);
        if (value.photoset_id == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.photoset_id);
        }
        if (value.photo_id == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.photo_id);
        }
        if (value.secret == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.secret);
        }
        if (value.server_id == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.server_id);
        }
        if (value.title == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.title);
        }
        stmt.bindLong(7, value.isprimary);
        if (value.path_disco_big == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.path_disco_big);
        }
        if (value.path_disco_small == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.path_disco_small);
        }
        if (value.path_url == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.path_url);
        }
        stmt.bindLong(11, value.pk);
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM detalle_directorio";
        return _query;
      }
    };
  }

  @Override
  public void insert(final DetalleDirectorio detalleDirectorio) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfDetalleDirectorio.insert(detalleDirectorio);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final DetalleDirectorio... detalleDirectorio) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfDetalleDirectorio.handleMultiple(detalleDirectorio);
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
  public LiveData<List<DetalleDirectorio>> getFotos() {
    final String _sql = "SELECT * FROM detalle_directorio";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"detalle_directorio"}, false, new Callable<List<DetalleDirectorio>>() {
      @Override
      public List<DetalleDirectorio> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPk = CursorUtil.getColumnIndexOrThrow(_cursor, "pk");
          final int _cursorIndexOfPhotosetId = CursorUtil.getColumnIndexOrThrow(_cursor, "photoset_id");
          final int _cursorIndexOfPhotoId = CursorUtil.getColumnIndexOrThrow(_cursor, "photo_id");
          final int _cursorIndexOfSecret = CursorUtil.getColumnIndexOrThrow(_cursor, "secret");
          final int _cursorIndexOfServerId = CursorUtil.getColumnIndexOrThrow(_cursor, "server_id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfIsprimary = CursorUtil.getColumnIndexOrThrow(_cursor, "isprimary");
          final int _cursorIndexOfPathDiscoBig = CursorUtil.getColumnIndexOrThrow(_cursor, "path_disco_big");
          final int _cursorIndexOfPathDiscoSmall = CursorUtil.getColumnIndexOrThrow(_cursor, "path_disco_small");
          final int _cursorIndexOfPathUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "path_url");
          final List<DetalleDirectorio> _result = new ArrayList<DetalleDirectorio>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final DetalleDirectorio _item;
            _item = new DetalleDirectorio();
            _item.pk = _cursor.getInt(_cursorIndexOfPk);
            if (_cursor.isNull(_cursorIndexOfPhotosetId)) {
              _item.photoset_id = null;
            } else {
              _item.photoset_id = _cursor.getString(_cursorIndexOfPhotosetId);
            }
            if (_cursor.isNull(_cursorIndexOfPhotoId)) {
              _item.photo_id = null;
            } else {
              _item.photo_id = _cursor.getString(_cursorIndexOfPhotoId);
            }
            if (_cursor.isNull(_cursorIndexOfSecret)) {
              _item.secret = null;
            } else {
              _item.secret = _cursor.getString(_cursorIndexOfSecret);
            }
            if (_cursor.isNull(_cursorIndexOfServerId)) {
              _item.server_id = null;
            } else {
              _item.server_id = _cursor.getString(_cursorIndexOfServerId);
            }
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _item.title = null;
            } else {
              _item.title = _cursor.getString(_cursorIndexOfTitle);
            }
            _item.isprimary = _cursor.getInt(_cursorIndexOfIsprimary);
            if (_cursor.isNull(_cursorIndexOfPathDiscoBig)) {
              _item.path_disco_big = null;
            } else {
              _item.path_disco_big = _cursor.getString(_cursorIndexOfPathDiscoBig);
            }
            if (_cursor.isNull(_cursorIndexOfPathDiscoSmall)) {
              _item.path_disco_small = null;
            } else {
              _item.path_disco_small = _cursor.getString(_cursorIndexOfPathDiscoSmall);
            }
            if (_cursor.isNull(_cursorIndexOfPathUrl)) {
              _item.path_url = null;
            } else {
              _item.path_url = _cursor.getString(_cursorIndexOfPathUrl);
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
