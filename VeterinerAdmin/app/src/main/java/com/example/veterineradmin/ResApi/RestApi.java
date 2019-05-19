package com.example.veterineradmin.ResApi;



import com.example.veterineradmin.Models.KampanyaEkleModel;
import com.example.veterineradmin.Models.KampanyaModel;
import com.example.veterineradmin.Models.KampanyaSilModel;
import com.example.veterineradmin.Models.PetAsiTakipModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestApi {

    @GET("/veterinerservis/kampanyaidli.php")
    Call<List<KampanyaModel>> getKampanya();

    @FormUrlEncoded
    @POST("/veterinerservis/kampanyaekle.php")
    Call<KampanyaEkleModel> addKampanya(@Field("baslik") String baslik, @Field("text") String icerik,@Field("resim") String resim);

    @FormUrlEncoded
    @POST("/veterinerservis/kampanyasil.php")
    Call<KampanyaSilModel> silKampanya(@Field("id") String id);

    @FormUrlEncoded
    @POST("/veterinerservis/veterinerasiTakip.php")
    Call<List<PetAsiTakipModel>> getPetAsiTakip(@Field("tarih") String tarih);



}