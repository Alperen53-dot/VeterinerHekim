package com.example.myapplication.ResApi;


import com.example.myapplication.Models.AnswersModel;
import com.example.myapplication.Models.AsiModel;
import com.example.myapplication.Models.AskQuestionModel;
import com.example.myapplication.Models.DeleteAnswerModel;
import com.example.myapplication.Models.KampanyaModel;
import com.example.myapplication.Models.LoginP;
import com.example.myapplication.Models.PetModel;
import com.example.myapplication.Models.RegisterP;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RestApi {


    @FormUrlEncoded
    @POST("/veterinerservis/kayitol.php")
    Call<RegisterP> registerUser (@Field("mailAdres") String mailAdres, @Field("kadi") String kadi, @Field("parola") String parola);



    @FormUrlEncoded
    @POST("/veterinerservis/girisyap.php")
    Call<LoginP> loginUser (@Field("kadi") String kadi, @Field("parola") String parola);

    @FormUrlEncoded
    @POST("/veterinerservis/petlerim.php")
    Call<List<PetModel>> getPets (@Field("musid") String musid);

    @FormUrlEncoded
    @POST("/veterinerservis/sorusor.php")
    Call<AskQuestionModel> askQuestion(@Field("id") String id, @Field("soru") String soru);

    @FormUrlEncoded
    @POST("/veterinerservis/cevaplar.php")
    Call<List<AnswersModel>> getAnswers(@Field("mus_id") String mus_id);

    @FormUrlEncoded
    @POST("/veterinerservis/cevapsil.php")
    Call<DeleteAnswerModel> deleteAnswer(@Field("soruid") String soruid, @Field("cevapid") String cevapid);

    @FormUrlEncoded
    @POST("/veterinerservis/kampanya.php")
    Call<List<KampanyaModel>> getKampanya(@Field("asd") String s);

    @FormUrlEncoded
    @POST("/veterinerservis/asitakip.php")
    Call<List<AsiModel>> getAsi(@Field("id") String id);

    @FormUrlEncoded
    @POST("/veterinerservis/gecmisasi.php")
    Call<List<AsiModel>> getGecmisAsi(@Field("id") String id, @Field("petid") String pet_id);

}