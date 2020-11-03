package com.example.android.Task03.Retrofit;

import android.content.Context;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(Context mContext) {
        OkHttpClient.Builder oktHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new NetworkConnectionInterceptor(mContext));

        if (retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl("https://api.openweathermap.org/data/2.5/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(oktHttpClient.build())
                    .build();
        }

        return retrofit;
    }
}
