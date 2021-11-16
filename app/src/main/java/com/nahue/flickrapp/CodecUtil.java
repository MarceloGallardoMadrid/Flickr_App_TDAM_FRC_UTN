package com.nahue.flickrapp;

import com.nahue.flickrapp.databd.Descripcion;
import com.nahue.flickrapp.databd.Photoset;
import com.nahue.flickrapp.databd.Directorio;
import com.nahue.flickrapp.databd.Titulo;

//Clase que facilita la traduccion de api Objecto 2 BD Objecct
public class CodecUtil {
    /*DATOS DE LOS DIRECTORIOS
    *************API************
    *public String id;
    *public Titulo title;
    *public Descripcion description;
    *public String primary;
    *public String server;
    *public String farm;
    *public String photos;
    *public String count_views;
    *
    **********BD**********
    *public String id;
    *public String secret;
    *public String primary;
    *public String server;
    *public String farm;
    *public String photos;
    *public String count_views;
    *public String title;
    *public String descripcion;

    *
    */
    //Permite pasara bd a API
    public static com.nahue.flickrapp.databd.Photoset fromBD2Api(Directorio directorio){
        Photoset set= new Photoset();

        Descripcion desc=new Descripcion();
        desc.content=directorio.descripcion;
        set.description=desc;
        Titulo tit=new Titulo();
        tit.content=directorio.title;


        set.id=directorio.id;
        set.primary= directorio.primary;
        set.server= directorio.server;
        set.farm= directorio.farm;
        set.photos=directorio.photos;
        set.count_views= directorio.count_views;
        return set;
    }
    //Permite pasar de API a BD
    public static Directorio fromAPI2BD(com.nahue.flickrapp.databd.Photoset set){
        Directorio directorio=  new Directorio();
        directorio.id=set.id;
        directorio.primary=set.primary;
        directorio.server= set.server;
        directorio.farm= set.farm;
        directorio.photos= set.photos;
        directorio.count_views= set.count_views;;
        directorio.title=set.title.content;
        directorio.descripcion=set.description.content;
        return  directorio;
    }
}
