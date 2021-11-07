package com.nahue.flickrapp.databd;
import com.google.gson.annotations.SerializedName;
//Clase que representa la lista de albumes
public class Photosets {

    @SerializedName("photoset")
    public Photoset[] sets;
    public String toString(){
        StringBuilder m= new StringBuilder();
        for (Photoset set : sets) {
            m.append(set.toString()).append("\n");
        }
        return m.toString();
    }
}
