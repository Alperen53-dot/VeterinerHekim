package com.example.veterineradmin.ResApi;



import com.example.veterineradmin.Models.KampanyaEkleModel;
import com.example.veterineradmin.Models.KampanyaModel;

import java.util.List;

import retrofit2.Call;

public class ManagerAll extends BaseManager {

    private static ManagerAll ourInstance = new ManagerAll();

    public static synchronized ManagerAll getInstance() {
        return ourInstance;
    }

    public Call<List<KampanyaModel>> getKampanya() {
        Call<List<KampanyaModel>> x = getRestApi().getKampanya();
        return x;
    }

    public Call<KampanyaEkleModel> addKampanya(String baslik, String icerik, String resim) {
        Call<KampanyaEkleModel> x = getRestApi().addKampanya(baslik, icerik, resim);
        return x;
    }

}
