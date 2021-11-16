package com.nahue.flickrapp.databd;
import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class FlickrRepository{
	private ComentarioDao mComentarioDao;
	private LiveData<List<Comentario>> mAllComentarios;
	
	private DirectorioDao mDirectorioDao;
	private LiveData<List<Directorio>> mAllDirectorios;
	
	private DetalleDirectorioDao mDetalleDirectorioDao;
	private LiveData<List<DetalleDirectorio>> mAllDetalleDirectorios;
	private LiveData<List<DetalleDirectorio>> mAllDetalleDirectoriosFromAlbum;

	public FlickrRepository(Application application){
		FlickrDatabase db=FlickrDatabase.getDatabase(application);
		
		mDirectorioDao =db.directorioDao();
		mAllDirectorios=mDirectorioDao.getDirectorios();
		
		mDetalleDirectorioDao =db.detalleDirectorioDao();
		mAllDetalleDirectorios=mDetalleDirectorioDao.getFotos();


		
		mComentarioDao=db.comentarioDao();
		mAllComentarios=mComentarioDao.getComentarios();
		
		
	}

	/*
	************************COMENTARIOS****************************
	 */


	//Metodo para reiniciar tabla comentarios
	public void deleteAllComentario(){
	    mComentarioDao.deleteAll();
    }
    //Metodo para borrar los comentarios de una foto
    public void deleteAllComentariosFromFoto(String foto_id,String set_id){
	    FlickrDatabase.databaseWriteExecutor.execute(()->{
	        mComentarioDao.deleteFromFoto(foto_id,set_id);
        });
    }

    //Metodo para traer los comentarios de una foto
    LiveData<List<Comentario>> getAllComentariosFromFoto(String foto_id,String set_id){
	   return mComentarioDao.getComentariosFromFoto(foto_id, set_id);
    }


	//Observed LiveData will notify the observer when the data has changed
    LiveData<List<Comentario>> getAllComentarios(){
        return mAllComentarios;
    }

    //Para evitar long running task en el main thread, debo utilizar un hilo distinto al del UI
    void insertComentario(Comentario comentario){
        FlickrDatabase.databaseWriteExecutor.execute(()->{
            mComentarioDao.insert(comentario);
        });
    }

    /*
     ************************DIRECTORIOS****************************
     */

    //Metodo para reiniciar la tabla directorio
    public void deleteAllDirectorio(){
	    FlickrDatabase.databaseWriteExecutor.execute(()->{
                    mDirectorioDao.deleteAll();

                });


    }

    //Observed LiveData will notify the observer when the data has changed
    LiveData<List<Directorio>> getAllDirectorios(){
        return mAllDirectorios;
    }

    //Para evitar long running task en el main thread, debo utilizar un hilo distinto al del UI
    void insertDirectorio(Directorio directorio){
        FlickrDatabase.databaseWriteExecutor.execute(()->{
            mDirectorioDao.insert(directorio);
        });
    }

    /*
     ************************FOTOS****************************
     */


    //Metodo para reiniciar la tabla detalle_directorio
    public void deleteAllFotos(){
        mDetalleDirectorioDao.deleteAll();
    }

    public void deleteAllFromAlbum(String album){
	    FlickrDatabase.databaseWriteExecutor.execute(()->{
	        mDetalleDirectorioDao.deleteAllFromAlbum(album);
        });
    }

	//Observed LiveData will notify the observer when the data has changed
    LiveData<List<DetalleDirectorio>> getAllFotos(){
        return mAllDetalleDirectorios;
    }

    //Observed LiveData will notify the observer when the data has changed
    LiveData<List<DetalleDirectorio>> getAllFotosFromAlbum(String album){
        mAllDetalleDirectoriosFromAlbum=mDetalleDirectorioDao.getFotosFromAlbum(album);
        return mAllDetalleDirectoriosFromAlbum;
    }



    //Para evitar long running task en el main thread, debo utilizar un hilo distinto al del UI
    void insertFoto(DetalleDirectorio detalle){
        FlickrDatabase.databaseWriteExecutor.execute(()->{
            mDetalleDirectorioDao.insert(detalle);
        });
    }

}

