package com.nahue.flickrapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nahue.flickrapp.Entidades.EntidadDetalle;

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
        public View v;
        OnItemClickListener onItemClickListener;
        public ViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            v=itemView;
            titulo = (TextView) itemView.findViewById(R.id.lblAlbumm);
            this.onItemClickListener=onItemClickListener;
        }

    }
}
