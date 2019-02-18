package com.charlest.openweathermap.roomdb.dao;

import com.charlest.openweathermap.roomdb.entities.WeatherData;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface WeatherDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void weatherDataDao(List<WeatherData> weatherData);

    @Query("SELECT * FROM WEATHER_DATA")
    LiveData<List<WeatherData>> fetchWeatherDataList();
}
