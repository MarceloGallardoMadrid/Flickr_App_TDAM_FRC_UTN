package com.nahue.flickrapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
    ArrayList<EntidadDetalle> entidades;
    OnItemClickListener onItemClickListener;
    public AlbumAdapter(ArrayList<EntidadDetalle> entidades,OnItemClickListener onItemClickListener){
        this.entidades=entidades;
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
        holder.titulo.setText(entidades.get(holder.getAbsoluteAdapterPosition()).getTitulo());
        holder.foto.setImageResource(entidades.get(holder.getAbsoluteAdapterPosition()).getFoto());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onClick(view,holder.getAbsoluteAdapterPosition());

            }
        });
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
            foto = (ImageView) itemView.findViewById(R.id.imgAlbum);
            this.onItemClickListener=onItemClickListener;
        }


    }
}
