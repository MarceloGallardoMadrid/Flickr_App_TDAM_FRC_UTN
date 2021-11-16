package com.nahue.flickrapp.Entidades;

public class RootObjectComentario {
    Comments comments;
    private String stat;

    // Getter Methods
    public Comments getComments() {
        return comments;
    }

    public String getStat() {
        return stat;
    }

    // Setter Methods
    public void setComments(Comments comments) {
        this.comments = comments;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }
}
