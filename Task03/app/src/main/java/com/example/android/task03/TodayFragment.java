package com.example.android.task03;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.task03.Retrofit.MainData;
import com.example.android.task03.Retrofit.MainDataValues;

import java.text.SimpleDateFormat;
import java.util.Date;


public class TodayFragment extends Fragment {
    MainDataValues myData;
    Drawable image;
    String temperature;
    String hour;
    Date date;
    public TodayFragment(MainDataValues temp) {
        myData = temp;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        hour = simpleDateFormat.format(date);
        if (WeatherData.currentTemp == null) {
            temperature = "+4";
        } else {
            temperature = WeatherData.currentTemp;
        }
        image = getResources().getDrawable(R.drawable.partly_cloudy);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_today, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.today_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        recyclerView.setAdapter(new TodayRecyclerViewAdapter(getContext(), image, temperature, hour));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return root;
    }
}