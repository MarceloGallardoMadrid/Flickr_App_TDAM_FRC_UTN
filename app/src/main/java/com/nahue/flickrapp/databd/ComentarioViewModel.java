package com.nahue.flickrapp.databd;
import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
public class ComentarioViewModel extends AndroidViewModel{
    private FlickrRepository mRepository;

    private final LiveData<List<Comentario>> mAllComentarios;

    public ComentarioViewModel(Application app){
        super(app);
        mRepository=new FlickrRepository(app);
        mAllComentarios=mRepository.getAllComentarios();
    }

    LiveData<List<Comentario>> getAllComentarios(){return  mAllComentarios;}
    public void insert(Comentario comentario){mRepository.insertComentario(comentario);}


}
