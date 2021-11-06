package com.nahue.flickrapp;

import android.net.Uri;
import android.widget.ImageView;

public class EntidadDetalle {
    private String titulo;
    private int foto;
     private Uri uri;

    public EntidadDetalle(){
    }

    public EntidadDetalle(String titulo, int foto) {
        this.titulo = titulo;
        this.foto = foto;
    }

    public EntidadDetalle(String titulo, Uri uri) {
        this.titulo = titulo;
        this.uri = uri;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}
