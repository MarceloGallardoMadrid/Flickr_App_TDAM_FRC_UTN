package com.nahue.flickrapp.databd;

import com.google.gson.annotations.SerializedName;

//Clase contenedora de la lista de albumes
public class Root {
    @SerializedName("photosets")
    public Photosets photosets;
}
