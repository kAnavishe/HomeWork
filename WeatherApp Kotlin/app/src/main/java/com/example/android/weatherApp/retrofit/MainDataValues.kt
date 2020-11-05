package com.example.android.weatherApp.retrofit

import com.google.gson.annotations.SerializedName

class MainDataValues {
    @SerializedName("temp")
    var temp = 0.0

    @SerializedName("humidity")
    var humidity: String? = null
    fun getTemp(): String {
        val t = temp.toInt()
        val grad = 176.toChar()
        return t.toString() + "" + grad
    }
}