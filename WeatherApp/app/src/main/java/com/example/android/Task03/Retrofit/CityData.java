package com.example.android.Task03.Retrofit;

import com.google.gson.annotations.SerializedName;

public class CityData {

    @SerializedName("timezone")
    int timezone;

    public int getTimezone() {
        return timezone;
    }
}
