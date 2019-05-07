package com.example.myapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Models.DeleteAnswerModel;
import com.example.myapplication.R;
import com.example.myapplication.Models.AnswersModel;
import com.example.myapplication.ResApi.ManagerAll;
import com.example.myapplication.Utils.Warnings;
import com.google.android.material.button.MaterialButton;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.ViewHolder> {

    List<AnswersModel> list;
    Context context;

    public AnswerAdapter(List<AnswersModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cevapitemlayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.cevapsorutext.setText("Soru: "+list.get(position).getSoru().toString());
        holder.cevapcevaptext.setText("Cevap: "+list.get(position).getCevap().toString());

        holder.cevapsilbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String soruid = list.get(position).getSoruid();
                String cevapid = list.get(position).getCevapid();
                deleteToService(soruid,cevapid,position);
            }
        });
    }
    private void deleteToList(int position){
        list.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }
    private void deleteToService(String soruid, String cevapid, final int position) {
        Call<DeleteAnswerModel> req = ManagerAll.getInstance().deleteAnswer(soruid,cevapid);
        req.enqueue(new Callback<DeleteAnswerModel>() {
            @Override
            public void onResponse(Call<DeleteAnswerModel> call, Response<DeleteAnswerModel> response) {
                if (response.body().isTf()){
                    if (response.isSuccessful()){
                    Toast.makeText(context, response.body().getText(), Toast.LENGTH_SHORT).show();
                    deleteToList(position);
                    }
                }else{
                    Toast.makeText(context, response.body().getText(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<DeleteAnswerModel> call, Throwable t) {
                Toast.makeText(context, Warnings.internetProblemText, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cevapsorutext, cevapcevaptext;
        MaterialButton cevapsilbutton;

        // itemView ile Listview ın her elemanı için Layout ile oluşturduğumuz view tanımlanması işlemi gerçekleiecek
        public ViewHolder(View itemView) {
            super(itemView);
            cevapsorutext = itemView.findViewById(R.id.cevapsorutext);
            cevapcevaptext = itemView.findViewById(R.id.cevapcevaptext);
            cevapsilbutton = itemView.findViewById(R.id.cevapsilbutton);

        }
    }
}
