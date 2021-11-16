package com.nahue.flickrapp.databd;
import com.google.gson.annotations.SerializedName;
//Clase que representa la foto de un album y que puede generar su propia url de foto
public class Foto {
    @SerializedName("server")
    public String server;
    @SerializedName("id")
    public String id;
    @SerializedName("secret")
    public String secret;
    @SerializedName("title")
    public String title;
    public String url_foto(){
        return "https://live.staticflickr.com/"+server+"/"+id+"_"+secret+"_w.jpg";
    }
}
