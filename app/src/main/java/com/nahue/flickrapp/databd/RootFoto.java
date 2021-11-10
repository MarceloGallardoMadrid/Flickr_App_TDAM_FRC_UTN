package com.nahue.flickrapp.databd;

import com.google.gson.annotations.SerializedName;

//Clase contenedora de la lista de fotos
public class RootFoto {
    @SerializedName("photoset")
    public Fotos fotos;
}
