package com.nahue.flickrapp;

import android.app.Application;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class MyApplication extends Application {

    private static RequestQueue queue;
    private static ImageLoader imageLoader;

    //Atributo global de la aplicacion que representa la conexion a internet
    //0 para no,1 para si
    public static int INTERNET_CONEX=0;

    @Override
    public void onCreate() {
        super.onCreate();
        queue = Volley.newRequestQueue(this);
        imageLoader = new ImageLoader(queue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
    }

    public static RequestQueue getSharedQueue() {
        return queue;
    }

    public static ImageLoader getImageLoader() {
        return imageLoader;
    }
}