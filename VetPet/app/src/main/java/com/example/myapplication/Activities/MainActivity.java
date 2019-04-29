package com.example.myapplication.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import com.example.myapplication.Fragments.HomeFragment;
import com.example.myapplication.R;
import com.example.myapplication.Utils.ChangeFragments;
import com.example.myapplication.Utils.GetSharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private GetSharedPreferences preferences;
    private Button exit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFragment();
        tanımla();
        kontrol();

    }

    private void getFragment() {
        ChangeFragments changeFragments = new ChangeFragments(MainActivity.this);
        changeFragments.change(new HomeFragment());
    }

    private void tanımla() {
        preferences = new GetSharedPreferences(MainActivity.this);
        sharedPreferences = preferences.getSession();
    }

    public void kontrol() {
        if (sharedPreferences.getString("id", null) == null &&
                sharedPreferences.getString("kadi", null) == null &&
                sharedPreferences.getString("mailAdres", null) == null) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
