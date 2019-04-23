package com.example.myapplication.Utils;

import android.app.Activity;
import android.content.SharedPreferences;

public class GetSharedPreferences {

    private SharedPreferences sharedPreferences;
    private Activity activity;


    public GetSharedPreferences(Activity activity) {
        this.activity = activity;
    }


    public SharedPreferences getSession(){
        sharedPreferences = activity.getSharedPreferences("session",0);
        return sharedPreferences;
    }

    public void setSession(String id, String kadi, String mailAdres){
        sharedPreferences = activity.getApplicationContext().getSharedPreferences("session",0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("id",id);
        editor.putString("kadi", kadi);
        editor.putString("mailAdres", mailAdres);
        editor.commit();
    }

}
