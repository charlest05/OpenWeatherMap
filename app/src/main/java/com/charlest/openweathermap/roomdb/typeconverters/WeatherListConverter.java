package com.charlest.openweathermap.roomdb.typeconverters;

import com.charlest.openweathermap.roomdb.entities.Weather;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import androidx.room.TypeConverter;

public class WeatherListConverter {

    @TypeConverter
    public static List<Weather> fromJsonString(String weathers) {

        if(weathers == null || weathers.isEmpty()){
            return (null);
        }

        Gson gson = new Gson();
        Type type = new TypeToken<List<Weather>>(){}.getType();
        return gson.fromJson(weathers, type);
    }

    @TypeConverter
    public static String toJsonString(List<Weather> weathers) {
        return weathers == null || weathers.size() == 0 ? ""
                : new Gson().toJson(weathers, new TypeToken<List<Weather>>(){}.getType());
    }
}
