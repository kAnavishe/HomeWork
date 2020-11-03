package com.example.android.Task03.Retrofit;

import com.google.gson.annotations.SerializedName;

public class ListData {

    @SerializedName("list")
    private MainData[] mainData;

    @SerializedName("city")
    private CityData cityData;

    public MainData[] getMainData() {
        return mainData;
    }

    public CityData getCityData() {
        return cityData;
    }
}
