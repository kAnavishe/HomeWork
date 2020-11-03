package com.example.android.Task03;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.Task03.Retrofit.MainData;

public class ThreeDaysFragment extends Fragment {

    ViewFlipper threeDaysViewFlipper;
    ThreeDaysRecyclerViewAdapter threeDaysRecyclerViewAdapter;
    private ImageView mBackGround;

    public ThreeDaysFragment() {
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        threeDaysRecyclerViewAdapter = new ThreeDaysRecyclerViewAdapter(getContext());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_three_days, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.three_days_recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(threeDaysRecyclerViewAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mBackGround = root.findViewById(R.id.three_days_background);
        threeDaysViewFlipper = root.findViewById(R.id.viewFlipperThreeDays);
        return root;
    }

    void setDataThreeDaysFragment(MainData[] values) {
        threeDaysRecyclerViewAdapter.setDataThreeDaysFragment(values);
        mBackGround.setVisibility(View.VISIBLE);
    }
}
