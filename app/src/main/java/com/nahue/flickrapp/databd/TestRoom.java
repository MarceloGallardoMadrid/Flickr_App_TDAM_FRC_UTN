package com.nahue.flickrapp.databd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nahue.flickrapp.R;

import java.util.List;
import java.util.Random;

public class TestRoom extends AppCompatActivity {

    TextView txtTest;
    Random rnd;
    //Objeto que representa el acceso a los datos de los comentarios
    ComentarioViewModel mComentarioModel;
    //Objeto que representa el acceso a los datos de las fotos
    DetalleDirectorioViewModel mDetalleDirectorioModel;
    //Objeto que representa el acceso a los datos de los albumes
    DirectorioViewModel mDirectorioModel;

    //No se porque lo comente seguro por referencia, borrar si no me acuer
    /*
    mWordViewModel=new ViewModelProvider(this).get(WordViewModel.class);
        mWordViewModel.getAllWords().observe(this,words -> {
        adapter.submitList(words);
    });

     */
    //Estos string representarian los recyclerView
    String ComentariosStack;
    String DirectoriosStack;
    String FotosStack;

    //Objetos que funcionan como callbacks ante un cambio en la lista
    //Los adapter de los recycler son observer
    Observer<List<Comentario>> comObs;
    Observer<List<Directorio>> dirObs;
    Observer<List<DetalleDirectorio>> fotoObs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_room);
        rnd=new Random();
        //Metodo para hacer referencia a los elementos visuales y darle logica a los botones
        initView();
        //Metodo para tener acceso a los datos
        initRepos();
    }

    private void initRepos() {
        mDetalleDirectorioModel=new ViewModelProvider(this).get(DetalleDirectorioViewModel.class);
        mDirectorioModel=new ViewModelProvider(this).get(DirectorioViewModel.class);
        mComentarioModel=new ViewModelProvider(this).get(ComentarioViewModel.class);
        comObs=new Observer<List<Comentario>>() {
            @Override
            public void onChanged(List<Comentario> comentarios) {
                ComentariosStack="";
                for(Comentario com:comentarios){
                    ComentariosStack+=com+"\n";
                }
            }
        };
        dirObs=new Observer<List<Directorio>>() {
            @Override
            public void onChanged(List<Directorio> directorios) {
                DirectoriosStack="";
                for(Directorio dir:directorios){
                    DirectoriosStack+=dir+"\n";
                }
            }
        };
        fotoObs=new Observer<List<DetalleDirectorio>>() {
            @Override
            public void onChanged(List<DetalleDirectorio> detalleDirectorios) {
                FotosStack="";
                for (DetalleDirectorio foto:detalleDirectorios){
                    FotosStack+=foto+"\n";
                }
            }
        };
    }

    private  void initView(){
        txtTest=(TextView) findViewById(R.id.txtTest);
        Button btnAddDir =(Button) findViewById(R.id.btnGuardarDir);
        btnAddDir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarDirectorio();
            }
        });
        Button btnAddCom =(Button) findViewById(R.id.btnGuardarCom);
        btnAddCom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarComentario();
            }
        });
        Button btnAddFoto =(Button) findViewById(R.id.btnGuardarFoto);
        btnAddFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarFoto();
            }
        });
        Button btnVerDir =(Button) findViewById(R.id.btnVerDir);
        btnVerDir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verDirectorios();
            }
        });
        Button btnVerCom =(Button) findViewById(R.id.btnVerCom);
        btnVerCom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verComentarios();
            }
        });
        Button btnVerFotos =(Button) findViewById(R.id.btnVerFoto);
        btnVerFotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verFotos();
            }
        });

    }
    private void guardarDirectorio(){
        Directorio dir=Directorio.rnd(rnd);
        mDirectorioModel.insert(dir);
        txtTest.setText("Se guardo: "+dir);
    }
    private void guardarComentario(){
        Comentario com=Comentario.rnd(rnd);
        mComentarioModel.insert(com);
        txtTest.setText("Se guardo: "+com);
    }
    private void guardarFoto(){
        DetalleDirectorio fot=DetalleDirectorio.rnd(rnd);
        mDetalleDirectorioModel.insert(fot);
        txtTest.setText("Se guardo: "+fot);
    }
    private  void verDirectorios(){

        mDirectorioModel.getAllDirectorios().observe(this,dirObs);
        txtTest.setText(DirectoriosStack);
    }
    private  void verFotos(){
        mDetalleDirectorioModel.getAllFotos().observe(this,fotoObs);
        txtTest.setText(FotosStack);
    }
    private  void verComentarios(){
        mComentarioModel.getAllComentarios().observe(this,comObs);
        txtTest.setText(ComentariosStack);
    }

}