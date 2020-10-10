package com.example.android.task03;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    ViewPager mPager;
    PagerAdapter mAdapter;
    Toolbar mToolBar;
    TabLayout mTabLayout;
    TabItem mDogTabItem;
    TabItem mCarTabItem;
    TabItem mRocketTabItem;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolBar = findViewById(R.id.toolBar);
        mToolBar.setTitle(getResources().getString(R.string.title));
        //   setSupportActionBar(mToolBar);

        mTabLayout = findViewById(R.id.tabLayout);
        mDogTabItem = findViewById(R.id.dogTabItem);
        mCarTabItem = findViewById(R.id.carTabItem);
        mRocketTabItem = findViewById(R.id.rocketTabItem);

        mPager = findViewById(R.id.ViewPager);
        mAdapter = new MyAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());
        mPager.setAdapter(mAdapter);

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mPager.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Log.d("Maks", "OnScrollChange : " + oldScrollX + " " + scrollX);
            }
        });

    }

    private static class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new FragmentDog();
                case 1:
                    return new FragmentRocket();
                case 2:
                    return new FragmentCar();
            }
            return new FragmentDog();
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
}