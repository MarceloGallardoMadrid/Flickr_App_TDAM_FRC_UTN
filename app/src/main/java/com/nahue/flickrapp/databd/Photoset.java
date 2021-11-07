package com.nahue.flickrapp.databd;
import com.google.gson.annotations.SerializedName;
//Clase que representa un album, tiene los atributos minimos para mostrar fotos
public class Photoset {
    @SerializedName("id")
    public String id;
    @SerializedName("title")
    public Titulo title;
    @SerializedName("description")
    public Descripcion description;

    public String toString(){
        return url_fotos();
    }

    public String url_fotos(){
        return  USER_DATA.URL_REQUEST
                +USER_DATA.PHOTOS_MET
                +USER_DATA.AND
                +USER_DATA.API_KEY
                +USER_DATA.AND
                +"photoset_id="+id
                +USER_DATA.AND
                +USER_DATA.USER_ID
                +USER_DATA.AND
                +USER_DATA.FORMAT
                +USER_DATA.AND
                +"nojsoncallback=1";
    }
}
