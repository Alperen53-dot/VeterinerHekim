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

import com.example.myapplication.R;
import com.example.myapplication.ResApi.ManagerAll;
import com.example.myapplication.Utils.ChangeFragments;
import com.example.myapplication.Utils.GetSharedPreferences;
import com.example.myapplication.Utils.Warnings;
import com.example.myapplication.models.AskQuestionModel;

import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.myapplication.ResApi.ManagerAll.getInstance;


public class HomeFragment extends Fragment {


    public View view;
    private LinearLayout petlerimLayout,sorusorlinearlayout;
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