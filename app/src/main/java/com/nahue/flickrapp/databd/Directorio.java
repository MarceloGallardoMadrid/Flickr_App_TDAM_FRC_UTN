package com.nahue.flickrapp.databd;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName="directorio")
public class Directorio {
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
    public String date_title;
    public String descripcion;


}
