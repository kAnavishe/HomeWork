package com.example.android.Task03.Retrofit;

import com.google.gson.annotations.SerializedName;

public class MainData {

    @SerializedName("weather")
    private WeatherData[] weathers;

    public WeatherData[] getWeathers() {
        return weathers;
    }

    @SerializedName("dt_txt")
    String date;

    public String getDate() {
        String [] parseDate = date.split(" ");
        return parseDate[0];
    }

    public String getHour() {
        String [] parseDate = date.split(" ");

        return parseDate[1].substring(0, 5);
    }

    @SerializedName("main")
    private MainDataValues mainDataValues;

    public MainData(MainDataValues mainDataValues) {
        this.mainDataValues = mainDataValues;
    }

    public MainDataValues getMainDataValues() {
        return mainDataValues;
    }
}
