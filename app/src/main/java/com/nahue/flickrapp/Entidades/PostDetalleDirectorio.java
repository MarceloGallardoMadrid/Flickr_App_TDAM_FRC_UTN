package com.nahue.flickrapp.Entidades;

import android.net.Uri;

import java.io.Serializable;

public class PostDetalleDirectorio  implements Serializable {
    private Long id;
    private String isprimary;
    private String secret;
    private String server;
    private String title;
    private String url;
    private String path;
    private String url_b; // paa buscar el archivo grande
    private Uri uri;

    public PostDetalleDirectorio(){
    }

    public PostDetalleDirectorio(Long id, String isprimary, String secret, String server, String title) {
        this.id = id;
        this.isprimary = isprimary;
        this.secret = secret;
        this.server = server;
        this.title = title;
    }

    public PostDetalleDirectorio(Photo photo){
        this.id = photo.getId();
        this.isprimary = photo.getIsprimary();
        this.secret = photo.getSecret();
        this.server = photo.getServer();
        this.title = photo.getTitle();
        this.url = "https://live.staticflickr.com/"+ this.server + "/" + this.id.toString() + "_"+ this.secret + "_w.jpg" ;
        this.url_b = "https://live.staticflickr.com/"+ this.server + "/" + this.id.toString() + "_"+ this.secret + "_b.jpg" ;
        this.path = "";
        //https://live.staticflickr.com/65535/51504317081_a59b2bb444_w.jpg
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIsprimary(String isprimary) {
        this.isprimary = isprimary;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUri(String uri) {
        this.url = uri;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getId() {
        return id;
    }

    public String getIsprimary() {
        return isprimary;
    }

    public String getSecret() {
        return secret;
    }

    public String getServer() {
        return server;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() { return url; }

    public String getPath() { return path;  }

    public String getUrl_b() { return url_b;    }

    public void setUrl_b(String uri_b) {this.url_b = url_b;    }
    public void setUrl(String url) {
        this.url = url;
    }

    public Uri getUri() {        return uri;    }

    public void setUri(Uri uri) {        this.uri = uri;    }
}
