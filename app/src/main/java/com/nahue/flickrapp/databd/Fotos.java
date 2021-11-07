package com.nahue.flickrapp.databd;
import com.google.gson.annotations.SerializedName;
//Clase que representa la lista de fotos de un album
public class Fotos {
   @SerializedName("photo")
    public Foto[] fotos;
}
