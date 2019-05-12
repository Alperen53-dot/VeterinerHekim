package com.example.myapplication.ResApi;


import com.example.myapplication.Models.AnswersModel;
import com.example.myapplication.Models.AskQuestionModel;
import com.example.myapplication.Models.DeleteAnswerModel;
import com.example.myapplication.Models.KampanyaModel;
import com.example.myapplication.Models.LoginP;
import com.example.myapplication.Models.PetModel;
import com.example.myapplication.Models.RegisterP;

import java.util.List;

import retrofit2.Call;

public class ManagerAll extends BaseManager {

    private  static ManagerAll ourInstance = new ManagerAll();

    public  static synchronized ManagerAll getInstance()
    {
        return  ourInstance;
    }

    public Call<RegisterP> kayitOl(String mailAdres , String kadi, String parola)
    {
        Call<RegisterP> x = getRestApi().registerUser(mailAdres, kadi, parola);
        return  x ;
    }

    public Call<LoginP> girisYap(String kadi, String parola)
    {
        Call<LoginP> x = getRestApi().loginUser(kadi, parola);
        return  x ;
    }

    public Call<List<PetModel>> getPets(String id)
    {
        Call<List<PetModel>> x = getRestApi().getPets(id);
        return  x ;
    }

    public Call<AskQuestionModel> soruSor(String id, String soru)
    {
        Call<AskQuestionModel> x = getRestApi().askQuestion(id, soru);
        return  x ;
    }

    public Call<DeleteAnswerModel> deleteAnswer(String soruid, String cevapid)
    {
        Call<DeleteAnswerModel> x = getRestApi().deleteAnswer(soruid, cevapid);
        return  x ;
    }

    public Call<List<AnswersModel>> getAnswers(String mus_id)
    {
        Call<List<AnswersModel>> x = getRestApi().getAnswers(mus_id);
        return  x ;
    }

    public Call<List<KampanyaModel>> getKampanya()
    {
        Call<List<KampanyaModel>> x = getRestApi().getKampanya("g");
        return  x ;
    }

}
