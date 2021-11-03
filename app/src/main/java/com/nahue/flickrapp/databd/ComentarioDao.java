package com.nahue.flickrapp.databd;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ComentarioDao {
	//Permitir Comentarios repetidos
	@Insert(onConflict=OnConflictStrategy.IGNORE)
	void insert(Comentario comentario);
	
	@Query("DELETE FROM comentario")
	void deleteAll();
	
	@Query("SELECT * FROM comentario ")
	LiveData<List<Comentario>> getComentarios();
	
	@Update
	void update(Comentario... comentario);
	

}
