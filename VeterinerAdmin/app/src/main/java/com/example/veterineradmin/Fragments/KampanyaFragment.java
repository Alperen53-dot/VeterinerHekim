package com.example.veterineradmin.Fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.veterineradmin.Adapters.KampanyaAdapter;
import com.example.veterineradmin.Models.KampanyaEkleModel;
import com.example.veterineradmin.Models.KampanyaModel;
import com.example.veterineradmin.R;
import com.example.veterineradmin.ResApi.ManagerAll;
import com.example.veterineradmin.Utils.ChangeFragments;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class KampanyaFragment extends Fragment {

    private View view;
    private RecyclerView kampanyaRecyclerView;
    private List<KampanyaModel> kampanyaList;
    private KampanyaAdapter kampanyaAdapter;
    private ChangeFragments changeFragments;
    private Button kampanyaEkle;
    ImageView resimSec;
    Bitmap bitmap;
    String imageString;


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
        imageString = null;
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
                    kampanyaAdapter = new KampanyaAdapter(kampanyaList, getContext(),getActivity());
                    kampanyaRecyclerView.setAdapter(kampanyaAdapter);
                } else {
                    Toast.makeText(getActivity(), "Herhangi bir kampanya bulunmamaktadır.", Toast.LENGTH_LONG).show();
                    changeFragments.change(new HomeFragment());
                }
            }

            @Override
            public void onFailure(Call<List<KampanyaModel>> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage().toString(), Toast.LENGTH_LONG).show();

            }
        });
    }

    public void addKampanya() {
        LayoutInflater layoutInflater = this.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.kampanyaeklelayout, null);

        resimSec = view.findViewById(R.id.resimSecImage);
        final EditText kampanyaBaslikET = view.findViewById(R.id.kampanyaBaslikET);
        final EditText kampanyaIcerikET = view.findViewById(R.id.kampanyaIcerikET);
        Button resimEkleButton = view.findViewById(R.id.resimEkleButton);
        Button kampanyaEkleButton = view.findViewById(R.id.kampanyaEkleButton);

        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setView(view);
        alert.setCancelable(true); //boş bir yere tıklayınca kapat

        final AlertDialog alertDialog = alert.create();

        resimEkleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                galeriAc();
            }
        });

       kampanyaEkleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kampanyaBaslikET == null || kampanyaBaslikET.getText().toString().equals("") || kampanyaBaslikET.length() ==0 ||
                        kampanyaIcerikET.getText().toString().equals("") || kampanyaIcerikET == null || kampanyaIcerikET.length() ==0 ||
                            imageToString().equals("") || imageToString() == null)
                {

                    Toast.makeText(getContext(), "Tüm alanların doldurulması ve resmin seçilmesi zorunludur!", Toast.LENGTH_SHORT).show();

                }else{

                    Log.e("baslik",kampanyaBaslikET.getText().toString());
                    kampanyaEkle(kampanyaBaslikET.getText().toString(),kampanyaIcerikET.getText().toString(),imageToString(),alertDialog);
                    kampanyaBaslikET.setText("");
                    kampanyaIcerikET.setText("");

                }
            }
        });


        alert.show();
    }

    public void galeriAc(){
        Intent intent = new Intent();
        intent.setType("image/*"); //bir string alıyor
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,777);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 777 && resultCode==RESULT_OK && data != null){
            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),path);
                resimSec.setImageBitmap(bitmap);
                resimSec.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String imageToString(){

        if (bitmap != null){
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
            byte [] bytes = byteArrayOutputStream.toByteArray();
            imageString = Base64.encodeToString(bytes, android.util.Base64.DEFAULT);
            return imageString;
        }else{
            return imageString;
        }

    }
    public void kampanyaEkle(String baslik, String icerik, final String resim, final AlertDialog alertDialog){
        Call<KampanyaEkleModel> req = ManagerAll.getInstance().addKampanya(baslik,icerik,resim);
        req.enqueue(new Callback<KampanyaEkleModel>() {
            @Override
            public void onResponse(Call<KampanyaEkleModel> call, Response<KampanyaEkleModel> response) {
                if (response.body().isTf()){
                    Toast.makeText(getContext(), response.body().getText(), Toast.LENGTH_SHORT).show();
                    getKampanya();
                    alertDialog.cancel();
                }
                else{
                    Toast.makeText(getContext(), response.body().getText(), Toast.LENGTH_SHORT).show();
                    alertDialog.cancel();
                }
            }

            @Override
            public void onFailure(Call<KampanyaEkleModel> call, Throwable t) {
                Toast.makeText(getContext(),"Check your internet connection!" , Toast.LENGTH_SHORT).show();
            }
        });
    }
}
