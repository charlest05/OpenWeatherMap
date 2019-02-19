package com.charlest.openweathermap.appcore;

import com.charlest.openweathermap.roomdb.entities.WeatherData;
import com.charlest.openweathermap.weatherlist.data.FetchWeatherListResponse;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServiceEndPoints {

    @GET("data/2.5/group")
    Single<Response<FetchWeatherListResponse>> fetchWeatherList(@Query("id") String cityIds, @Query("appid") String apiKey);

    @GET("data/2.5/weather")
    Single<Response<WeatherData>> fetchWeatherData(@Query("id") int cityId, @Query("appid") String apiKey);
}
