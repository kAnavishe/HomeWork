package com.example.android.task03;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.task03.Retrofit.MainDataValues;


public class SevenDaysFragment extends Fragment {

    SevenDaysRecyclerViewAdapter sevenDaysRecyclerViewAdapter;

    public SevenDaysFragment() {

        sevenDaysRecyclerViewAdapter = new SevenDaysRecyclerViewAdapter(getContext());
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);

        MainDataValues mainDataValues = new MainDataValues();
        sevenDaysRecyclerViewAdapter = new SevenDaysRecyclerViewAdapter(getContext());
        sevenDaysRecyclerViewAdapter.setDataSevenDayFragment(mainDataValues.getTemp());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_seven_days, container, false);
        RecyclerView mRecyclerView = root.findViewById(R.id.seven_days_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mRecyclerView.setAdapter(sevenDaysRecyclerViewAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        return root;

    }
    void setDataSevenDayFragment(String temp) {
       sevenDaysRecyclerViewAdapter.setDataSevenDayFragment(temp);
    }
}


