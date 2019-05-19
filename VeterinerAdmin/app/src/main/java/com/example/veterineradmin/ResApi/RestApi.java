package com.example.veterineradmin.ResApi;



import com.example.veterineradmin.Models.KampanyaModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApi {

    @GET("/veterinerservis/kampanyaidli.php")
    Call<List<KampanyaModel>> getKampanya();



}