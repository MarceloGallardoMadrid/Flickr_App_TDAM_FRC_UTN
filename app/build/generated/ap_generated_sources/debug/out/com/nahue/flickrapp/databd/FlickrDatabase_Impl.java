package com.nahue.flickrapp.databd;

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
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class FlickrDatabase_Impl extends FlickrDatabase {
  private volatile ComentarioDao _comentarioDao;

  private volatile DirectorioDao _directorioDao;

  private volatile DetalleDirectorioDao _detalleDirectorioDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `comentario` (`pk` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `photo_id` TEXT, `comment_id` TEXT, `author_id` TEXT, `authorname` TEXT, `datecreate` TEXT, `permalink` TEXT, `comment` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `directorio` (`pk` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `id` TEXT, `secret` TEXT, `primary` TEXT, `server` TEXT, `farm` TEXT, `photos` TEXT, `count_views` TEXT, `title` TEXT, `descripcion` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `detalle_directorio` (`pk` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `photoset_id` TEXT, `photo_id` TEXT, `secret` TEXT, `server_id` TEXT, `title` TEXT, `isprimary` INTEGER NOT NULL, `path_disco_big` TEXT, `path_disco_small` TEXT, `path_url` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '1ca5a0c5331fccb55cb5cb9ac0be678c')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `comentario`");
        _db.execSQL("DROP TABLE IF EXISTS `directorio`");
        _db.execSQL("DROP TABLE IF EXISTS `detalle_directorio`");
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
        final HashMap<String, TableInfo.Column> _columnsComentario = new HashMap<String, TableInfo.Column>(8);
        _columnsComentario.put("pk", new TableInfo.Column("pk", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComentario.put("photo_id", new TableInfo.Column("photo_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComentario.put("comment_id", new TableInfo.Column("comment_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComentario.put("author_id", new TableInfo.Column("author_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComentario.put("authorname", new TableInfo.Column("authorname", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComentario.put("datecreate", new TableInfo.Column("datecreate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComentario.put("permalink", new TableInfo.Column("permalink", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComentario.put("comment", new TableInfo.Column("comment", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysComentario = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesComentario = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoComentario = new TableInfo("comentario", _columnsComentario, _foreignKeysComentario, _indicesComentario);
        final TableInfo _existingComentario = TableInfo.read(_db, "comentario");
        if (! _infoComentario.equals(_existingComentario)) {
          return new RoomOpenHelper.ValidationResult(false, "comentario(com.nahue.flickrapp.databd.Comentario).\n"
                  + " Expected:\n" + _infoComentario + "\n"
                  + " Found:\n" + _existingComentario);
        }
        final HashMap<String, TableInfo.Column> _columnsDirectorio = new HashMap<String, TableInfo.Column>(10);
        _columnsDirectorio.put("pk", new TableInfo.Column("pk", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDirectorio.put("id", new TableInfo.Column("id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDirectorio.put("secret", new TableInfo.Column("secret", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDirectorio.put("primary", new TableInfo.Column("primary", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDirectorio.put("server", new TableInfo.Column("server", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDirectorio.put("farm", new TableInfo.Column("farm", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDirectorio.put("photos", new TableInfo.Column("photos", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDirectorio.put("count_views", new TableInfo.Column("count_views", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDirectorio.put("title", new TableInfo.Column("title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDirectorio.put("descripcion", new TableInfo.Column("descripcion", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDirectorio = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDirectorio = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDirectorio = new TableInfo("directorio", _columnsDirectorio, _foreignKeysDirectorio, _indicesDirectorio);
        final TableInfo _existingDirectorio = TableInfo.read(_db, "directorio");
        if (! _infoDirectorio.equals(_existingDirectorio)) {
          return new RoomOpenHelper.ValidationResult(false, "directorio(com.nahue.flickrapp.databd.Directorio).\n"
                  + " Expected:\n" + _infoDirectorio + "\n"
                  + " Found:\n" + _existingDirectorio);
        }
        final HashMap<String, TableInfo.Column> _columnsDetalleDirectorio = new HashMap<String, TableInfo.Column>(10);
        _columnsDetalleDirectorio.put("pk", new TableInfo.Column("pk", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDetalleDirectorio.put("photoset_id", new TableInfo.Column("photoset_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDetalleDirectorio.put("photo_id", new TableInfo.Column("photo_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDetalleDirectorio.put("secret", new TableInfo.Column("secret", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDetalleDirectorio.put("server_id", new TableInfo.Column("server_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDetalleDirectorio.put("title", new TableInfo.Column("title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDetalleDirectorio.put("isprimary", new TableInfo.Column("isprimary", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDetalleDirectorio.put("path_disco_big", new TableInfo.Column("path_disco_big", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDetalleDirectorio.put("path_disco_small", new TableInfo.Column("path_disco_small", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDetalleDirectorio.put("path_url", new TableInfo.Column("path_url", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDetalleDirectorio = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDetalleDirectorio = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDetalleDirectorio = new TableInfo("detalle_directorio", _columnsDetalleDirectorio, _foreignKeysDetalleDirectorio, _indicesDetalleDirectorio);
        final TableInfo _existingDetalleDirectorio = TableInfo.read(_db, "detalle_directorio");
        if (! _infoDetalleDirectorio.equals(_existingDetalleDirectorio)) {
          return new RoomOpenHelper.ValidationResult(false, "detalle_directorio(com.nahue.flickrapp.databd.DetalleDirectorio).\n"
                  + " Expected:\n" + _infoDetalleDirectorio + "\n"
                  + " Found:\n" + _existingDetalleDirectorio);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "1ca5a0c5331fccb55cb5cb9ac0be678c", "dcd49b7afe0daf6c284d3582fa1f374c");
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
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "comentario","directorio","detalle_directorio");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `comentario`");
      _db.execSQL("DELETE FROM `directorio`");
      _db.execSQL("DELETE FROM `detalle_directorio`");
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
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(ComentarioDao.class, ComentarioDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(DirectorioDao.class, DirectorioDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(DetalleDirectorioDao.class, DetalleDirectorioDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public ComentarioDao comentarioDao() {
    if (_comentarioDao != null) {
      return _comentarioDao;
    } else {
      synchronized(this) {
        if(_comentarioDao == null) {
          _comentarioDao = new ComentarioDao_Impl(this);
        }
        return _comentarioDao;
      }
    }
  }

  @Override
  public DirectorioDao directorioDao() {
    if (_directorioDao != null) {
      return _directorioDao;
    } else {
      synchronized(this) {
        if(_directorioDao == null) {
          _directorioDao = new DirectorioDao_Impl(this);
        }
        return _directorioDao;
      }
    }
  }

  @Override
  public DetalleDirectorioDao detalleDirectorioDao() {
    if (_detalleDirectorioDao != null) {
      return _detalleDirectorioDao;
    } else {
      synchronized(this) {
        if(_detalleDirectorioDao == null) {
          _detalleDirectorioDao = new DetalleDirectorioDao_Impl(this);
        }
        return _detalleDirectorioDao;
      }
    }
  }
}
