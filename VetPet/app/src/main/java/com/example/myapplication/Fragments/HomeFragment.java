package com.example.myapplication.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.myapplication.R;
import com.example.myapplication.Utils.ChangeFragments;

import androidx.fragment.app.Fragment;


public class HomeFragment extends Fragment {


    public View view;
    private LinearLayout petlerimLayout;
    private ChangeFragments changeFragments;
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
        changeFragments = new ChangeFragments(getContext());
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
    }
}