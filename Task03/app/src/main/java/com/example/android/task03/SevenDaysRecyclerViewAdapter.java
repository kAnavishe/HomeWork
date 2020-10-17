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

import java.text.SimpleDateFormat;
import java.util.Date;

public class SevenDaysRecyclerViewAdapter extends RecyclerView.Adapter<SevenDaysRecyclerViewAdapter.ViewHolder> {

    Context context;
    Drawable image;
    String fullDate;
    String temperature;

    public SevenDaysRecyclerViewAdapter(Context ct) {
        context = ct;
    }

    void setDataSevenDayFragment(String temp) {
        temperature = temp;
        Date date = new Date();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM/dd/yyyy");
        fullDate = simpleDateFormat.format(date);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.seven_days_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageDrawable(image);
        holder.textView1.setText(fullDate);
        holder.textView2.setText(temperature);
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1, textView2;
        public ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.date);
            textView2 = itemView.findViewById(R.id.temperature);
            imageView = itemView.findViewById(R.id.weather_condition);
        }
    }
}
