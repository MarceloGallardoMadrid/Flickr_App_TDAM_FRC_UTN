package com.nahue.flickrapp.listadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.nahue.flickrapp.OnItemClickListener;
import com.nahue.flickrapp.R;
import com.nahue.flickrapp.databd.Directorio;

public class AlbumListAdapter extends ListAdapter<Directorio,AlbumListAdapter.ViewHolder> {
    public OnItemClickListener onItemClickListener;
    public AlbumListAdapter(@NonNull DiffUtil.ItemCallback<Directorio> diffCalback,OnItemClickListener onItemClickListener){
        super(diffCalback);
        this.onItemClickListener=onItemClickListener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycler_album_item,parent,false);
        ViewHolder vh = new ViewHolder(v,onItemClickListener);
        return vh;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,int position){
        Directorio current=getItem(position);
        holder.printInfo(current);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onClick(view,holder.getAdapterPosition());
            }
        });
        //Toast.makeText(holder.v.getContext(), current.toString(),Toast.LENGTH_SHORT).show();
    }
    public int getSize(){
        return getItemCount();
    }

    public Directorio getDir(int position){
        return getItem(position);
    }
    public static class DirectorioDiff extends DiffUtil.ItemCallback<Directorio>{
        @Override
        public boolean areItemsTheSame(@NonNull Directorio oldItem,@NonNull Directorio newItem){
            return oldItem==newItem;
        }
        @Override
        public boolean areContentsTheSame(@NonNull Directorio oldItem,@NonNull Directorio newItem){
            return oldItem.pk==newItem.pk;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View v;
        OnItemClickListener itemClickListener;
        public ViewHolder(View v,OnItemClickListener onItemClickListener){
            super(v);
            this.v=v;
            itemClickListener=onItemClickListener;
        }
        public void printInfo(Directorio dir){
            TextView lblNombre=(TextView)v.findViewById(R.id.lblAlbumm);
            lblNombre.setText(dir.title);
            TextView lblDescripcion=(TextView)v.findViewById(R.id.lblAlbummDescripcion);
            lblDescripcion.setText(dir.descripcion);
            TextView lblCounts=(TextView)v.findViewById(R.id.lblAlbummCounts);
            lblCounts.setText(dir.count_views);
        }

    }
}
