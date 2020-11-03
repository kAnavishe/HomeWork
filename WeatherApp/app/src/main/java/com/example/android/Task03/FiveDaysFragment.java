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

public class FiveDaysFragment extends Fragment {

    FiveDaysRecyclerViewAdapter fiveDaysRecyclerViewAdapter;
    private ImageView mBackGround;
    ViewFlipper fiveDaysViewFlipper;

    public FiveDaysFragment() {
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        fiveDaysRecyclerViewAdapter = new FiveDaysRecyclerViewAdapter(getContext());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_seven_days, container, false);
        RecyclerView mRecyclerView = root.findViewById(R.id.five_days_recyclerView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(fiveDaysRecyclerViewAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mBackGround = root.findViewById(R.id.five_days_background);

        fiveDaysViewFlipper = root.findViewById(R.id.viewFlipperSevenDays);

        return root;
    }

    void setDataFiveDaysFragment(MainData[] values) {
        fiveDaysRecyclerViewAdapter.setDataFiveDayFragment(values);
        mBackGround.setVisibility(View.VISIBLE);
    }
}


