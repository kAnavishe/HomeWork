package com.example.android.weatherApp.retrofit

import com.google.gson.annotations.SerializedName

class ListData {
    @SerializedName("list")
    val mainData: Array<MainData>? = null

    @SerializedName("city")
    val cityData: CityData? = null
}