package com.example.android.task03.Retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class MainDataValues {

    @SerializedName("temp")
    String temp;

    @SerializedName("humidity")
    String humidity;

    public String getTemp() {
        char grad = 176;
        return temp + " " + grad + "C";
    }

    public String getHumidity() {
        return humidity;
    }
}
