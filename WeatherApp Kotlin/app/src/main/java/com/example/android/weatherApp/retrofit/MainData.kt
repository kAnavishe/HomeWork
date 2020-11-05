package com.example.android.weatherApp.retrofit

import com.google.gson.annotations.SerializedName

class MainData(@field:SerializedName("main") val mainDataValues: MainDataValues) {
    @SerializedName("dt")
    var rawDate: Int? = null


    @SerializedName("weather")
    var weathers: Array<WeatherData?>? = null

    @SerializedName("dt_txt")
    var date: String? = null

    @JvmName("getDate1")
    fun getDate(): String {
        val parseDate = date!!.split(" ".toRegex()).toTypedArray()
        return parseDate[0]
    }

    val hourFromRawTxtDate: String
        get() {
            val parseDate = date!!.split(" ".toRegex()).toTypedArray()
            return parseDate[1].substring(0, 5)
        }

}