package com.nahue.flickrapp.databd;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Random;

@Entity(tableName="detalle_directorio")
public class DetalleDirectorio {
    @Ignore
    public static int num=0;
    public static DetalleDirectorio rnd(Random rnd){
        DetalleDirectorio foto=new DetalleDirectorio();
        foto.photo_id=String.valueOf(num);
        foto.photoset_id=String.valueOf(rnd.nextInt(5000));
        foto.secret=String.valueOf(rnd.nextInt(5000));
        foto.server_id=String.valueOf(rnd.nextInt(5000));
        foto.title=String.valueOf(rnd.nextInt(5000));
        foto.path_disco_big=String.valueOf(rnd.nextInt(5000));
        foto.path_disco_small=String.valueOf(rnd.nextInt(5000));
        foto.path_url=String.valueOf(rnd.nextInt(5000));

        num++;
        return foto;
    }

    @PrimaryKey(autoGenerate=true)
	public int pk;
	
	public String photoset_id;
    public String photo_id;
    public String secret;
    public String server_id;
    public String title;
	
    public int isprimary;
    public String path_disco_big;
    public String path_disco_small;
    public String path_url;

    @Ignore
    public Uri uri;

    @Ignore
    public String url_b;

    public Uri getUri() {        return uri;    }

    public void setUri(Uri uri) {        this.uri = uri;    }

    public String getUrl_b(){
        return url_b;
    }
    public void setUrl_b(String url_b){
        this.url_b=url_b;
    }

    @Override
    public String toString(){
        return "Foto:{pk: "+pk+", id: "+photo_id+", secret: "+secret+"}";
    }

    public String getTitle(){return title;}
}
