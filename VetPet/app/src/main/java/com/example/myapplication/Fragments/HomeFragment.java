package com.example.myapplication.Fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.ResApi.ManagerAll;
import com.example.myapplication.Utils.ChangeFragments;
import com.example.myapplication.Utils.GetSharedPreferences;
import com.example.myapplication.Utils.Warnings;
import com.example.myapplication.models.AnswersModel;
import com.example.myapplication.models.AskQuestionModel;

import java.util.List;

import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.myapplication.ResApi.ManagerAll.getInstance;


public class HomeFragment extends Fragment {


    public View view;
    private LinearLayout petlerimLayout,sorusorlinearlayout,cevaplarLayout;
    private ChangeFragments changeFragments;
    private GetSharedPreferences getSharedPreferences;
    private String id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        tanimla();
        action();


        return view;
    }

    public void tanimla()
    {
        petlerimLayout = view.findViewById(R.id.petlerimLayout);
        sorusorlinearlayout = view.findViewById(R.id.sorusorlinearlayout);
        changeFragments = new ChangeFragments(getContext());
        getSharedPreferences = new GetSharedPreferences(getActivity());
        cevaplarLayout = view.findViewById(R.id.cevaplarlayout);
        id = getSharedPreferences.getSession().getString("id",null);
    }

    public void action()
    {
        petlerimLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                changeFragments.change(new UserPetsFragment());
            }

        });
        sorusorlinearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openQuestionAlert();
            }
        });
        cevaplarLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAnswers(id);
            }
        });
    }

    public void getAnswers(String mus_id) {
        final Call<List<AnswersModel>> request = ManagerAll.getInstance().getAnswers(mus_id);
        request.enqueue(new Callback<List<AnswersModel>>() {
            @Override
            public void onResponse(Call<List<AnswersModel>> call, Response<List<AnswersModel>> response) {
                if (response.body().get(0).isTf()){
                    Log.i("cevaplar",response.body().toString());
                }else{
                    Toast.makeText(getContext(), "Cevap yok", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<AnswersModel>> call, Throwable t) {
                Toast.makeText(getContext(), Warnings.internetProblemText, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void openQuestionAlert(){
        LayoutInflater layoutInflater = this.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.sorusoralertlayout,null);

        final EditText sorusoredittext = view.findViewById(R.id.sorusoredittext);
        Button sorusorbutton = view.findViewById(R.id.sorusorbutton);

        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setView(view);
        alert.setCancelable(true); //boş bir yere tıklayınca kapat

        final AlertDialog alertDialog = alert.create();
        sorusorbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //web servis isteği
                String soru = sorusoredittext.getText().toString();
                sorusoredittext.setText("");
                alertDialog.cancel();
                askQuestion(id,soru,alertDialog);
            }
        });
        alert.show();
    }

    public void askQuestion(String id, String soru, final AlertDialog alr){
        Call<AskQuestionModel> request =  ManagerAll.getInstance().soruSor(id,soru);
        request.enqueue(new Callback<AskQuestionModel>() {
            @Override
            public void onResponse(Call<AskQuestionModel> call, Response<AskQuestionModel> response) {
                if (response.body().isTf() == true){
                    Toast.makeText(getContext(), "Sorunuz ilgili hekime gönderildi!", Toast.LENGTH_SHORT).show();
                    alr.cancel();
                }else {
                    Toast.makeText(getContext(), "İşlem gerçekleşmedi lütfen daha sonra tekrar deneyiniz.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AskQuestionModel> call, Throwable t) {
                Toast.makeText(getContext(), Warnings.internetProblemText, Toast.LENGTH_SHORT).show();
            }
        });
    }
}