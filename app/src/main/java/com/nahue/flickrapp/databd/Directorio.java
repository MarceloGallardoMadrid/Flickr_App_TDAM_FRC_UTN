package com.nahue.flickrapp.databd;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Random;

@Entity(tableName="directorio")
public class Directorio {
    @Ignore
    public static int num=0;
    public static Directorio rnd(Random rnd){
        Directorio dir=new Directorio();
        dir.id=String.valueOf(num);
        dir.secret=String.valueOf(rnd.nextInt(6000));
        dir.primary=String.valueOf(rnd.nextInt(6000));
        dir.server=String.valueOf(rnd.nextInt(6000));
        dir.farm=String.valueOf(rnd.nextInt(6000));
        dir.photos=String.valueOf(rnd.nextInt(6000));
        dir.count_views=String.valueOf(rnd.nextInt(6000));
        dir.secret=String.valueOf(rnd.nextInt(6000));
        dir.title=String.valueOf(rnd.nextInt(6000));
        dir.descripcion=String.valueOf(rnd.nextInt(6000));

        num++;
        return  dir;
    }
	@PrimaryKey(autoGenerate=true)
    public int pk;
	//Pense en que todos sean String para que sea sencillo su manipulacion
    //Pocas filas y lo convertimos en numeros on the fly
    public String id;
    public String secret;
    public String primary;
    public String server;
    public String farm;
    public String photos;
    public String count_views;
    public String title;
    public String descripcion;

    @Override
    public String toString(){
        return "Directorio:{pk: "+pk+", id: "+id+", secret: "+secret+"}";
    }
    @Override
    public boolean equals(Object obj){
        Directorio otro=(Directorio)obj;
        return otro.pk==this.pk;
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

