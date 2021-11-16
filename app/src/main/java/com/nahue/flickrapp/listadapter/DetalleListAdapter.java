package com.nahue.flickrapp.listadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.nahue.flickrapp.OnItemClickListener;
import com.nahue.flickrapp.R;
import com.nahue.flickrapp.databd.DetalleDirectorio;
import com.nahue.flickrapp.databd.Directorio;
import com.nahue.flickrapp.databd.Photoset;

public class DetalleListAdapter extends ListAdapter<DetalleDirectorio, DetalleListAdapter.ViewHolder> {
    OnItemClickListener onItemClickListener;


    public DetalleListAdapter(@NonNull DiffUtil.ItemCallback<DetalleDirectorio> diffCalback,OnItemClickListener onItemClickListener){
        super(diffCalback);
        this.onItemClickListener=onItemClickListener;
    }
    @NonNull
    @Override
    public DetalleListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycler_detalle_item,parent,false);
        DetalleListAdapter.ViewHolder vh = new DetalleListAdapter.ViewHolder(v,onItemClickListener);
        return vh;
    }
    @Override
    public void onBindViewHolder(@NonNull DetalleListAdapter.ViewHolder holder, int position){


        /*
        holder.titulo.setText(listaDetalle.get(holder.getAbsoluteAdapterPosition()).getTitle());
        holder.foto.setImageURI(listaDetalle.get(holder.getAbsoluteAdapterPosition()).getUri());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onClick(view, holder.getAbsoluteAdapterPosition());
            }
        });

         */
        DetalleDirectorio current=getItem(holder.getAbsoluteAdapterPosition());
        holder.titulo.setText(current.getTitle());
        holder.foto.setImageURI(current.getUri());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onClick(view, holder.getAbsoluteAdapterPosition());
            }
        });

    }
    public DetalleDirectorio getFoto(int pos){
        return getItem(pos);
    }
    public static class DetalleDirectorioDiff extends DiffUtil.ItemCallback<DetalleDirectorio>{
        @Override
        public boolean areItemsTheSame(@NonNull DetalleDirectorio oldItem,@NonNull DetalleDirectorio newItem){
            return oldItem==newItem;
        }
        @Override
        public boolean areContentsTheSame(@NonNull DetalleDirectorio oldItem,@NonNull DetalleDirectorio newItem){
            return oldItem.pk==newItem.pk;
        }
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        //hace referencia a los componentes gráficos
        TextView titulo;
        ImageView foto;
        public View v;
        OnItemClickListener onItemClickListener;

        public ViewHolder(View itemView,OnItemClickListener onItemClickListener){
            super(itemView);
            v=itemView;
            titulo = (TextView) itemView.findViewById(R.id.textViewTitulo);
            foto = (ImageView) itemView.findViewById(R.id.imageView);
            this.onItemClickListener = onItemClickListener;
        }
    }
}
