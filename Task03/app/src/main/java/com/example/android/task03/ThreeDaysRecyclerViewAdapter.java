package com.example.android.task03;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.task03.Retrofit.MainDataValues;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreeDaysRecyclerViewAdapter extends RecyclerView.Adapter<ThreeDaysRecyclerViewAdapter.ViewHolder> {

    Context context;
    String dayOfWeek;
    String dayOfMonth;
    Drawable image;
    String temperature;


    public ThreeDaysRecyclerViewAdapter(Context ct) {
        context = ct;
    }
    public void setDataThreeDaysFragment(String temp){
        temperature = temp;
        Date date = new Date();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormatDayOfWeek = new SimpleDateFormat("E");
        dayOfWeek = (simpleDateFormatDayOfWeek.format(date));
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormatDayOfMonth = new SimpleDateFormat("dd/MMMM");
        dayOfMonth = (simpleDateFormatDayOfMonth.format(date));
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.three_days_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThreeDaysRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.imageView.setImageDrawable(image);
        holder.textView1.setText(dayOfWeek);
        holder.textView2.setText(dayOfMonth);
        holder.textView3.setText(temperature);
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
