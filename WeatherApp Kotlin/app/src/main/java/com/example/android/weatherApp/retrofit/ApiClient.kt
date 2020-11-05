package com.example.android.weatherApp.retrofit

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private var retrofit: Retrofit? = null
    fun getClient(mContext: Context): Retrofit? {
        val oktHttpClient = OkHttpClient.Builder()
                .addInterceptor(NetworkConnectionInterceptor(mContext))
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl("https://api.openweathermap.org/data/2.5/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(oktHttpClient.build())
                    .build()
        }
        return retrofit
    }
}