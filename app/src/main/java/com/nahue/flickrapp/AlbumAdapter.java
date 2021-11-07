package com.nahue.flickrapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//Volleyyy/////////////
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.nahue.flickrapp.databd.Fotos;
import com.nahue.flickrapp.databd.Photoset;
import com.nahue.flickrapp.databd.RootFoto;


public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
    ArrayList<Photoset> entidades;
    OnItemClickListener onItemClickListener;
    public AlbumAdapter(OnItemClickListener onItemClickListener){
        this.entidades=new ArrayList<>();
        this.onItemClickListener=onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycler_album_item,parent,false);
        ViewHolder vh = new ViewHolder(v,onItemClickListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,int pos) {
        Photoset set=entidades.get(holder.getAbsoluteAdapterPosition());
        holder.titulo.setText(set.title.content);
        holder.imprimirImg(set);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onClick(view,holder.getAbsoluteAdapterPosition());

            }
        });
    }
    public  void addPhotoset(Photoset p){
        entidades.add(p);
    }

    @Override
    public int getItemCount() {
        return entidades.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //hace referencia a los componentes gráficos
        TextView titulo;
        ImageView foto;
        public View v;
        OnItemClickListener onItemClickListener;
        public ViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            v=itemView;
            titulo = (TextView) itemView.findViewById(R.id.lblAlbumm);
            foto=(ImageView)itemView.findViewById(R.id.imgAlbum);
            this.onItemClickListener=onItemClickListener;
        }
        //Metodo "sobrecargado" para imprimir una foto a partir de los datos de un album osea photoset
        public void imprimirImg(Photoset set) {
            Gson g = new Gson();
            StringRequest stringRequest= new StringRequest(Request.Method.GET, set.url_fotos(), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    RootFoto rfotos=g.fromJson(response,RootFoto.class);
                    imprimirImg(rfotos.fotos.fotos[0].url_foto());
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(v.getContext(), "Imagen primera no sale bien",Toast.LENGTH_SHORT).show();
                }
            }
            );
            MyApplication.getSharedQueue().add(stringRequest);
        }
        //Metodo generico para dibujar una imagen con una url
		private void imprimirImg(String url){
            MyApplication.getImageLoader().get(url, new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    foto.setImageBitmap(response.getBitmap());
                }

                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(v.getContext(),"Imagen de mierda",Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
