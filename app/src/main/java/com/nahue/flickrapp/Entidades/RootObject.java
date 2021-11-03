package com.nahue.flickrapp.Entidades;

public class RootObject {
    Photoset PhotosetObject;
    private String stat;

    // Getter Methods
    public Photoset getPhotoset() {
        return PhotosetObject;
    }

    public String getStat() {
        return stat;
    }

    // Setter Methods
    public void setPhotoset(Photoset photosetObject) {
        this.PhotosetObject = photosetObject;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }
}
