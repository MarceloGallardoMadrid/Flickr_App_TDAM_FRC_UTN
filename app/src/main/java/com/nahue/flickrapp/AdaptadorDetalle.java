package com.nahue.flickrapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorDetalle extends RecyclerView.Adapter<AdaptadorDetalle.ViewHolderDetalle> {

    ArrayList<EntidadDetalle> listaDetalle;

    public AdaptadorDetalle(ArrayList<EntidadDetalle> listaDetalle) {
        this.listaDetalle = listaDetalle;
    }

    //@NonNull
    @Override
    public ViewHolderDetalle onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // hay que inflar el item list a través de un view
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycler_detalle_item,null, false);
        return new ViewHolderDetalle(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDetalle holder, int position) {
        //se llenan los datos desde acá
        holder.titulo.setText(listaDetalle.get(position).getTitulo());
        holder.foto.setImageResource(listaDetalle.get(position).getFoto());
    }

    @Override
    public int getItemCount() {
        return listaDetalle.size();
    }

    public class ViewHolderDetalle extends RecyclerView.ViewHolder {
        //hace referencia a los componentes gráficos
        TextView titulo;
        ImageView foto;

        public ViewHolderDetalle(@NonNull View itemView) {
            super(itemView);
            titulo = (TextView) itemView.findViewById(R.id.textViewTitulo);
            foto = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
