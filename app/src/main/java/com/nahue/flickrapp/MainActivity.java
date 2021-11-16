package com.nahue.flickrapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
        MyApplication.INTERNET_CONEX=verifyConection();
        notifyLackOfInternet(MyApplication.INTERNET_CONEX);
        listadetalle=new ArrayList<>();

        RecyclerAlbumFragment frag = new RecyclerAlbumFragment();
        FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container,frag);
        transaction.commit();

    }

    //Metodo encargado de avisar si hay internet
    private void notifyLackOfInternet(int internetConex) {
        //Notificar
        NotificationUtil.notifyNoInternet(internetConex);
    }

    private int verifyConection() {
        return NotificationUtil.verifyConection(this);

    }

}