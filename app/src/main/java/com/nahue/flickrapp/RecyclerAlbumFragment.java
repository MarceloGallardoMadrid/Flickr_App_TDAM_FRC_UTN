package com.nahue.flickrapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;

//////Volley y gson////////////77
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.nahue.flickrapp.Entidades.Photo;
import com.nahue.flickrapp.Entidades.Photoset;
import com.nahue.flickrapp.Entidades.RootObject;
import com.nahue.flickrapp.databd.ComentarioViewModel;
import com.nahue.flickrapp.databd.DetalleDirectorioViewModel;
import com.nahue.flickrapp.databd.DirectorioViewModel;
import com.nahue.flickrapp.databd.Photosets;
import com.nahue.flickrapp.databd.Root;
import com.nahue.flickrapp.databd.USER_DATA;
import com.nahue.flickrapp.listadapter.AlbumListAdapter;


import javax.crypto.AEADBadTagException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecyclerAlbumFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecyclerAlbumFragment extends Fragment {
    //String necesario para acceder a los albumes, sino es usado sacarlo
    private static final String URL_SET = "https://www.flickr.com/services/rest/?method=flickr.photosets.getPhotos&api_key=80e44d9e765e01bef6d4e1294caaf54d&photoset_id=72157720019424378&user_id=193985255%40N05&format=json&nojsoncallback=1";
    AlbumListAdapter adapter;


    //Models de las entidades

    //Objeto que representa el acceso a los datos de los albumes
    DirectorioViewModel mDirectorioModel;


    public RecyclerAlbumFragment() {
        // Required empty public constructor
    }


    public static RecyclerAlbumFragment newInstance(String param1, String param2) {
        RecyclerAlbumFragment fragment = new RecyclerAlbumFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_recycler_album, container, false);
        adapter=new AlbumListAdapter(new AlbumListAdapter.DirectorioDiff(), new OnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent i = new Intent(getActivity(),DetalleActivity.class);
                Toast.makeText(getActivity(),"Intennt",Toast.LENGTH_SHORT).show();
                i.putExtra("url_album",adapter.getDir(position).url_fotos());
                i.putExtra("titulo", adapter.getDir(position).title );
                //i.putExtra("titulo", "Musica" );
                startActivity(i);
            }
        });
        //Metodo para inicializar el recycler
        initRecycler(v);

        //Metodo para cargar los albumes del usuario
        loadAlbumList();
        return v;

    }
    public  void initRecycler(View v){
        RecyclerView rec = (RecyclerView)v.findViewById(R.id.recAlbum);
        rec.setHasFixedSize(true);
        rec.setLayoutManager(new LinearLayoutManager(getActivity()));

        rec.setAdapter(adapter);
        mDirectorioModel=new ViewModelProvider(this).get(DirectorioViewModel.class);

        mDirectorioModel.getAllDirectorios().observe(getActivity(),directorios -> {
            adapter.submitList(directorios);
        });
    }
    //Metodo que llena la lista de albumes
    private void loadAlbumList(){
        //Si hay internet los traigo y reinicio la tabla
        if(MyApplication.INTERNET_CONEX==1){
            /*
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    mDirectorioModel.deleteAll();
                    adapter.notifyDataSetChanged();
                    loadAlbumListApi();
                }
            });
            */
            //mDirectorioModel.deleteAll();
            loadAlbumListApi();
        }
    }

	private void loadAlbumListApi(){
        String url_albums = getUrlAlbums();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_albums, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson g = new Gson();
                //Se utiliza una clase contenedora de la lista de albumes ya que eso devuelve la api
                //Debi crear estas clases auxiliares para representar los mas transparentes al json de la api
                Root root=g.fromJson(response,Root.class);
                //Se recorre cada album de la api y se lo agrega al adapter
                mDirectorioModel.deleteAll();
                for(int i=0;i<root.photosets.sets.length;i++){
                    mDirectorioModel.insert(CodecUtil.fromAPI2BD(root.photosets.sets[i]));
                }
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //No hay un metodo estandar para responder ante un error de red, solo avisar
                Toast.makeText(getContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        }
        );
        MyApplication.getSharedQueue().add(stringRequest);

	}

    //Metodo simple pero que evita recargar los metodos principales innecesariamente
    private String getUrlAlbums(){
        return USER_DATA.URL_REQUEST
                +USER_DATA.PHOTOSET_MET
                +USER_DATA.AND
                +USER_DATA.API_KEY
                +USER_DATA.AND
                +USER_DATA.USER_ID
                +USER_DATA.AND
                +USER_DATA.FORMAT
                +USER_DATA.AND
                +"nojsoncallback=1";

    }
}