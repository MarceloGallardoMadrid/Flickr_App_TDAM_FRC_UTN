package com.nahue.flickrapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nahue.flickrapp.Entidades.EntidadComentario;

import java.util.ArrayList;

public class ComentarioAdapter extends RecyclerView.Adapter<ComentarioAdapter.ViewHolderComentario> {

    ArrayList<EntidadComentario> listaComentario;

    public ComentarioAdapter(ArrayList<EntidadComentario> listaComentario){
        this.listaComentario = listaComentario;
    }

    //@NonNull
    @Override
    public ComentarioAdapter.ViewHolderComentario onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycler_comentario, parent, false);
        return new ComentarioAdapter.ViewHolderComentario(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComentarioAdapter.ViewHolderComentario holder, int position) {
        holder.comentario.setText(listaComentario.get(holder.getAbsoluteAdapterPosition()).getComentario());
    }

    @Override
    public int getItemCount() {
        return listaComentario.size();
    }

    public class ViewHolderComentario extends RecyclerView.ViewHolder {
        TextView comentario;
        public View v;

        public ViewHolderComentario(@NonNull View itemView) {
            super(itemView);
            v = itemView;
            comentario = (TextView) itemView.findViewById(R.id.textViewComentario);
        }
    }
}
