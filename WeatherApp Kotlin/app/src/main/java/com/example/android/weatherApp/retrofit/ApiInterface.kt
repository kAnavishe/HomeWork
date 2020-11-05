package com.example.android.weatherApp.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("forecast?appid=20643d0926a7e824ed846f8a8ab41274&units=metric")
    fun getWeatherData(@Query("q") name: String?): Call<ListData?>?
}