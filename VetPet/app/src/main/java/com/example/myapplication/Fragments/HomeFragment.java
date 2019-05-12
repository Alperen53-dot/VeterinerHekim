package com.example.myapplication.Fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.myapplication.Adapters.AnswerAdapter;
import com.example.myapplication.R;
import com.example.myapplication.ResApi.ManagerAll;
import com.example.myapplication.Utils.ChangeFragments;
import com.example.myapplication.Utils.GetSharedPreferences;
import com.example.myapplication.Utils.Warnings;
import com.example.myapplication.Models.AnswersModel;
import com.example.myapplication.Models.AskQuestionModel;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {


    public View view;
    private LinearLayout petlerimLayout,sorusorlinearlayout,cevaplarLayout,kampanyaLinearLayout;
    private ChangeFragments changeFragments;
    private GetSharedPreferences getSharedPreferences;
    private String id;
    private AnswerAdapter answerAdapter;
    private List<AnswersModel> answersModelList;

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
        kampanyaLinearLayout = view.findViewById(R.id.kampanyaLinearLayout);
        changeFragments = new ChangeFragments(getContext());
        getSharedPreferences = new GetSharedPreferences(getActivity());
        cevaplarLayout = view.findViewById(R.id.cevaplarlayout);
        id = getSharedPreferences.getSession().getString("id",null);
        answersModelList = new ArrayList<>();
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
        kampanyaLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragments.change(new KampanyaFragment());
            }
        });
    }

    public void getAnswers(String mus_id) {
        final Call<List<AnswersModel>> request = ManagerAll.getInstance().getAnswers(mus_id);
        request.enqueue(new Callback<List<AnswersModel>>() {
            @Override
            public void onResponse(Call<List<AnswersModel>> call, Response<List<AnswersModel>> response) {
                if (response.body().get(0).isTf()){
                    if (response.isSuccessful()){
                        answersModelList = response.body();
                        answerAdapter = new AnswerAdapter(answersModelList,getContext());
                    openAnswerAlert();
                    }
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
                askQuestion(id,soru,alertDialog);
            }
        });
        alert.show();
    }

    public void openAnswerAlert(){
        LayoutInflater layoutInflater = this.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.cevapalertlayout,null);

        RecyclerView recyclerView = view.findViewById(R.id.cevapRecylerview);

        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setView(view);
        alert.setCancelable(true); //boş bir yere tıklayınca kapat
        final AlertDialog alertDialog = alert.create();

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(answerAdapter);

        alert.show();
    }

    public void askQuestion(String id, String soru, final AlertDialog alr){
        Call<AskQuestionModel> request =  ManagerAll.getInstance().soruSor(id,soru);
        request.enqueue(new Callback<AskQuestionModel>() {
            @Override
            public void onResponse(Call<AskQuestionModel> call, Response<AskQuestionModel> response) {
                if (response.body().isTf() == true){
                    alr.cancel();
                    alr.dismiss();
                    Toast.makeText(getContext(), "Sorunuz ilgili hekime gönderildi!", Toast.LENGTH_SHORT).show();

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