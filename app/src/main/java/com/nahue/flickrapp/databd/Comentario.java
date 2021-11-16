package com.nahue.flickrapp.databd;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Random;

@Entity(tableName="comentario")
public class Comentario {

	@PrimaryKey(autoGenerate=true)
	public int pk;

	public String photo_id;
	public String album_id;
	public String comment_id;
	public String author_id;
	public String realname;

	public String comment;

	@Override
	public String toString(){
		return "Comentario:{pk: "+pk+", id: "+photo_id+", comment: "+comment+"}";
	}
}
