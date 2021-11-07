package com.nahue.flickrapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nahue.flickrapp.Entidades.EntidadDetalle;

import java.util.ArrayList;

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
        initRecycler(v);
        return v;

    }
    public  void initRecycler(View v){
        ArrayList<EntidadDetalle> detalles=new ArrayList<>();
        cargarDetalle(detalles);
        RecyclerView rec = (RecyclerView)v.findViewById(R.id.recAlbum);
        rec.setHasFixedSize(true);

        rec.setLayoutManager(new LinearLayoutManager(getActivity()));

        RecyclerView.Adapter adapter = new AlbumAdapter(detalles, new OnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                //Toast.makeText(getActivity().getApplicationContext(), "Hice un clic: "+position, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity().getApplicationContext(),DetalleActivity.class));
            }
        });
        rec.setAdapter(adapter);

    }

    private void cargarDetalle(ArrayList<EntidadDetalle> detalles) {
        detalles.add(new EntidadDetalle("Nueva Foto",R.drawable.foto1));
        detalles.add(new EntidadDetalle("Disfrutando el paisaje",R.drawable.foto2));
        detalles.add(new EntidadDetalle("Disfrutando la vida",R.drawable.foto3));
        detalles.add(new EntidadDetalle("Haciendo ejemplos",R.drawable.foto4));
        detalles.add(new EntidadDetalle("Metiéndole a full para que salga",R.drawable.foto5));
        detalles.add(new EntidadDetalle("Ver que pasa si es una descripción muy larga que no entra",R.drawable.foto6));
        detalles.add(new EntidadDetalle("Bien",R.drawable.foto7));
        detalles.add(new EntidadDetalle("Excelente",R.drawable.foto8));
        //return detalles;
    }
}