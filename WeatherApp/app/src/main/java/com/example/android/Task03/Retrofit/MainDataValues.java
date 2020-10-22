package com.example.android.Task03.Retrofit;

import com.google.gson.annotations.SerializedName;

public class MainDataValues {

    @SerializedName("temp")
    double temp;

    @SerializedName("humidity")
    String humidity;

    public String getTemp() {
        int t = (int)temp;
            char grad = 176;
            return t + "" + grad;
        }

    public String getHumidity() {
        return humidity;
    }
}
