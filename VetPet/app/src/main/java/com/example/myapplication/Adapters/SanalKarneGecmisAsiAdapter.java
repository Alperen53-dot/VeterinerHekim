package com.example.myapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.Models.AsiModel;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class SanalKarneGecmisAsiAdapter extends RecyclerView.Adapter<SanalKarneGecmisAsiAdapter.ViewHolder> {

    List<AsiModel> list;
    Context context;

    public SanalKarneGecmisAsiAdapter(List<AsiModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sanalkarnegecmislayout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.sanalKarneGecmisAsıText.setText(list.get(position).getAsiisim().toString()+" Aşısı Yapılmıştır.");
        holder.sanalKarneGecmisAsiBilgi.setText(list.get(position).getPetisim().toString()+"isimli petinize"
        +list.get(position).getAsitarih()+" tarihinde "+ list.get(position).getAsiisim()+"aşısı yapılmıştır.");
        Picasso.get().load(list.get(position).getPetresim().toString()).into(holder.sanalKarneGecmisAsiImage);

    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView sanalKarneGecmisAsıText,sanalKarneGecmisAsiBilgi;
        CircleImageView sanalKarneGecmisAsiImage;


        // itemView ile Listview ın her elemanı için Layout ile oluşturduğumuz view tanımlanması işlemi gerçekleiecek
        public ViewHolder(View itemView) {

            super(itemView);
            sanalKarneGecmisAsıText = (TextView) itemView.findViewById(R.id.sanalKarneGecmisAsıText);
            sanalKarneGecmisAsiBilgi = (TextView) itemView.findViewById(R.id.sanalKarneGecmisAsiBilgi);
            sanalKarneGecmisAsiImage = (CircleImageView) itemView.findViewById(R.id.sanalKarneGecmisAsiImage);
        }
    }
}
