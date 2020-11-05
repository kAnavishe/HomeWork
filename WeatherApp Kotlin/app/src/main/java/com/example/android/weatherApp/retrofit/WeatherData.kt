package com.example.android.weatherApp.retrofit

import com.google.gson.annotations.SerializedName

class WeatherData {
    @SerializedName("icon")
    var weatherIcon: String? = null

    @SerializedName("main")
    var weatherCondition: String? = null
}