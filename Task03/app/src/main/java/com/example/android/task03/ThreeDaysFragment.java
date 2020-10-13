package com.example.android.task03;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ThreeDaysFragment extends Fragment {

    String dayOfWeek;
    String dayOfMonth;
    Drawable image;
    String temperature;
    ArrayList<Date> datesArray;

    public ThreeDaysFragment() {
    }

    public void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);

        Date date = new Date();
        SimpleDateFormat simpleDateFormatDayOfWeek = new SimpleDateFormat("E");
        dayOfWeek = (simpleDateFormatDayOfWeek.format(date)).toString();
        SimpleDateFormat simpleDateFormatDayOfMonth = new SimpleDateFormat("dd/MMMM");
        dayOfMonth = (simpleDateFormatDayOfMonth.format(date)).toString();
        image = getResources().getDrawable(R.drawable.rain);
        temperature = "+23";
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_three_days, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.three_days_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(new ThreeDaysRecyclerViewAdapter(getContext(), dayOfWeek, dayOfMonth, image, temperature));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return root;
    }
}
