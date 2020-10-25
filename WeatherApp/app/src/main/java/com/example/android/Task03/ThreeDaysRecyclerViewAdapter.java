package com.example.android.Task03;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.Task03.Retrofit.MainData;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class ThreeDaysRecyclerViewAdapter extends RecyclerView.Adapter<ThreeDaysRecyclerViewAdapter.ViewHolder> {

    Context context;
    MainData[] mainData;
    int count;


    public ThreeDaysRecyclerViewAdapter(Context ct) {
        context = ct;
        count = 0;
    }

    public void setDataThreeDaysFragment(MainData[] values) {
        mainData = values;
        count = 0;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.three_days_list_item, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ThreeDaysRecyclerViewAdapter.ViewHolder holder, int position) {


        if (mainData != null) {
            holder.imageView.setImageDrawable(MainActivity.weatherIcons.get(mainData[count].getWeathers()[0].getWeatherIcon()));
        }

        if (mainData != null) {
            LocalDate date = LocalDate.parse(mainData[count].getDate());
            String dayOfMonth = date.getDayOfMonth() + " " + date.getMonth();
            holder.textView2.setText(dayOfMonth);
        }
        if (mainData != null) {
            LocalDate date = LocalDate.parse(mainData[count].getDate());
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            holder.textView1.setText(dayOfWeek.toString());
        }
        if (mainData != null) {
            holder.textView3.setText(mainData[count].getMainDataValues().getTemp());
            count += 8;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1, textView2, textView3;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.day_of_week);
            textView2 = itemView.findViewById(R.id.day_of_month);
            textView3 = itemView.findViewById(R.id.temperature);
            imageView = itemView.findViewById(R.id.weather_condition);
        }
    }
}
