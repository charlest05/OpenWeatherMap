package com.charlest.openweathermap.roomdb.entities;

import com.charlest.openweathermap.roomdb.typeconverters.WeatherListConverter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

@Entity(tableName = "WEATHER_DATA")
public class WeatherData {


    @PrimaryKey(autoGenerate = true)
    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @Embedded
    @SerializedName("coord")
    private Coordinates coordinates;

    @Expose
    @Embedded
    @SerializedName("sys")
    private Sys sys;

    @Expose
    @SerializedName("weather")
    @TypeConverters(WeatherListConverter.class)
    private List<Weather> weathers;

    @Expose
    @Embedded
    @SerializedName("main")
    private Main main;

    @Expose
    @SerializedName("visibility")
    private int visibility;

    @Expose
    @Embedded
    @SerializedName("wind")
    private Wind wind;

    @Expose
    @SerializedName("dt")
    private long dt;

    @Expose
    @SerializedName("name")
    private String name;

    public WeatherData(Coordinates coordinates, Sys sys, List<Weather> weathers, Main main, int visibility, Wind wind, long dt, int id, String name) {
        this.coordinates = coordinates;
        this.sys = sys;
        this.weathers = weathers;
        this.main = main;
        this.visibility = visibility;
        this.wind = wind;
        this.dt = dt;
        this.id = id;
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public List<Weather> getWeathers() {
        return weathers;
    }

    public void setWeathers(List<Weather> weathers) {
        this.weathers = weathers;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
