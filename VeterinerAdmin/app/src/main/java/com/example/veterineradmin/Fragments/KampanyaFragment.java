package com.example.veterineradmin.Fragments;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.veterineradmin.Adapters.KampanyaAdapter;
import com.example.veterineradmin.Models.KampanyaModel;
import com.example.veterineradmin.R;
import com.example.veterineradmin.ResApi.ManagerAll;
import com.example.veterineradmin.Utils.ChangeFragments;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KampanyaFragment extends Fragment {

    private View view;
    private RecyclerView kampanyaRecyclerView;
    private List<KampanyaModel> kampanyaList;
    private KampanyaAdapter kampanyaAdapter;
    private ChangeFragments changeFragments;
    private Button kampanyaEkle;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_kampanya, container, false);

        tanimla();
        getKampanya();
        clicks();

        return view;
    }

    private void tanimla() {
        kampanyaRecyclerView = view.findViewById(R.id.kampanyaRecylerView);
        RecyclerView.LayoutManager mng = new GridLayoutManager(getContext(), 1);
        kampanyaRecyclerView.setLayoutManager(mng);
        kampanyaList = new ArrayList<>();
        changeFragments = new ChangeFragments(getActivity());
        kampanyaEkle = view.findViewById(R.id.kampanyaEkle);
    }

    public void clicks(){
        kampanyaEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addKampanya();
            }
        });
    }

    public void getKampanya() {
        Call<List<KampanyaModel>> req = ManagerAll.getInstance().getKampanya();
        req.enqueue(new Callback<List<KampanyaModel>>() {
            @Override
            public void onResponse(Call<List<KampanyaModel>> call, Response<List<KampanyaModel>> response) {

                Log.i("kampanyalar", response.body().toString());
                if (response.body().get(0).isTf()) {
                    kampanyaList = response.body();
                    kampanyaAdapter = new KampanyaAdapter(kampanyaList, getContext());
                    kampanyaRecyclerView.setAdapter(kampanyaAdapter);
                } else {
                    Toast.makeText(getContext(), "Herhangi bir kampanya bulunmamaktadır.", Toast.LENGTH_LONG).show();
                    changeFragments.change(new HomeFragment());
                }
            }

            @Override
            public void onFailure(Call<List<KampanyaModel>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage().toString(), Toast.LENGTH_LONG).show();

            }
        });
    }

    public void addKampanya() {
        LayoutInflater layoutInflater = this.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.kampanyaeklelayout, null);

        EditText kampanyaBaslikET = view.findViewById(R.id.kampanyaBaslikET);
        EditText kampanyaIcerikET = view.findViewById(R.id.kampanyaIcerikET);
        ImageView resimEkleImage = view.findViewById(R.id.resimSecImage);
        Button kampanyaEkleButton = view.findViewById(R.id.kampanyaEkleButton);
        Button resimSecButton = view.findViewById(R.id.resimSecImage);

        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setView(view);
        alert.setCancelable(true); //boş bir yere tıklayınca kapat

        final AlertDialog alertDialog = alert.create();

        kampanyaEkleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        alert.show();
    }

}
