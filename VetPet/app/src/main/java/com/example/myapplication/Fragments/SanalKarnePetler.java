package com.example.myapplication.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.Adapters.SanalKarnePetAdapter;
import com.example.myapplication.Models.PetModel;
import com.example.myapplication.R;
import com.example.myapplication.ResApi.ManagerAll;
import com.example.myapplication.Utils.ChangeFragments;
import com.example.myapplication.Utils.GetSharedPreferences;
import com.example.myapplication.Utils.Warnings;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SanalKarnePetler extends Fragment {


    private RecyclerView sanalKarnepetler;
    private View view;
    private SanalKarnePetAdapter sanalKarnePetAdapter;
    private List<PetModel> petList;
    private ChangeFragments changeFragments;
    private String mus_id;
    private GetSharedPreferences getSharedPreferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_user_pets, container, false);
        tanimla();


        Log.e("sanalkarnepetlerififii",String.valueOf(mus_id));
        getPets(mus_id);
        return view;
    }


    public void tanimla()
    {
        petList = new ArrayList<>();
        sanalKarnepetler = view.findViewById(R.id.petlistrecyclerview);
        RecyclerView.LayoutManager mng = new GridLayoutManager(getContext(),1);
        sanalKarnepetler.setLayoutManager(mng);
        changeFragments = new ChangeFragments(getContext());
        getSharedPreferences = new GetSharedPreferences(getActivity());
        mus_id = getSharedPreferences.getSession().getString("id",null);
        Log.e("sanalkarnepetler",String.valueOf(mus_id));


    }
    public void getPets(String mus_id)
    {
        Call<List<PetModel>> req = ManagerAll.getInstance().getPets(mus_id);
        req.enqueue(new Callback<List<PetModel>>() {
            @Override
            public void onResponse(Call<List<PetModel>> call, Response<List<PetModel>> response) {
                if(response.body().get(0).isTf())
                {
                    petList = response.body();
                    sanalKarnePetAdapter = new SanalKarnePetAdapter(petList,getContext());
                    sanalKarnepetler.setAdapter(sanalKarnePetAdapter);


                }else
                {
                    Toast.makeText(getContext(),"Sistemde kayıtlı petiniz bulunmamaktadır.",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<PetModel>> call, Throwable t) {
                Toast.makeText(getContext(), Warnings.internetProblemText,Toast.LENGTH_LONG).show();

            }
        });
    }

}
