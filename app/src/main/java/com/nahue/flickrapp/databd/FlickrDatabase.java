package com.nahue.flickrapp.databd;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(
	entities = {
				Comentario.class,
				Directorio.class,
				DetalleDirectorio.class
				},
	version = 1,
	exportSchema = false
	)
public abstract class FlickrDatabase extends RoomDatabase {
    public abstract ComentarioDao comentarioDao();
    public abstract DirectorioDao directorioDao();
    public abstract DetalleDirectorioDao detalleDirectorioDao();

    private static volatile FlickrDatabase INSTACE;

    private static final int NUMBER_OF_THREADS=4;

    static final ExecutorService databaseWriteExecutor= Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static FlickrDatabase getDatabase(final Context context){
        if(INSTACE==null){
            synchronized (FlickrDatabase.class){
                if(INSTACE==null){
                    INSTACE= Room.databaseBuilder(context.getApplicationContext(),
                            FlickrDatabase.class,"flickr_database")
                            //.addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTACE;
    }
    
}
