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

import java.time.LocalDate;

public class FiveDaysRecyclerViewAdapter extends RecyclerView.Adapter<FiveDaysRecyclerViewAdapter.ViewHolder> {

    private Context context;
    MainData[] mainData;
    private int count;

    public FiveDaysRecyclerViewAdapter(Context ct) {
        context = ct;
    }

    void setDataFiveDayFragment(MainData[] values) {
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (mainData != null) {
            holder.imageView.setImageDrawable(MainActivity.weatherIcons.get(mainData[count].getWeathers()[0].getWeatherIcon()));
        }
        if (mainData != null) {
            LocalDate rowDate = LocalDate.parse(mainData[count].getDate());
            String date = rowDate.getDayOfMonth() + "." + rowDate.getMonthValue() + "." + rowDate.getYear();
            holder.textView1.setText(date);
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
