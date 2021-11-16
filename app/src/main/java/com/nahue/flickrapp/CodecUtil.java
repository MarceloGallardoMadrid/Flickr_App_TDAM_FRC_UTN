package com.nahue.flickrapp;

import android.net.Uri;

import com.nahue.flickrapp.Entidades.Comment;
import com.nahue.flickrapp.databd.Comentario;
import com.nahue.flickrapp.databd.Descripcion;
import com.nahue.flickrapp.databd.DetalleDirectorio;
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

    /**
     *
     * @param foto: Objeto que se obtuvo de la API
     *           Existe "impedancia" entre al API y la BD
     * @return DetalleDirectorio: Clase que representa el almaccenamiento de una foto en la bd
     */
    public static DetalleDirectorio fotoFromAPI2BD(com.nahue.flickrapp.Entidades.PostDetalleDirectorio foto,String id_album){
        DetalleDirectorio dd = new DetalleDirectorio();
        dd.photoset_id=id_album;
        dd.photo_id=String.valueOf(foto.id);
        dd.secret=foto.secret;
        dd.server_id= foto.server;
        dd.title= foto.title;
        dd.isprimary=1;
        dd.path_disco_big= foto.path;
        dd.path_disco_small="";
        dd.setUri(foto.getUri());
        dd.url_b=foto.getUrl_b();
        dd.path_url= foto.url;

        return dd;
    }

    /**
     *
     * @param apiCom:Clase que representa el comentario obtenido de la api
     * @return devuelve un comentario que puede ser grabado en la bd
     */
    /*

    ************Comment****************
        private String id;
    private String realname;
    private String _content;

    *************Comentario************
    public String photo_id;
	public String album_id;
	public String comment_id;
	public String realname;

	public String comment;

     */
    public static Comentario comFromAPI2BD(Comment apiCom,String foto_id,String album_id){
        Comentario com =new Comentario();
        com.photo_id=foto_id;
        com.album_id=album_id;
        com.comment_id=apiCom.getId();
        com.realname=apiCom.getRealname();
        return com;
    }
}
