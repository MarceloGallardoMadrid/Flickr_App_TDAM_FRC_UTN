package com.nahue.flickrapp.Entidades;

import android.net.Uri;

public class EntidadDetalle {
    private String titulo;
    private int foto;
     private Uri uri;
    private Long photo_id;

    public EntidadDetalle(){
    }

    public EntidadDetalle(String titulo, int foto) {
        this.titulo = titulo;
        this.foto = foto;
    }

    public Long getPhoto_id() {     return photo_id;   }
    public void setPhoto_id(Long photo_id) {    this.photo_id = photo_id;    }

    public EntidadDetalle(String titulo, Uri uri, Long photo_id) {
        this.titulo = titulo;
        this.uri = uri;
        this.photo_id = photo_id;
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
