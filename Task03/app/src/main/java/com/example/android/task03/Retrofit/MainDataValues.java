package com.example.android.task03.Retrofit;

import com.google.gson.annotations.SerializedName;

public class MainDataValues {

    @SerializedName("temp")
    static String temp;

    @SerializedName("humidity")
    String humidity;

    public static String getTemp() {
        return temp;
    }

    public String getHumidity() {
        return humidity;
    }
}
