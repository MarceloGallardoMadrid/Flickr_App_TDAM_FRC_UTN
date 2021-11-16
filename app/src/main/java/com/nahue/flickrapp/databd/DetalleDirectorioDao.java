package com.nahue.flickrapp.databd;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface DetalleDirectorioDao {

	//Permitir fotos repetidas
	@Insert(onConflict = OnConflictStrategy.IGNORE)
	void insert(DetalleDirectorio detalleDirectorio);

	@Query("SELECT * FROM detalle_directorio")
	LiveData<List<DetalleDirectorio>> getFotos();

	@Query("SELECT * FROM detalle_directorio where photoset_id like :album")
	LiveData<List<DetalleDirectorio>> getFotosFromAlbum(String album);
	
	@Update
	void update(DetalleDirectorio... detalleDirectorio);

	@Query("DELETE FROM detalle_directorio")
	void deleteAll();

	@Query("DELETE FROM detalle_directorio where photoset_id like :album ")
	void deleteAllFromAlbum(String album);

}
