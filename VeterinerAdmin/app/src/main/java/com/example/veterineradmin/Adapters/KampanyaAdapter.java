package com.example.veterineradmin.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veterineradmin.Fragments.KampanyaFragment;
import com.example.veterineradmin.Models.KampanyaModel;
import com.example.veterineradmin.Models.KampanyaSilModel;
import com.example.veterineradmin.R;
import com.example.veterineradmin.ResApi.ManagerAll;
import com.example.veterineradmin.Utils.ChangeFragments;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KampanyaAdapter extends RecyclerView.Adapter<KampanyaAdapter.ViewHolder> {

    List<KampanyaModel> list;
    Context context;
    Activity activity;
    String id;
    ChangeFragments changeFragments;

    public KampanyaAdapter(List<KampanyaModel> list, Context context, Activity activity) {
        this.list = list;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.kampanyaitemlayout, parent, false);
        changeFragments = new ChangeFragments(context);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.kampanyaBaslikText.setText(list.get(position).getBaslik().toString());
        holder.kampanyatext.setText(list.get(position).getText().toString());
        Picasso.get().load(list.get(position).getResim()).resize(200,200).into(holder.kampanyaImageView);
        holder.kampanyaCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                silKampanyaAlert(position);
            }
        });

    }


    @Override
    public int getItemCount() {

        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView kampanyaBaslikText, kampanyatext;
        ImageView kampanyaImageView;
        CardView kampanyaCardview;

        // itemView ile Listview ın her elemanı için Layout ile oluşturduğumuz view tanımlanması işlemi gerçekleiecek

        public ViewHolder(View itemView) {
            super(itemView);
            kampanyatext =(TextView) itemView.findViewById(R.id.kampanyatext);
            kampanyaBaslikText =(TextView) itemView.findViewById(R.id.kampanyaBaslikText);
            kampanyaImageView =(ImageView) itemView.findViewById(R.id.kampanyaImageView);
            kampanyaCardview = itemView.findViewById(R.id.kampanyaCardView);

        }
    }

    public void kampanyaSil(String id, final int position){
        Call<KampanyaSilModel> req = ManagerAll.getInstance().silKampanya(id);
        req.enqueue(new Callback<KampanyaSilModel>() {
            @Override
            public void onResponse(Call<KampanyaSilModel> call, Response<KampanyaSilModel> response) {
                if (response.body().isTf()){
                    Toast.makeText(context, response.body().getText(), Toast.LENGTH_SHORT).show();
                    deleteToList(position);
                }else{
                    Toast.makeText(context, response.body().getText(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<KampanyaSilModel> call, Throwable t) {
                Toast.makeText(context, "Check your internet connection!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteToList(int position) {
        list.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();

    }


    public void silKampanyaAlert(final int position) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.kampanyasillayout, null);

        //
        Button evetAlertDialog = view.findViewById(R.id.evetAlertDialog);
        Button hayirAlertDialog = view.findViewById(R.id.hayirAlertDialog);


        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setView(view);
        alert.setCancelable(true); //boş bir yere tıklayınca kapat

        final AlertDialog alertDialog = alert.create();

        //
        evetAlertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kampanyaSil(list.get(position).getId().toString(),position);
                alertDialog.cancel();
                alertDialog.dismiss();
            }
        });

        hayirAlertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
                alertDialog.dismiss();
                changeFragments.change(new KampanyaFragment());
            }
        });


        alert.show();
    }
}
