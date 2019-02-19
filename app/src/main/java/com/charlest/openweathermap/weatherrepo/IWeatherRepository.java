package com.charlest.openweathermap.weatherrepo;

import com.charlest.openweathermap.roomdb.entities.WeatherData;
import com.charlest.openweathermap.weatherdetail.data.IFetchWeatherDataCallback;
import com.charlest.openweathermap.weatherlist.data.IFetchWeatherListRequestCallback;

import java.util.List;

import androidx.lifecycle.LiveData;

public interface IWeatherRepository {

    interface IWeatherRemoteRepository {
        void fetchWeatherList(String cityIds, String apiKey, IFetchWeatherListRequestCallback iFetchWeatherListRequestCallback);

        void fetchWeatherData(int cityId, String apiKey, IFetchWeatherDataCallback iFetchWeatherDataCallback);

        void dispose();
    }

    interface IWeatherLocalRepository {
        LiveData<List<WeatherData>> fetchWeatherData();

        void insertWeatherData(List<WeatherData> weatherDataList);

        void updateWeatherData(WeatherData weatherData);

        LiveData<WeatherData> getWeatherData(int weatherDataId);
    }
}
