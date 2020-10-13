package com.example.android.task03;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;


public class SevenDaysFragment extends Fragment {

    Date date;
    Drawable image;
    String temperature;


    public SevenDaysFragment() {
    }

    public void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        date = new Date();
        image = getResources().getDrawable(R.drawable.sunny);
        temperature = "+3";


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_seven_days, container, false);
        RecyclerView mRecyclerView = root.findViewById(R.id.seven_days_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mRecyclerView.setAdapter(new SevenDaysRecyclerViewAdapter(getContext(), date, image, temperature));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        return root;

    }
}


