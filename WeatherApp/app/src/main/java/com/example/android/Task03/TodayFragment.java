package com.example.android.Task03;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.android.Task03.Retrofit.MainData;

public class TodayFragment extends Fragment {
    private TodayRecyclerViewAdapter todayRecyclerViewAdapter;
    TextView mTemperature;
    TextView mWeatherDescription;
    TextView mMainText;
    ImageView mBackground;
    ImageView mWeatherCondition;
    ViewFlipper todayViewFlipper;
    ConstraintLayout constraintLayout;
    ProgressBar progressBar;
    ImageView error;

    public TodayFragment() {
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        todayRecyclerViewAdapter = new TodayRecyclerViewAdapter(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_today, container, false);
        RecyclerView mRecyclerView = root.findViewById(R.id.today_recyclerView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setAdapter(todayRecyclerViewAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mTemperature = root.findViewById(R.id.temperature);
        mTemperature.setText(MainActivity.weatherData.get("temperature"));

        mMainText = root.findViewById(R.id.text_main);
        mWeatherDescription = root.findViewById(R.id.weather_description_todayFragment);

        mBackground = root.findViewById(R.id.background_android);

        mWeatherCondition = root.findViewById(R.id.weather_condition);
        mWeatherCondition.setImageDrawable(MainActivity.weatherIcons.get(MainActivity.weatherData.get("icon")));

        todayViewFlipper = root.findViewById(R.id.viewFlipperToday);
        constraintLayout = root.findViewById(R.id.constraintToday);
        progressBar = root.findViewById(R.id.progressBarToday);
        error = root.findViewById(R.id.todayError);
        return root;
    }

    public void setMainText(String text) {
        mMainText.setText(text);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    void setDataTodayFragment(MainData[] mainData) {
        todayRecyclerViewAdapter.setDataTodayFragment(mainData);
        setMainText(mainData[0].getWeathers()[0].getWeatherCondition());
        mTemperature.setText(mainData[0].getMainDataValues().getTemp());
        mWeatherCondition.setImageDrawable(MainActivity.weatherIcons.get(mainData[0].getWeathers()[0].getWeatherIcon()));
        MainActivity.todayFragmentBackground = getResources().getDrawable(R.drawable.weather_background_clouds);
        mWeatherDescription.setText(mainData[0].getWeathers()[0].getWeatherCondition());
        setMainText("");
        mBackground.setImageDrawable(MainActivity.todayFragmentBackground);
        mBackground.setPaddingRelative(0, 0, 0, 0);
    }
}