package com.nahue.flickrapp.databd;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface DirectorioDao {

	//Permitir directorios repetidos
    @Insert(onConflict= OnConflictStrategy.IGNORE)
    void insert(Directorio directorio);

    @Query("DELETE FROM directorio")
    void deleteAll();

    @Query("SELECT * FROM directorio ")
    LiveData<List<Directorio>> getDirectorios();

}
