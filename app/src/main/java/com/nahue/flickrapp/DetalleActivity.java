package com.nahue.flickrapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class DetalleActivity extends AppCompatActivity {

    RecyclerView recyclerViewDet;
    ArrayList<EntidadDetalle> listadetalle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        listadetalle = new ArrayList<>();

        recyclerViewDet = (RecyclerView) findViewById(R.id.recyclerViewDetalle);
        recyclerViewDet.setHasFixedSize(true);

        recyclerViewDet.setLayoutManager(new LinearLayoutManager(this));
        //recyclerViewD.setLayoutManager(new GridLayoutManager(this, 2));

        cargarDetalle();

        AdaptadorDetalle adapter = new AdaptadorDetalle(listadetalle);
        recyclerViewDet.setAdapter(adapter);

    }

    private void cargarDetalle() {
        listadetalle.add(new EntidadDetalle("Nueva Foto",R.drawable.foto1));
        listadetalle.add(new EntidadDetalle("Disfrutando el paisaje",R.drawable.foto2));
        listadetalle.add(new EntidadDetalle("Disfrutando la vida",R.drawable.foto3));
        listadetalle.add(new EntidadDetalle("Haciendo ejemplos",R.drawable.foto4));
        listadetalle.add(new EntidadDetalle("Metiéndole a full para que salga",R.drawable.foto5));
        listadetalle.add(new EntidadDetalle("Ver que pasa si es una descripción muy larga que no entra",R.drawable.foto6));
        listadetalle.add(new EntidadDetalle("Bien",R.drawable.foto7));
        listadetalle.add(new EntidadDetalle("Excelente",R.drawable.foto8));


    }
}