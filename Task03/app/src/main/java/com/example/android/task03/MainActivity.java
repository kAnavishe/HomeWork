package com.example.android.task03;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toolbar;

import com.example.android.task03.Retrofit.ApiClient;
import com.example.android.task03.Retrofit.ApiInterface;
import com.example.android.task03.Retrofit.MainData;
import com.example.android.task03.Retrofit.MainDataValues;
import com.google.android.material.tabs.TabLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ViewPager mViewPager;
    PagerAdapter mViewPagerAdapter;
    Toolbar mToolBar;
    TabLayout mTabLayout;
    Button mSearchButton;
    EditText mEditText;
    static TodayFragment mTodayFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTodayFragment = new TodayFragment(WeatherData.currentTemp);
        mSearchButton = findViewById(R.id.location_search_button);
        mToolBar = findViewById(R.id.toolBar);
        mTabLayout = findViewById(R.id.tabLayout);
        mViewPager = findViewById(R.id.ViewPager);
        mViewPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());
        mViewPager.setAdapter(mViewPagerAdapter);
        mEditText = findViewById(R.id.location_search_edit_text);

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // API Call
                Log.d("MAKS", "onClick");
                getWeatherData(mEditText.getText().toString().trim());
            }
        });

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

    }


    private static class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return mTodayFragment;
                case 1:
                    return new ThreeDaysFragment();
                case 2:
                    return new SevenDaysFragment();
            }
            return new TodayFragment();
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "One";
                case 1:
                    return "Two";
                case 2:
                    return "Three";
            }
            return super.getPageTitle(position);
        }
    }

    private void getWeatherData(String name) {
        Log.d("MAX", "getWeatherData");
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Log.d("MAX", "apiInterface is ready");
        Call<MainData> call = apiInterface.getWeatherData(name);
        Log.d("MAX", "call is ready " + apiInterface);
        call.enqueue(new Callback<MainData>() {

            @Override
            public void onResponse(Call<MainData> call, Response<MainData> response) {

                Log.d("MAX", "onResponse " + response.message() + " " + response.body().getMainDataValues().getTemp() + " " + response.body().getMainDataValues().getHumidity());
                WeatherData.currentTemp = MainDataValues.getTemp();
            }

            @Override
            public void onFailure(Call<MainData> call, Throwable t) {
                Log.e("MAX", "onFailure " + t.getMessage(), t.getCause());
            }
        });
    }
}