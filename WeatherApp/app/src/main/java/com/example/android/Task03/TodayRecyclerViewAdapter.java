package com.example.android.Task03;

import android.annotation.SuppressLint;
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

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TodayRecyclerViewAdapter extends RecyclerView.Adapter<TodayRecyclerViewAdapter.ViewHolder> {

    Context mContext;
    MainData[] mainData;
    private static int timeZone;

    public TodayRecyclerViewAdapter(Context ct) {
        mContext = ct;
    }

    public void setDataTodayFragment(MainData[] values) {
        mainData = values;
        notifyDataSetChanged();
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.today_list_item, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onBindViewHolder(@NonNull TodayRecyclerViewAdapter.ViewHolder holder, int position) {

        if (mainData != null) {
            holder.imageView.setImageDrawable(MainActivity.weatherIcons.get(mainData[position].getWeathers()[0].getWeatherIcon()));
        }
        if (mainData != null) {
            holder.textView1.setText(mainData[position].getMainDataValues().getTemp());
        }
        if (mainData != null) {

            Timestamp ts = new Timestamp((mainData[position].getRawDate() + timeZone) * 1000L);
            @SuppressLint("SimpleDateFormat") SimpleDateFormat isoFormat = new SimpleDateFormat("HH:mm");

            holder.textView2.setText(isoFormat.format(ts));
        }
    }

    public int getItemCount() {
        if (mainData != null) {
            return mainData.length;
        } else {
            return 1;
        }
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

    public static void getLocalDate(int unix) {
        timeZone = unix + 8 * 3600;
    }
}
