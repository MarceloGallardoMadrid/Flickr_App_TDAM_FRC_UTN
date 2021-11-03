package com.nahue.flickrapp.Entidades;

import java.util.ArrayList;

public class Photoset {
    private String id;
    private String primary;
    private String owner;
    private String ownername;
    ArrayList<Photo> photo = new ArrayList<Photo> ();
    private float page;
    private float per_page;
    private float perpage;
    private float pages;
    private String title;
    private float total;

    // Getter Methods
    public ArrayList<Photo> getPhoto() {
        return photo;
    }
    public void setPhoto(ArrayList<Photo> photo) {
        this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public String getPrimary() {
        return primary;
    }

    public String getOwner() {
        return owner;
    }

    public String getOwnername() {
        return ownername;
    }

    public float getPage() {
        return page;
    }

    public float getPer_page() {
        return per_page;
    }

    public float getPerpage() {
        return perpage;
    }

    public float getPages() {
        return pages;
    }

    public String getTitle() {
        return title;
    }

    public float getTotal() {
        return total;
    }

    // Setter Methods

    public void setId(String id) {
        this.id = id;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    public void setPage(float page) {
        this.page = page;
    }

    public void setPer_page(float per_page) {
        this.per_page = per_page;
    }

    public void setPerpage(float perpage) {
        this.perpage = perpage;
    }

    public void setPages(float pages) {
        this.pages = pages;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
