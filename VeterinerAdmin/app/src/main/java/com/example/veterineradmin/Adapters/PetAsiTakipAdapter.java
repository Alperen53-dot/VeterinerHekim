package com.example.veterineradmin.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veterineradmin.Fragments.HomeFragment;
import com.example.veterineradmin.Fragments.KampanyaFragment;
import com.example.veterineradmin.Models.AsiOnaylaModel;
import com.example.veterineradmin.Models.KampanyaModel;
import com.example.veterineradmin.Models.KampanyaSilModel;
import com.example.veterineradmin.Models.PetAsiTakipModel;
import com.example.veterineradmin.R;
import com.example.veterineradmin.ResApi.ManagerAll;
import com.example.veterineradmin.Utils.ChangeFragments;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetAsiTakipAdapter extends RecyclerView.Adapter<PetAsiTakipAdapter.ViewHolder> {

    List<PetAsiTakipModel> list;
    Context context;
    Activity activity;
    String id;
    ChangeFragments changeFragments;

    public PetAsiTakipAdapter(List<PetAsiTakipModel> list, Context context, Activity activity) {
        this.list = list;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.asitakiplayout, parent, false);
        changeFragments = new ChangeFragments(context);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.asiTakipPetName.setText(list.get(position).getAsiisim());
        holder.asiTakipBilgiText.setText(list.get(position).getKadi()+" isimli kullanıcının "+list.get(position).getPetisim()
        +" isimli petinin "+list.get(position).getAsiisim()+ " aşısı kapılacaktır.");
        Picasso.get().load(list.get(position).getPetresim()).resize(200,200).into(holder.asiTakipImage);

        holder.asiTakipAraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ara(list.get(position).getTelefon());
            }
        });

        holder.asiTakipOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asiOnayla(list.get(position).getAsiid(),position);
            }
        });

        holder.asiTakipCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asiIptal(list.get(position).getAsiid(),position);
            }
        });
    }


    @Override
    public int getItemCount() {

        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView asiTakipPetName, asiTakipBilgiText;
        ImageView asiTakipImage,asiTakipOkButton,asiTakipCancelButton, asiTakipAraButton;
        //CardView kampanyaCardview;

        // itemView ile Listview ın her elemanı için Layout ile oluşturduğumuz view tanımlanması işlemi gerçekleiecek

        public ViewHolder(View itemView) {
            super(itemView);
            asiTakipPetName =itemView.findViewById(R.id.asiTakipPetName);
            asiTakipBilgiText =itemView.findViewById(R.id.asiTakipBilgiText);

            asiTakipImage =itemView.findViewById(R.id.asiTakipImage);
            asiTakipOkButton =itemView.findViewById(R.id.asiTakipOkButton);
            asiTakipCancelButton =itemView.findViewById(R.id.asiTakipCancelButton);
            asiTakipAraButton =itemView.findViewById(R.id.asiTakipAraButton);

        }
    }

    private void deleteToList(int position) {
        list.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();

    }

    public void ara (String numara){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("tel:"+numara));
        activity.startActivity(intent);
    }

    public void asiOnayla(String id, final int position){
        Call<AsiOnaylaModel> req = ManagerAll.getInstance().asiOnayla(id);
        req.enqueue(new Callback<AsiOnaylaModel>() {
            @Override
            public void onResponse(Call<AsiOnaylaModel> call, Response<AsiOnaylaModel> response) {
                Toast.makeText(context, response.body().getText(), Toast.LENGTH_SHORT).show();
                deleteToList(position);
                if (list.size() == 0){
                    changeFragments.change(new HomeFragment());
                }
            }

            @Override
            public void onFailure(Call<AsiOnaylaModel> call, Throwable t) {
                Toast.makeText(context, "Check your internet connection!", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void asiIptal(String id, final int position){
        Call<AsiOnaylaModel> req = ManagerAll.getInstance().asiIptal(id);
        req.enqueue(new Callback<AsiOnaylaModel>() {
            @Override
            public void onResponse(Call<AsiOnaylaModel> call, Response<AsiOnaylaModel> response) {
                Toast.makeText(context, response.body().getText(), Toast.LENGTH_SHORT).show();
                deleteToList(position);
                if (list.size() == 0){
                    changeFragments.change(new HomeFragment());
                }
            }

            @Override
            public void onFailure(Call<AsiOnaylaModel> call, Throwable t) {
                Toast.makeText(context, "Check your internet connection!", Toast.LENGTH_SHORT).show();
            }
        });
    }



}
