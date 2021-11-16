package com.nahue.flickrapp.Entidades;

public class RootObject {
    Photoset photoset;
    private String stat;

    // Getter Methods
    public Photoset getPhotoset() {
        return photoset;
    }

    public String getStat() {
        return stat;
    }

    // Setter Methods
    public void setPhotoset(Photoset photoset) {
        this.photoset = photoset;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }
}
