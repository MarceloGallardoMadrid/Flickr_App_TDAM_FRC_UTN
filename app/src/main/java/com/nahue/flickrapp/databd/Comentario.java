package com.nahue.flickrapp.databd;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Random;

@Entity(tableName="comentario")
public class Comentario {
	@Ignore
	public static int num=0;
	public static Comentario rnd(Random rnd){
		Comentario com=new Comentario();
		com.photo_id=String.valueOf(num);
		com.comment_id=String.valueOf(rnd.nextInt(6000));
		com.author_id=String.valueOf(rnd.nextInt(6000));
		com.authorname=String.valueOf(rnd.nextInt(6000));
		com.datecreate=String.valueOf(rnd.nextInt(6000));
		com.permalink=String.valueOf(rnd.nextInt(6000));
		com.comment=String.valueOf(rnd.nextInt(100000));

		num++;
		return com;
	}
	@PrimaryKey(autoGenerate=true)
	public int pk;

	public String photo_id;
	public String comment_id;
	public String author_id;
	public String authorname;
	public String datecreate;
	public String permalink;
	public String comment;

	@Override
	public String toString(){
		return "Comentario:{pk: "+pk+", id: "+photo_id+", comment: "+comment+"}";
	}
}
