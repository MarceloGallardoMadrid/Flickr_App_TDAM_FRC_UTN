package com.nahue.flickrapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class FotoFinalActivity extends AppCompatActivity {

    TextView tvTitulo;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto_final);

        tvTitulo = (TextView) findViewById(R.id.tvTitulo);
        String titulo = getIntent().getStringExtra("titulo");
        Long photo_id = getIntent().getLongExtra("photo_id", 0);
        tvTitulo.setText(titulo);
        textView = (TextView) findViewById(R.id.textView);
        textView.setText(photo_id.toString());
    }
}