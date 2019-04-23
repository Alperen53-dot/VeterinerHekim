package com.example.myapplication.ResApi;


import com.example.myapplication.models.LoginP;
import com.example.myapplication.models.RegisterP;

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


}
