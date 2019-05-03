package com.example.myapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.models.PetModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class PetsAdapter extends RecyclerView.Adapter<PetsAdapter.ViewHolder> {

    List<PetModel> list;
    Context context;

    public PetsAdapter(List<PetModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.petlistitemlayout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.petlayoutcinsname.setText("Pet Cinsi ="+list.get(position).getPetcins().toString());
        holder.petlayoutpetname.setText("Pet İsmi ="+list.get(position).getPetisim().toString());
        holder.petlayoutturname.setText("Pet Türü ="+list.get(position).getPettur().toString());

        Picasso.get().load(list.get(position).getPetresim()).into(holder.petlayoutpetimage);

    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView petlayoutpetname,petlayoutcinsname,petlayoutturname;
        CircleImageView petlayoutpetimage;
        // itemView ile Listview ın her elemanı için Layout ile oluşturduğumuz view tanımlanması işlemi gerçekleiecek
        public ViewHolder(View itemView) {

            super(itemView);
            petlayoutpetname = (TextView) itemView.findViewById(R.id.petlayoutpetname);
            petlayoutcinsname = (TextView) itemView.findViewById(R.id.petlayoutcinsname);
            petlayoutturname = (TextView) itemView.findViewById(R.id.petlayoutturname);
            petlayoutpetimage = (CircleImageView) itemView.findViewById(R.id.petlayoutpetimage);
        }
    }
}
