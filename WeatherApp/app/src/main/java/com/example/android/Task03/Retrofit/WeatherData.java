package com.example.android.Task03.Retrofit;

import com.google.gson.annotations.SerializedName;

public class WeatherData {
    @SerializedName("icon")
    String weatherIcon;

    public String getWeatherIcon() {
        return weatherIcon;
    }

    @SerializedName("main")
    String weatherCondition;

    public String getWeatherCondition() {
        return weatherCondition;
    }
}
