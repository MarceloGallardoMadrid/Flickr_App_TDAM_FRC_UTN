package com.nahue.flickrapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nahue.flickrapp.Entidades.Comment;
import com.nahue.flickrapp.Entidades.EntidadComentario;
import com.nahue.flickrapp.Entidades.PostDetalleDirectorio;

import java.util.ArrayList;

public class ComentarioAdapter extends RecyclerView.Adapter<ComentarioAdapter.ViewHolderComentario> {

    //ArrayList<EntidadComentario> listaComentario;
    ArrayList<Comment> listaComentario;

    /*public ComentarioAdapter(ArrayList<EntidadComentario> listaComentario){
        this.listaComentario = listaComentario;
    }
    */

    public ComentarioAdapter(ArrayList<Comment> listaComentario){
        this.listaComentario = new ArrayList<>();
    }

    public ComentarioAdapter (){
        this.listaComentario = new ArrayList<>();
    }

    public void addlista(Comment p){
        listaComentario.add(p);
    }

    //@NonNull
    @Override
    public ComentarioAdapter.ViewHolderComentario onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycler_comentario, parent, false);
        return new ComentarioAdapter.ViewHolderComentario(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComentarioAdapter.ViewHolderComentario holder, int position) {
        //holder.comentario.setText(listaComentario.get(holder.getAbsoluteAdapterPosition()).getComentario());
        holder.comentario.setText(listaComentario.get(holder.getAbsoluteAdapterPosition()).get_content());
        holder.autor.setText(listaComentario.get(holder.getAbsoluteAdapterPosition()).getRealname());
    }

    @Override
    public int getItemCount() {
        return listaComentario.size();
    }

    public class ViewHolderComentario extends RecyclerView.ViewHolder {
        TextView comentario;
        TextView autor;
        public View v;

        public ViewHolderComentario(@NonNull View itemView) {
            super(itemView);
            v = itemView;
            comentario = (TextView) itemView.findViewById(R.id.textViewComentario);
            autor = (TextView) itemView.findViewById(R.id.textViewAutor);
        }
    }
}
