package com.charlest.openweathermap.weatherrepo;

import com.charlest.openweathermap.appcore.AppInjector;
import com.charlest.openweathermap.roomdb.dao.WeatherDataDao;
import com.charlest.openweathermap.roomdb.entities.WeatherData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;

public class WeatherRepositoryRoomImpl implements IWeatherRepository.IWeatherLocalRepository {

    private WeatherDataDao weatherDataDao;

    private Executor dbExecutor;

    @Inject
    public WeatherRepositoryRoomImpl(WeatherDataDao weatherDataDao, Executor executor) {
        this.weatherDataDao = weatherDataDao;
        this.dbExecutor = executor;
    }

    @Override
    public LiveData<List<WeatherData>> fetchWeatherData() {
        return weatherDataDao.fetchWeatherDataList();
    }

    @Override
    public void insertWeatherData(final List<WeatherData> weatherDataList) {
        dbExecutor.execute(new Runnable() {
            @Override
            public void run() {
                weatherDataDao.insertWeatherDataDao(weatherDataList);
            }
        });
    }

    @Override
    public void updateWeatherData(final WeatherData weatherData) {
        dbExecutor.execute(new Runnable() {
            @Override
            public void run() {
                weatherDataDao.updateWeatherData(weatherData);
            }
        });
    }

    @Override
    public LiveData<WeatherData> getWeatherData(int weatherDataId) {
        return weatherDataDao.getWeatherData(weatherDataId);
    }
}
