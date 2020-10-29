package com.example.android.Task03;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.android.Task03.Retrofit.ApiClient;
import com.example.android.Task03.Retrofit.ApiInterface;
import com.example.android.Task03.Retrofit.ListData;
import com.example.android.Task03.Retrofit.MainData;
import com.google.android.material.tabs.TabLayout;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    public static TodayFragment todayFragment;
    @SuppressLint("StaticFieldLeak")
    public static ThreeDaysFragment threeDaysFragment;
    @SuppressLint("StaticFieldLeak")
    public static SevenDaysFragment sevenDaysFragment;

    public static HashMap<String, String> weatherData = new HashMap<>();
    public static HashMap<String, Drawable> weatherIcons = new HashMap<>();
    public static Drawable todayFragmentBackground;
    ViewPager mViewPager;
    PagerAdapter mViewPagerAdapter;
    Toolbar mToolBar;
    TabLayout mTabLayout;
    Button mSearchButton;
    EditText mEditText;
    Boolean mPicChangeFlag;
    Boolean error;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todayFragmentBackground = getDrawable(R.drawable.android);

        todayFragment = new TodayFragment();
        threeDaysFragment = new ThreeDaysFragment();
        sevenDaysFragment = new SevenDaysFragment();

        swipeRefreshLayout = findViewById(R.id.mainSwipeRefresh);
        mSearchButton = findViewById(R.id.location_search_button);
        mToolBar = findViewById(R.id.toolBar);
        mTabLayout = findViewById(R.id.tabLayout);
        mViewPager = findViewById(R.id.ViewPager);
        mViewPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());
        mViewPager.setAdapter(mViewPagerAdapter);
        mEditText = findViewById(R.id.location_search_edit_text);
        mPicChangeFlag = false;
        error = false;

        weatherIcons.put("01d", getResources().getDrawable(R.drawable.i01d));
        weatherIcons.put("01n", getResources().getDrawable(R.drawable.i01n));
        weatherIcons.put("02d", getResources().getDrawable(R.drawable.i02d));
        weatherIcons.put("02n", getResources().getDrawable(R.drawable.i02n));
        weatherIcons.put("03d", getResources().getDrawable(R.drawable.i03d));
        weatherIcons.put("03n", getResources().getDrawable(R.drawable.i03n));
        weatherIcons.put("04d", getResources().getDrawable(R.drawable.i04d));
        weatherIcons.put("04n", getResources().getDrawable(R.drawable.i04n));
        weatherIcons.put("09d", getResources().getDrawable(R.drawable.i09d));
        weatherIcons.put("09n", getResources().getDrawable(R.drawable.i09n));
        weatherIcons.put("10d", getResources().getDrawable(R.drawable.i10d));
        weatherIcons.put("10n", getResources().getDrawable(R.drawable.i10n));
        weatherIcons.put("11d", getResources().getDrawable(R.drawable.i11d));
        weatherIcons.put("11n", getResources().getDrawable(R.drawable.i11n));
        weatherIcons.put("13d", getResources().getDrawable(R.drawable.i13d));
        weatherIcons.put("13n", getResources().getDrawable(R.drawable.i13n));
        weatherIcons.put("50d", getResources().getDrawable(R.drawable.i50d));
        weatherIcons.put("50n", getResources().getDrawable(R.drawable.i50n));

        mSearchButton.setOnClickListener(v -> {
            swipeRefreshLayout.setRefreshing(true);
            getWeatherData(mEditText.getText().toString().trim());
            closeKeyBoard();
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getWeatherData(mEditText.getText().toString().trim());
                closeKeyBoard();
            }
        });

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
                closeKeyBoard();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mViewPager.setOffscreenPageLimit(3);
    }

    private void closeKeyBoard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
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
                    return todayFragment;
                case 1:
                    return threeDaysFragment;
                case 2:
                    return sevenDaysFragment;
            }
            return todayFragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

     void getWeatherData(String name) {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);


        Call<ListData> call = apiInterface.getWeatherData(name);

        call.enqueue(new Callback<ListData>() {

            @SuppressLint("CheckResult")
            @Override
            public void onResponse(Call<ListData> call, Response<ListData> response) {

                Observable<MainData[]> observable = Observable.fromCallable(() -> response.body().getMainData());
                error = false;
                observable.subscribe(todayFragment::setDataTodayFragment, throwable -> {
                    error = true;
                            closeKeyBoard();
                        });
                observable.subscribe(threeDaysFragment::setDataThreeDaysFragment, throwable -> {
                    closeKeyBoard();
                });
                observable.subscribe(sevenDaysFragment::setDataSevenDaysFragment, throwable -> {
                    closeKeyBoard();
                });

                if (error) {
                    todayFragment.todayViewFlipper.setDisplayedChild(1);
                    threeDaysFragment.threeDaysViewFlipper.setDisplayedChild(1);
                    sevenDaysFragment.sevenDaysViewFlipper.setDisplayedChild(1);
                    swipeRefreshLayout.setRefreshing(false);
                    error = false;


                } else {
                    todayFragment.todayViewFlipper.setDisplayedChild(0);
                    threeDaysFragment.threeDaysViewFlipper.setDisplayedChild(0);
                    sevenDaysFragment.sevenDaysViewFlipper.setDisplayedChild(0);
                    swipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<ListData> call, Throwable t) {
                todayFragment.setMainText("No Connection");
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}