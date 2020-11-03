package com.example.android.Task03.Retrofit;

import com.google.gson.annotations.SerializedName;

public class MainData {

    @SerializedName("dt")
    public int rawDate;

    @SerializedName("weather")
    private WeatherData[] weathers;

    @SerializedName("dt_txt")
    String date;

    @SerializedName("main")
    private MainDataValues mainDataValues;

    public WeatherData[] getWeathers() {
        return weathers;
    }

    public int getRawDate() {
        return rawDate;
    }

    public MainData(MainDataValues mainDataValues) {
        this.mainDataValues = mainDataValues;
    }

    public MainDataValues getMainDataValues() {
        return mainDataValues;
    }

    public String getDate() {
        String[] parseDate = date.split(" ");
        return parseDate[0];
    }

    public String getHourFromRawTxtDate() {
        String[] parseDate = date.split(" ");
        return parseDate[1].substring(0, 5);
    }
}
