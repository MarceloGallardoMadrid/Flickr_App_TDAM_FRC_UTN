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
        dir.count_commments=String.valueOf(rnd.nextInt(6000));
        dir.date_create=String.valueOf(rnd.nextInt(6000));
        dir.date_update=String.valueOf(rnd.nextInt(6000));
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
    public String count_commments;
    public String date_create;
    public String date_update;
    public String title;
    public String descripcion;

    @Override
    public String toString(){
        return "Directorio:{pk: "+pk+", id: "+id+", secret: "+secret+"}";
    }
}
