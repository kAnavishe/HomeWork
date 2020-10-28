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

public class SevenDaysFragment extends Fragment {

    SevenDaysRecyclerViewAdapter sevenDaysRecyclerViewAdapter;
    ImageView backGround;
    ViewFlipper sevenDaysViewFlipper;
    ImageView error;

    public SevenDaysFragment() {
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        sevenDaysRecyclerViewAdapter = new SevenDaysRecyclerViewAdapter(getContext());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_seven_days, container, false);
        RecyclerView mRecyclerView = root.findViewById(R.id.seven_days_recyclerView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(sevenDaysRecyclerViewAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        backGround = root.findViewById(R.id.seven_days_background);

        sevenDaysViewFlipper = root.findViewById(R.id.viewFlipperSevenDays);

        error = root.findViewById(R.id.sevenDaysError);
        return root;

    }
    void setDataSevenDaysFragment(MainData[] values) {
       sevenDaysRecyclerViewAdapter.setDataSevenDayFragment(values);
       backGround.setImageDrawable(getResources().getDrawable(R.drawable.weather_background_clouds));
    }
}


