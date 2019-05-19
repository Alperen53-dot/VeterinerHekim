package com.example.veterineradmin.Fragments;

import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import androidx.fragment.app.Fragment;

import com.example.veterineradmin.R;
import com.example.veterineradmin.Utils.ChangeFragments;

public class HomeFragment extends Fragment {

    private View view;
    private LinearLayout kampanyaLayout;
    private ChangeFragments changeFragments;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        tanimla();
        clickToLayout();



        return view;
    }

    public void tanimla(){
        kampanyaLayout = view.findViewById(R.id.kampanyaLayout);
        changeFragments = new ChangeFragments(getActivity());
    }

    public void clickToLayout(){
        kampanyaLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragments.change(new KampanyaFragment());
            }
        });
    }

}
