package com.example.android.task03;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TodayFragment extends Fragment {
    private TodayRecyclerViewAdapter todayRecyclerViewAdapter;

    public TodayFragment() {
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        todayRecyclerViewAdapter = new TodayRecyclerViewAdapter(getContext());
        todayRecyclerViewAdapter.setDataTodayFragment("0");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_today, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.today_recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        recyclerView.setAdapter(todayRecyclerViewAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return root;
    }

    public void setDataTodayFragment(String temp) {
        todayRecyclerViewAdapter.setDataTodayFragment(temp);
    }

}