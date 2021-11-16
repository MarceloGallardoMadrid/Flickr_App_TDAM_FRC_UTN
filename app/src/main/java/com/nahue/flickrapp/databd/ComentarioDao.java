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

	@Query("DELETE FROM COMENTARIO where photo_id like :foto_id and album_id like :set_id")
	void deleteFromFoto(String foto_id,String set_id);

	@Query("SELECT * FROM comentario ")
	LiveData<List<Comentario>> getComentarios();

	@Query("SELECT * FROM comentario where photo_id like :foto_id and album_id like :set_id")
	LiveData<List<Comentario>> getComentariosFromFoto(String foto_id,String set_id);
	@Update
	void update(Comentario... comentario);
	

}
