package com.nahue.flickrapp.databd;
import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
public class DirectorioViewModel extends AndroidViewModel{
    private FlickrRepository mRepository;

    private final LiveData<List<Directorio>> mAllDirectorios;

    public DirectorioViewModel(Application app){
        super(app);
        mRepository=new FlickrRepository(app);
        mAllDirectorios=mRepository.getAllDirectorios();
    }

    public LiveData<List<Directorio>> getAllDirectorios(){return  mAllDirectorios;}
    public void insert(Directorio directorio){mRepository.insertDirectorio(directorio);}
    public void deleteAll(){mRepository.deleteAllDirectorio();}

}
