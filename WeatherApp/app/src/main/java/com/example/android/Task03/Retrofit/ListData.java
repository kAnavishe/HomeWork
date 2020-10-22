package com.example.android.Task03.Retrofit;

import com.google.gson.annotations.SerializedName;

public class ListData {

    @SerializedName("list")
    private MainData[] mainData;

    public ListData(MainData[] mainData) {
        this.mainData = mainData;
    }

    public MainData[] getMainData() {
        return mainData;
    }
}
