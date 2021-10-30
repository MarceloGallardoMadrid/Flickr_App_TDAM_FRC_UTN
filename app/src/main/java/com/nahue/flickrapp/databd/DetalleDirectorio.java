package com.nahue.flickrapp.databd;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName="detalle_directorio")
public class DetalleDirectorio {
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
    public String path_ul;

}
