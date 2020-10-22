package com.example.android.Task03;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.Task03.Retrofit.MainData;

public class SevenDaysRecyclerViewAdapter extends RecyclerView.Adapter<SevenDaysRecyclerViewAdapter.ViewHolder> {

    Context context;
    MainData[] mainData;
    int count;

    public SevenDaysRecyclerViewAdapter(Context ct) {
        context = ct;
        count = 0;
    }

    void setDataSevenDayFragment(MainData[] values) {
        count = 0;
        mainData = values;
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
        if (mainData != null) {
            holder.imageView.setImageDrawable(MainActivity.weatherIcons.get(mainData[count].getWeathers()[0].getWeatherIcon()));
        }
        if (mainData != null) {
            holder.textView1.setText(mainData[count].getDate());
        }
        if (mainData != null) {
            holder.textView2.setText(mainData[count].getMainDataValues().getTemp());
        }
        count += 8;
    }

    @Override
    public int getItemCount() {
        return 5;
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
