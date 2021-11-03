package com.nahue.flickrapp.Entidades;

public class Photo {
    private Long id;
    private String secret;
    private String server;
    private float farm;
    private String title;
    private String isprimary;
    private float ispublic;
    private float isfriend;
    private float isfamily;

    public Photo(Long id, String secret, String server, String title, String isprimary) {
        this.id = id;
        this.secret = secret;
        this.server = server;
        this.title = title;
        this.isprimary = isprimary;

    }


    // Getter Methods

    public Long getId() {
        return id;
    }

    public String getSecret() {
        return secret;
    }

    public String getServer() {
        return server;
    }

    public float getFarm() {
        return farm;
    }

    public String getTitle() {
        return title;
    }

    public String getIsprimary() {
        return isprimary;
    }

    public float getIspublic() {
        return ispublic;
    }

    public float getIsfriend() {
        return isfriend;
    }

    public float getIsfamily() {
        return isfamily;
    }

    // Setter Methods

    public void setId(Long id) {
        this.id = id;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public void setFarm(float farm) {
        this.farm = farm;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIsprimary(String isprimary) {
        this.isprimary = isprimary;
    }

    public void setIspublic(float ispublic) {
        this.ispublic = ispublic;
    }

    public void setIsfriend(float isfriend) {
        this.isfriend = isfriend;
    }

    public void setIsfamily(float isfamily) {
        this.isfamily = isfamily;
    }
}
