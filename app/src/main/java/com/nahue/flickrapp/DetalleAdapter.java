package com.nahue.flickrapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nahue.flickrapp.Entidades.EntidadDetalle;

import java.util.ArrayList;

public class DetalleAdapter extends RecyclerView.Adapter<DetalleAdapter.ViewHolderDetalle> {

    ArrayList<EntidadDetalle> listaDetalle;
    OnItemClickListener onItemClickListener;

    public DetalleAdapter(ArrayList<EntidadDetalle> listaDetalle, OnItemClickListener onItemClickListener) {
        this.listaDetalle = listaDetalle;
        this.onItemClickListener = onItemClickListener;
    }

    //@NonNull
    @Override
    public ViewHolderDetalle onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // hay que inflar el item list a través de un view
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycler_detalle_item,null, false);
        return new DetalleAdapter.ViewHolderDetalle(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDetalle holder, int pos) {
        //se llenan los datos desde acá
        holder.titulo.setText(listaDetalle.get(holder.getAbsoluteAdapterPosition()).getTitulo());
        holder.foto.setImageURI(listaDetalle.get(holder.getAbsoluteAdapterPosition()).getUri());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onClick(view, holder.getAbsoluteAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaDetalle.size();
    }

    public class ViewHolderDetalle extends RecyclerView.ViewHolder {
        //hace referencia a los componentes gráficos
        TextView titulo;
        ImageView foto;
        public View v;
        OnItemClickListener onItemClickListener;

        public ViewHolderDetalle(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            v=itemView;
            titulo = (TextView) itemView.findViewById(R.id.textViewTitulo);
            foto = (ImageView) itemView.findViewById(R.id.imageView);
            this.onItemClickListener = onItemClickListener;
        }
    }
}
