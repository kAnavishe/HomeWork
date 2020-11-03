package com.example.android.Task03.Retrofit;

import com.google.gson.annotations.SerializedName;

public class WeatherData {
    @SerializedName("icon")
    String weatherIcon;

    @SerializedName("main")
    String weatherCondition;

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }
}
