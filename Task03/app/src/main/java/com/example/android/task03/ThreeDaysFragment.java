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

public class ThreeDaysFragment extends Fragment {

    ThreeDaysRecyclerViewAdapter threeDaysRecyclerViewAdapter;

    public ThreeDaysFragment() {
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);

        threeDaysRecyclerViewAdapter = new ThreeDaysRecyclerViewAdapter(getContext());
        setDataTodayFragment("0");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_three_days, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.three_days_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(threeDaysRecyclerViewAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return root;
    }

    public void setDataTodayFragment(String temp) {
        threeDaysRecyclerViewAdapter.setDataThreeDaysFragment(temp);
    }
}
