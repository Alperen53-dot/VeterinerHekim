package com.example.myapplication.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.myapplication.Fragments.HomeFragment;
import com.example.myapplication.R;
import com.example.myapplication.Utils.ChangeFragments;
import com.example.myapplication.Utils.GetSharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private GetSharedPreferences preferences;
    private ChangeFragments changeFragments;
    private ImageView cikisYap, aramaYapButon, anasayfaButon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFragment();
        tanimla();
        kontrol();
        action();

    }

    private void getFragment() {
        changeFragments = new ChangeFragments(MainActivity.this);
        changeFragments.change(new HomeFragment());
    }

    public void action() {
        anasayfaButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragments.change(new HomeFragment());

            }
        });
        cikisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetSharedPreferences getSharedPreferences = new GetSharedPreferences(MainActivity.this);
                getSharedPreferences.deleteToSession();
                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });
        aramaYapButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("tel:05324005653"));
                startActivity(intent);
            }
        });
    }

    private void tanimla() {
        preferences = new GetSharedPreferences(MainActivity.this);
        sharedPreferences = preferences.getSession();
        anasayfaButon = (ImageView) findViewById(R.id.anasayfaButon);
        cikisYap = (ImageView) findViewById(R.id.cikisYap);
        aramaYapButon = (ImageView) findViewById(R.id.aramaYapButon);
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
