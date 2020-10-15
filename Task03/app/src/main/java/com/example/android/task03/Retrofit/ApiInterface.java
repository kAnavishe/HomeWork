package com.example.android.task03.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiInterface {
    @GET("weather?appid=5722d2d4e58e7f38694182fdae59019d&units=metric")

    Call<MainData> getWeatherData(@Query("q") String name);
}
