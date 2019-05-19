package com.example.veterineradmin.Fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.veterineradmin.Adapters.PetAsiTakipAdapter;
import com.example.veterineradmin.Models.PetAsiTakipModel;
import com.example.veterineradmin.R;
import com.example.veterineradmin.ResApi.ManagerAll;
import com.example.veterineradmin.Utils.ChangeFragments;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AsiTakipFragment extends Fragment {

    private View view;
    private DateFormat format;
    private Date date;
    String today;
    ChangeFragments changeFragments;
    RecyclerView asiTakipRecyclerView;
    private List<PetAsiTakipModel> list;
    private PetAsiTakipAdapter petAsiTakipAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_asi_takip, container, false);

        tanimla();
        istekAt(today);

        return view;
    }

    public void tanimla(){
        format = new SimpleDateFormat("dd/MM/yyyy");
        date = Calendar.getInstance().getTime();
        today = format.format(date);
        changeFragments = new ChangeFragments(getContext());
        asiTakipRecyclerView = view.findViewById(R.id.asiTakipRecyclerView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        asiTakipRecyclerView.setLayoutManager(layoutManager);

    }

    public void istekAt(String tarih){
        Log.e("tarih", tarih);
        Call<List<PetAsiTakipModel>> req = ManagerAll.getInstance().getPetAsiTakip(tarih);
        req.enqueue(new Callback<List<PetAsiTakipModel>>() {
            @Override
            public void onResponse(Call<List<PetAsiTakipModel>> call, Response<List<PetAsiTakipModel>> response) {
                if (response.body().get(0).isTf()){
                    Toast.makeText(getContext(), "Bugün "+response.body().size()+" pete aşı yapılacaktır.", Toast.LENGTH_SHORT).show();
                    list = response.body();
                    petAsiTakipAdapter = new PetAsiTakipAdapter(list,getContext(),getActivity());
                    asiTakipRecyclerView.setAdapter(petAsiTakipAdapter);
                }else{
                    Toast.makeText(getContext(), "Bugün aşı yapılacak pet yoktur", Toast.LENGTH_SHORT).show();
                    changeFragments.change(new HomeFragment());
                }
            }

            @Override
            public void onFailure(Call<List<PetAsiTakipModel>> call, Throwable t) {
                Toast.makeText(getContext(), "Check your internet connection!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
