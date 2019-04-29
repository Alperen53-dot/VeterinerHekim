package com.example.myapplication.Activities;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.ResApi.ManagerAll;
import com.example.myapplication.Utils.GetSharedPreferences;
import com.example.myapplication.Utils.Warnings;
import com.example.myapplication.models.LoginP;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText username_edit, password_edit;
    TextView forgot_password,sign_up;
    Button sign_in;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username_edit = findViewById(R.id.username_edit);
        password_edit = findViewById(R.id.password_edit);
        forgot_password = findViewById(R.id.forgot_password);
        sign_up = findViewById(R.id.sign_up);
        sign_in = findViewById(R.id.sign_in);

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username_edit.length() == 0 || username_edit.equals("") || username_edit == null
                        || password_edit.length() == 0 || password_edit.equals("") || password_edit == null)
                {
                    Snackbar.make(v,"Be sure to fill in the required fields!",Snackbar.LENGTH_SHORT).show();
                }
                else
                {
                    login(username_edit.getText().toString(),password_edit.getText().toString());
                }
            }
        });

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(i);
            }
        });
    }

    public void login(final String kadi, String parola){
        final Call<LoginP> loginRequest = ManagerAll.getInstance().girisYap(kadi,parola);
        loginRequest.enqueue(new Callback<LoginP>() {
            @Override
            public void onResponse(Call<LoginP> call, Response<LoginP> response) {
                if (response.body().isTrueFalse()){
                    if (response.body().getDurum()==1){
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        GetSharedPreferences getSharedPreferences = new GetSharedPreferences(LoginActivity.this);
                        getSharedPreferences.setSession(response.body().getId(),response.body().getKadi(),response.body().getMailAdres());
                        startActivity(i);
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this, response.body().getText(), Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(LoginActivity.this, response.body().getText(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginP> call, Throwable t) {
                Toast.makeText(LoginActivity.this, Warnings.internetProblemText, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
