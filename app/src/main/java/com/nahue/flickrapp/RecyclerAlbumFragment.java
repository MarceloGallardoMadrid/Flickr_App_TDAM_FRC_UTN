package com.nahue.flickrapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
import com.nahue.flickrapp.databd.Photosets;
import com.nahue.flickrapp.databd.Root;
import com.nahue.flickrapp.databd.USER_DATA;


import javax.crypto.AEADBadTagException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecyclerAlbumFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecyclerAlbumFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String URL_SET = "https://www.flickr.com/services/rest/?method=flickr.photosets.getPhotos&api_key=80e44d9e765e01bef6d4e1294caaf54d&photoset_id=72157720019424378&user_id=193985255%40N05&format=json&nojsoncallback=1";
    AlbumAdapter adapter;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RecyclerAlbumFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecyclerAlbumFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecyclerAlbumFragment newInstance(String param1, String param2) {
        RecyclerAlbumFragment fragment = new RecyclerAlbumFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_recycler_album, container, false);
        adapter=new AlbumAdapter(new OnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent i = new Intent(getActivity(),DetalleActivity.class);
                i.putExtra("url_album",adapter.entidades.get(position).url_fotos());
                i.putExtra("titulo", adapter.entidades.get(position).title.content );
                //i.putExtra("titulo", "Musica" );
                startActivity(i);
            }
        });
        //Metodo para cargar los albumes del usuario
        loadAlbumList();
        //Metodo para inicializar el recycler
        initRecycler(v);
        return v;

    }
    public  void initRecycler(View v){

        RecyclerView rec = (RecyclerView)v.findViewById(R.id.recAlbum);
        rec.setHasFixedSize(true);

        rec.setLayoutManager(new LinearLayoutManager(getActivity()));

        rec.setAdapter(adapter);

    }
	private void loadAlbumList(){


        String url_albums = getUrlAlbums();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_albums, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson g = new Gson();
                //Se utiliza una clase contenedora de la lista de albumes ya que eso devuelve la api
                //Debi crear estas clases auxiliares para representar los mas transparentes al json de la api
                Root root=g.fromJson(response,Root.class);
                //Se recorre cada album de la api y se lo agrega al adapter
                for(int i=0;i<root.photosets.sets.length;i++){
                    adapter.addPhotoset(root.photosets.sets[i]);

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
    /*private void cargarDetalle(ArrayList<EntidadDetalle> detalles) {
        detalles.add(new EntidadDetalle("Nueva Foto",R.drawable.foto1));
        detalles.add(new EntidadDetalle("Disfrutando el paisaje",R.drawable.foto2));
        detalles.add(new EntidadDetalle("Disfrutando la vida",R.drawable.foto3));
        detalles.add(new EntidadDetalle("Haciendo ejemplos",R.drawable.foto4));
        detalles.add(new EntidadDetalle("Metiéndole a full para que salga",R.drawable.foto5));
        detalles.add(new EntidadDetalle("Ver que pasa si es una descripción muy larga que no entra",R.drawable.foto6));
        detalles.add(new EntidadDetalle("Bien",R.drawable.foto7));
        detalles.add(new EntidadDetalle("Excelente",R.drawable.foto8));
        //return detalles;
    }*/
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