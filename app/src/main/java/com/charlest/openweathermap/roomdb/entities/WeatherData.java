package com.charlest.openweathermap.roomdb.entities;

import android.util.Log;

import com.charlest.openweathermap.roomdb.typeconverters.WeatherListConverter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

    public String getFormattedWindData() {
        return wind.getSpeed() + " m/s";
    }

    public String getWeatherdescription() {
        if(weathers == null || weathers.size() == 0)
            return "";
        else
            return weathers.get(0).getDescription();
    }

    public String getPressure() {

        return main.getPressure() + " hpa";
    }

    public String getHumidity() {
        return main.getHumidity() + "%";
    }

    public String getSunrise() {
        Date date = new Date(sys.getSunrise() * 1000L);
        return new SimpleDateFormat("HH:mm", Locale.getDefault()).format(date);
    }

    public String getSunset() {
        Date date = new Date(sys.getSunset() * 1000L);
        return new SimpleDateFormat("HH:mm", Locale.getDefault()).format(date);
    }

    public String getFormattedCoordinates() {
        return "[" +coordinates.getLatitude() + ", " + coordinates.getLongitude() + "]";
    }

    public String getWeatherIconUrl() {
        return "http://openweathermap.org/img/w/" + weathers.get(0).getIcon() + ".png";
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

    public String getActualWeather() {
        if(weathers == null || weathers.size() == 0)
            return "";
        else
            return weathers.get(0).getMain();
    }

    public void setWeathers(List<Weather> weathers) {
        this.weathers = weathers;
    }

    public Main getMain() {
        return main;
    }

    public String getTemperature() {
        return String.valueOf(main.getTemperature()) + (char) 0x00B0;
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
