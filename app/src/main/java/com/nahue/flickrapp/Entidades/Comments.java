package com.nahue.flickrapp.Entidades;

import com.nahue.flickrapp.databd.Foto;

import java.util.ArrayList;

public class Comments {
    private Long photo_id;
    ArrayList<Comment> comment = new ArrayList<Comment> ();


    public Long getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(Long photo_id) {
        this.photo_id = photo_id;
    }

   public ArrayList<Comment> getComment() {
        return comment;
    }

    public void setComment(ArrayList<Comment> comment) {
        this.comment = comment;
    }
}
