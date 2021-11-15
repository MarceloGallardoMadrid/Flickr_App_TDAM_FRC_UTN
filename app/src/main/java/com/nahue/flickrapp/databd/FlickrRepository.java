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
	
	public FlickrRepository(Application application){
		FlickrDatabase db=FlickrDatabase.getDatabase(application);
		
		mDirectorioDao =db.directorioDao();
		mAllDirectorios=mDirectorioDao.getDirectorios();
		
		mDetalleDirectorioDao =db.detalleDirectorioDao();
		mAllDetalleDirectorios=mDetalleDirectorioDao.getFotos();
		
		
		mComentarioDao=db.comentarioDao();
		mAllComentarios=mComentarioDao.getComentarios();
		
		
	}

	//Metodo para reiniciar tabla comentarios
	public void deleteAllComentario(){
	    mComentarioDao.deleteAll();
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


    //Metodo para reiniciar la tabla detalle_directorio
    public void deleteAllFotos(){
        mDetalleDirectorioDao.deleteAll();
    }

	//Observed LiveData will notify the observer when the data has changed
    LiveData<List<DetalleDirectorio>> getAllFotos(){
        return mAllDetalleDirectorios;
    }

    //Para evitar long running task en el main thread, debo utilizar un hilo distinto al del UI
    void insertFoto(DetalleDirectorio detalle){
        FlickrDatabase.databaseWriteExecutor.execute(()->{
            mDetalleDirectorioDao.insert(detalle);
        });
    }

}

