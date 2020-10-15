package com.example.android.task03.Retrofit;

import com.google.gson.annotations.SerializedName;

public class MainData {

    @SerializedName("main")
    private MainDataValues mainDataValues;

    public MainDataValues getMainDataValues() {
        return mainDataValues;
    }
}
