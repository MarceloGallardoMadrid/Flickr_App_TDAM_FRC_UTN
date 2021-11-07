package com.nahue.flickrapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.nahue.flickrapp.Entidades.EntidadDetalle;
import com.nahue.flickrapp.databd.TestRoom;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<EntidadDetalle> listadetalle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listadetalle=new ArrayList<>();
        cargarDetalle();
        RecyclerAlbumFragment frag = new RecyclerAlbumFragment();
        FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container,frag);
        transaction.commit();
        findViewById(R.id.btnRoom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),TestRoom.class));
            }
        });

    }
    private void onCreateRecycler(){
        listadetalle=new ArrayList<>();
        cargarDetalle();
        //RecyclerView rec =(RecyclerView)findViewById(R.id.rec);
        RecyclerView rec =new RecyclerView(this);
        rec.setHasFixedSize(true);

        rec.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView.Adapter adapter = new AlbumAdapter(listadetalle, new OnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                Toast.makeText(getApplicationContext(), "Hice un clic: "+position, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),DetalleActivity.class));
            }
        });
        rec.setAdapter(adapter);
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