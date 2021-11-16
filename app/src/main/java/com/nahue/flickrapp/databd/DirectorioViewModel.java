package com.nahue.flickrapp.databd;
import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.nahue.flickrapp.callback.APICallback;

import java.util.List;

public class DirectorioViewModel extends AndroidViewModel{
    private FlickrRepository mRepository;

    private final LiveData<List<Directorio>> mAllDirectorios;


    public APICallback onDelete;
    public DirectorioViewModel(Application app){
        super(app);
        mRepository=new FlickrRepository(app);
        mAllDirectorios=mRepository.getAllDirectorios();
    }



    public APICallback getOnDelete() {
        return onDelete;
    }

    public LiveData<List<Directorio>> getAllDirectorios(){return  mAllDirectorios;}
    public void insert(Directorio directorio){mRepository.insertDirectorio(directorio);}
    public void deleteAll(){mRepository.deleteAllDirectorio();}

    //No lo borro porque me parece interesante el concepto de AsyncTask(Deprecado)
    //pero no lo uso.
    private class DeleteAllAsyncTask extends AsyncTask<Void,Void,Void>{


        @Override
        protected Void doInBackground(Void... voids) {

            mRepository.deleteAllDirectorio();
            onDelete.n_a_callback();
            return null;
        }
    }
}
