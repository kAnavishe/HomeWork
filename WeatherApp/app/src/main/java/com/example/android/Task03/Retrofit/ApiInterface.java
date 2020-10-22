package com.example.android.Task03.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiInterface {
    @GET("forecast?appid=20643d0926a7e824ed846f8a8ab41274&units=metric")
    Call<ListData> getWeatherData(@Query("q") String name);
}
