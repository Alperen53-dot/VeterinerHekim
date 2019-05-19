package com.example.veterineradmin.ResApi;



import com.example.veterineradmin.Models.AsiOnaylaModel;
import com.example.veterineradmin.Models.KampanyaEkleModel;
import com.example.veterineradmin.Models.KampanyaModel;
import com.example.veterineradmin.Models.KampanyaSilModel;
import com.example.veterineradmin.Models.PetAsiTakipModel;

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

    public Call<KampanyaSilModel> silKampanya(String id) {
        Call<KampanyaSilModel> x = getRestApi().silKampanya(id);
        return x;
    }

    public Call<List<PetAsiTakipModel>> getPetAsiTakip(String tarih) {
        Call<List<PetAsiTakipModel>> x = getRestApi().getPetAsiTakip(tarih);
        return x;
    }

    public Call<AsiOnaylaModel> asiOnayla(String id) {
        Call<AsiOnaylaModel> x = getRestApi().asiOnayla(id);
        return x;
    }

    public Call<AsiOnaylaModel> asiIptal(String id) {
        Call<AsiOnaylaModel> x = getRestApi().asiIptal(id);
        return x;
    }

}
