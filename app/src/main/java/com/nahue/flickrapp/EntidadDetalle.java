package com.nahue.flickrapp;

public class EntidadDetalle {
    private String titulo;
    private int foto;

    public EntidadDetalle(){

    }

    public EntidadDetalle(String titulo, int foto) {
        this.titulo = titulo;
        this.foto = foto;
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
}
