package com.charlest.openweathermap.weatherdetail.data;

import com.charlest.openweathermap.appcore.RequestResult;
import com.charlest.openweathermap.roomdb.entities.WeatherData;

public interface IFetchWeatherDataCallback {
    void onFetchWeatherData(WeatherData weatherData, RequestResult requestResult);
}
