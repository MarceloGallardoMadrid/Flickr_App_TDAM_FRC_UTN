package com.nahue.flickrapp.databd;

//Clase con valores estaticos que permite un uso mas estandar de la api
//Puede ser reemplazado por sharedPreferences??
public class USER_DATA {
    public static String API_KEY="api_key=52db643e54fc246e7eb008a1ac80fa60";
    //public static String USER_ID="user_id=193985255%40N05";
    public static String USER_ID="user_id=193792370%40N07"; //Nuevo para probar y subir
    public static String FORMAT="format=json";
    public static String URL_REQUEST="https://www.flickr.com/services/rest/?";
    public static String PHOTOSET_MET="method=flickr.photosets.getList";
    public static String AND="&";
    public static String PHOTOS_MET="method=flickr.photosets.getPhotos";
    public static String COMENTARIOS_MET = "method=flickr.photos.comments.getList";
    public static String[] DIRECCION_MAIL = {"pablo.szyrko@gmail.com", "claudiojgonzalez@gmail.com"};
}
