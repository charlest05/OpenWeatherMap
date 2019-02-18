package com.charlest.openweathermap.roomdb;

import android.app.Application;
import android.support.v4.app.INotificationSideChannel;

import com.charlest.openweathermap.roomdb.dao.WeatherDataDao;
import com.charlest.openweathermap.roomdb.entities.WeatherData;
import com.charlest.openweathermap.roomdb.typeconverters.WeatherListConverter;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {WeatherData.class}, exportSchema = false, version = 1)
@TypeConverters(WeatherListConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    public abstract WeatherDataDao weatherDataDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance(Application application) {
        if(INSTANCE == null) {
            synchronized (AppDatabase.class) {
                INSTANCE = Room.databaseBuilder(application, AppDatabase.class, "weatherdb").build();
            }
        }

        return INSTANCE;
    }
}
