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
public final class ComentarioDao_Impl implements ComentarioDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Comentario> __insertionAdapterOfComentario;

  private final EntityDeletionOrUpdateAdapter<Comentario> __updateAdapterOfComentario;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public ComentarioDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfComentario = new EntityInsertionAdapter<Comentario>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `comentario` (`pk`,`photo_id`,`comment_id`,`author_id`,`authorname`,`datecreate`,`permalink`,`comment`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Comentario value) {
        stmt.bindLong(1, value.pk);
        if (value.photo_id == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.photo_id);
        }
        if (value.comment_id == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.comment_id);
        }
        if (value.author_id == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.author_id);
        }
        if (value.authorname == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.authorname);
        }
        if (value.datecreate == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.datecreate);
        }
        if (value.permalink == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.permalink);
        }
        if (value.comment == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.comment);
        }
      }
    };
    this.__updateAdapterOfComentario = new EntityDeletionOrUpdateAdapter<Comentario>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `comentario` SET `pk` = ?,`photo_id` = ?,`comment_id` = ?,`author_id` = ?,`authorname` = ?,`datecreate` = ?,`permalink` = ?,`comment` = ? WHERE `pk` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Comentario value) {
        stmt.bindLong(1, value.pk);
        if (value.photo_id == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.photo_id);
        }
        if (value.comment_id == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.comment_id);
        }
        if (value.author_id == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.author_id);
        }
        if (value.authorname == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.authorname);
        }
        if (value.datecreate == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.datecreate);
        }
        if (value.permalink == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.permalink);
        }
        if (value.comment == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.comment);
        }
        stmt.bindLong(9, value.pk);
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM comentario";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Comentario comentario) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfComentario.insert(comentario);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Comentario... comentario) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfComentario.handleMultiple(comentario);
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
  public LiveData<List<Comentario>> getComentarios() {
    final String _sql = "SELECT * FROM comentario ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"comentario"}, false, new Callable<List<Comentario>>() {
      @Override
      public List<Comentario> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPk = CursorUtil.getColumnIndexOrThrow(_cursor, "pk");
          final int _cursorIndexOfPhotoId = CursorUtil.getColumnIndexOrThrow(_cursor, "photo_id");
          final int _cursorIndexOfCommentId = CursorUtil.getColumnIndexOrThrow(_cursor, "comment_id");
          final int _cursorIndexOfAuthorId = CursorUtil.getColumnIndexOrThrow(_cursor, "author_id");
          final int _cursorIndexOfAuthorname = CursorUtil.getColumnIndexOrThrow(_cursor, "authorname");
          final int _cursorIndexOfDatecreate = CursorUtil.getColumnIndexOrThrow(_cursor, "datecreate");
          final int _cursorIndexOfPermalink = CursorUtil.getColumnIndexOrThrow(_cursor, "permalink");
          final int _cursorIndexOfComment = CursorUtil.getColumnIndexOrThrow(_cursor, "comment");
          final List<Comentario> _result = new ArrayList<Comentario>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Comentario _item;
            _item = new Comentario();
            _item.pk = _cursor.getInt(_cursorIndexOfPk);
            if (_cursor.isNull(_cursorIndexOfPhotoId)) {
              _item.photo_id = null;
            } else {
              _item.photo_id = _cursor.getString(_cursorIndexOfPhotoId);
            }
            if (_cursor.isNull(_cursorIndexOfCommentId)) {
              _item.comment_id = null;
            } else {
              _item.comment_id = _cursor.getString(_cursorIndexOfCommentId);
            }
            if (_cursor.isNull(_cursorIndexOfAuthorId)) {
              _item.author_id = null;
            } else {
              _item.author_id = _cursor.getString(_cursorIndexOfAuthorId);
            }
            if (_cursor.isNull(_cursorIndexOfAuthorname)) {
              _item.authorname = null;
            } else {
              _item.authorname = _cursor.getString(_cursorIndexOfAuthorname);
            }
            if (_cursor.isNull(_cursorIndexOfDatecreate)) {
              _item.datecreate = null;
            } else {
              _item.datecreate = _cursor.getString(_cursorIndexOfDatecreate);
            }
            if (_cursor.isNull(_cursorIndexOfPermalink)) {
              _item.permalink = null;
            } else {
              _item.permalink = _cursor.getString(_cursorIndexOfPermalink);
            }
            if (_cursor.isNull(_cursorIndexOfComment)) {
              _item.comment = null;
            } else {
              _item.comment = _cursor.getString(_cursorIndexOfComment);
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
