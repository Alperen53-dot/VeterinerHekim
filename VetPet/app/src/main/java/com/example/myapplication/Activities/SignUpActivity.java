package com.example.myapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.ResApi.ManagerAll;
import com.example.myapplication.Utils.Warnings;
import com.example.myapplication.models.RegisterP;

public class SignUpActivity extends AppCompatActivity {

    Button alreadyMember, button_signup;
    EditText edtUsername, edtEmail, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        alreadyMember = findViewById(R.id.alreadyMember);
        button_signup = findViewById(R.id.button_signup);
        edtUsername = findViewById(R.id.edtUsername);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);

        alreadyMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register(edtUsername.getText().toString(),edtEmail.getText().toString(),edtPassword.getText().toString());
                Log.e("Hata : ", edtUsername.getText().toString()+edtEmail.getText().toString()+edtPassword.getText().toString());
            }
        });
    }

    public void register(final String username, String mailAdres, String password){
        final Call<RegisterP> registerRequest = ManagerAll.getInstance().kayitOl(mailAdres,username,password);
        registerRequest.enqueue(new Callback<RegisterP>() {
            @Override
            public void onResponse(Call<RegisterP> call, Response<RegisterP> response) {
                if (response.body().isTrueFalse()){
                    Toast.makeText(SignUpActivity.this, username+" kullanıcı adıyla hesabınız oluşturuldu.", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(SignUpActivity.this, "Bu bilgilere sahip bir kullanıcı zaten var.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterP> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, Warnings.internetProblemText, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
