package com.nahue.flickrapp.databd;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
public class DetalleDirectorioViewModel extends AndroidViewModel{
    private FlickrRepository mRepository;

    private final LiveData<List<DetalleDirectorio>> mAllFotos;



    public DetalleDirectorioViewModel(Application app){
        super(app);
        mRepository=new FlickrRepository(app);


        mAllFotos=mRepository.getAllFotos();
    }

    LiveData<List<DetalleDirectorio>> getAllFotos(){return  mAllFotos;}
    public void insert(DetalleDirectorio foto){mRepository.insertFoto(foto);}
    public void deleteAll(){mRepository.deleteAllFotos();}
    public void deleteAllFromAlbum(String album){mRepository.deleteAllFromAlbum(album);}
    public LiveData<List<DetalleDirectorio>> getAllFotosFromAlbum(String album) { return mRepository.getAllFotosFromAlbum(album);}

}
