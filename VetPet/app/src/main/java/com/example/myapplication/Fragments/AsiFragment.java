package com.example.myapplication.Fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Models.AsiModel;
import com.example.myapplication.R;
import com.example.myapplication.ResApi.ManagerAll;
import com.example.myapplication.Utils.ChangeFragments;
import com.example.myapplication.Utils.GetSharedPreferences;
import com.squareup.picasso.Picasso;
import com.squareup.timessquare.CalendarPickerView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.fragment.app.Fragment;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AsiFragment extends Fragment {

    private View view;
    private CalendarPickerView calendarPickerView;
    private DateFormat format;
    private Calendar nextYear;
    private Date today;
    private List<AsiModel> asiList;
    private List<Date> dataList;
    private String id;
    private GetSharedPreferences getSharedPreferences;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_asi, container, false);
        tanimla();
        getAsi();
        clickToCalendar();
        return view;
    }

    public void tanimla()
    {
        calendarPickerView = view.findViewById(R.id.calendarPickerView);
        format = new SimpleDateFormat("dd/MM/yyyy");
        nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR,1);
        today = new Date();

        calendarPickerView.init(today,nextYear.getTime());
        asiList = new ArrayList<>();
        dataList = new ArrayList<>();
        getSharedPreferences = new GetSharedPreferences(getActivity());
        id = getSharedPreferences.getSession().getString("id",null);

    }
    public void getAsi(){
        Call<List<AsiModel>> req = ManagerAll.getInstance().getAsi("30");
        req.enqueue(new Callback<List<AsiModel>>() {
            @Override
            public void onResponse(Call<List<AsiModel>> call, Response<List<AsiModel>> response) {
                if (response.isSuccessful()) {
                    if (response.body().get(0).isTf()) {
                        asiList = response.body();
                        for (int i = 0; i < asiList.size(); i++) {
                            String dataString = response.body().get(i).getAsitarih().toString();
                            try {
                                Date date = format.parse(dataString);
                                dataList.add(date);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                        calendarPickerView.init(today, nextYear.getTime())
                                .withHighlightedDates(dataList);

                    }
                }
                else
                {
                    ChangeFragments changeFragments = new ChangeFragments(getContext());
                    changeFragments.change(new HomeFragment());
                    Toast.makeText(getContext(),"PetlerinizeYoktur...",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<AsiModel>> call, Throwable t) {

            }
        });
    }
    public void clickToCalendar()
    {
        calendarPickerView.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                for(int i = 0; i <dataList.size();i++ )
                {
                    if (date.toString().equals(dataList.get(i).toString()))
                    {
                        openQuestionAlert(asiList.get(i).getPetisim().toString(),asiList.get(i).getAsitarih().toString(),
                                asiList.get(i).getAsiisim().toString(),asiList.get(i).getPetresim().toString());
                    }

                }
            }

            @Override
            public void onDateUnselected(Date date) {

            }
        });
    }
    public void openQuestionAlert(String petIsmi,String tarih, String asiIsmi,String resimUrl){
        LayoutInflater layoutInflater = this.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.asitakiplayout,null);

        TextView petİsimText = (TextView) view.findViewById(R.id.petİsimText);
        TextView petAsiTakipBilgiText = (TextView) view.findViewById(R.id.petAsiTakipBilgiText);
        CircleImageView asiTakipCircleImageView = (CircleImageView) view.findViewById(R.id.asiTakipCircleImageView);

        petİsimText.setText(petIsmi);
        petAsiTakipBilgiText.setText(petIsmi + " isimli petinizin " + tarih + " tarihinde " + asiIsmi + "aşısı yapılacaktır");

        Picasso.get().load(resimUrl).into(asiTakipCircleImageView);


        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setView(view);
        alert.setCancelable(true); //boş bir yere tıklayınca kapat

        final AlertDialog alertDialog = alert.create();

        alert.show();
    }


}
