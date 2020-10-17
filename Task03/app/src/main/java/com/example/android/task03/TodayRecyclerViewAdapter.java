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

public class TodayRecyclerViewAdapter extends RecyclerView.Adapter<TodayRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    Date date;
    Drawable image;
    String temperature;
    String hour;

    void setDataTodayFragment(String temp) {
        temperature = temp;
        date = new Date();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        hour = simpleDateFormat.format(date);
        notifyDataSetChanged();
    }

    public TodayRecyclerViewAdapter(Context ct) {
        mContext = ct;
    }

    public TodayRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.today_list_item, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull TodayRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.imageView.setImageDrawable(image);
        holder.textView1.setText(temperature);
        holder.textView2.setText(hour);
    }


    public int getItemCount() {
        return 10;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView1, textView2;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.temperature);
            textView2 = itemView.findViewById(R.id.current_time);
            imageView = itemView.findViewById(R.id.weather_condition);
        }
    }
}
