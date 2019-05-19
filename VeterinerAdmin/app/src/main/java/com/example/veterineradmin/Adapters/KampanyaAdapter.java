package com.example.veterineradmin.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veterineradmin.Models.KampanyaModel;
import com.example.veterineradmin.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class KampanyaAdapter extends RecyclerView.Adapter<KampanyaAdapter.ViewHolder> {

    List<KampanyaModel> list;
    Context context;

    public KampanyaAdapter(List<KampanyaModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.kampanyaitemlayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.kampanyaBaslikText.setText(list.get(position).getBaslik().toString());
        holder.kampanyatext.setText(list.get(position).getText().toString());
        Picasso.get().load(list.get(position).getResim()).resize(200,200).into(holder.kampanyaImageView);

    }


    @Override
    public int getItemCount() {

        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView kampanyaBaslikText, kampanyatext;
        ImageView kampanyaImageView;

        // itemView ile Listview ın her elemanı için Layout ile oluşturduğumuz view tanımlanması işlemi gerçekleiecek

        public ViewHolder(View itemView) {
            super(itemView);
            kampanyatext =(TextView) itemView.findViewById(R.id.kampanyatext);
            kampanyaBaslikText =(TextView) itemView.findViewById(R.id.kampanyaBaslikText);
            kampanyaImageView =(ImageView) itemView.findViewById(R.id.kampanyaImageView);

        }
    }
}
