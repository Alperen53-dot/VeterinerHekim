package com.example.myapplication.ResApi;


import com.example.myapplication.models.LoginP;
import com.example.myapplication.models.PetModel;
import com.example.myapplication.models.RegisterP;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RestApi {

    // Kayıt olma işlemi işin servis bağlantıları ve POST işlemi
    @FormUrlEncoded
    @POST("/veterinerservis/kayitol.php")
    Call<RegisterP> registerUser (@Field("mailAdres") String mailAdres, @Field("kadi") String kadi, @Field("parola") String parola);


    //login işlemi
    @FormUrlEncoded
    @POST("/veterinerservis/girisyap.php")
    Call<LoginP> loginUser (@Field("kadi") String kadi, @Field("parola") String parola);

    @FormUrlEncoded
    @POST("/veterinerservis/petlerim.php")
    Call<List<PetModel>> getPets (@Field("musid") String kadi);

}