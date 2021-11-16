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
    public void deleteAll(){mRepository.deleteAllComentario();}
    public  void deleteAllFromFoto(String foto_id,String set_id){mRepository.deleteAllComentariosFromFoto(foto_id, set_id);}
    public LiveData<List<Comentario>> getAllFromFoto(String foto,String set_id){return  mRepository.getAllComentariosFromFoto(foto, set_id);}

}
