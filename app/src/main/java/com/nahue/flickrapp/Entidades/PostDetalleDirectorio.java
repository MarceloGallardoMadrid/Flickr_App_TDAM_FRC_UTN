package com.nahue.flickrapp.Entidades;

public class PostDetalleDirectorio {
    private Long id;
    private String isprimary, secret, server, title, uri, path;

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
        this.uri = "https://live.staticflickr.com/"+ this.server + "/" + this.id.toString() + "_"+ this.secret + "_w.jpg" ;
        this.path = "";
        //https://live.staticflickr.com/65535/51504317081_a59b2bb444_w.jpg
    }

   /* public void setId(id) {
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
*/
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
}
