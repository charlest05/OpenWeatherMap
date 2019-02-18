package com.charlest.openweathermap.roomdb.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.room.ColumnInfo;

public class Weather {


    @Expose
    @SerializedName("id")
    @ColumnInfo(name = "weather_id")
    private int weatherId;


    @Expose
    @SerializedName("main")
    private String main;


    @Expose
    @SerializedName("description")
    private String description;


    @Expose
    @SerializedName("icon")
    private String icon;

    public Weather(int weatherId, String main, String description, String icon) {
        this.weatherId = weatherId;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

    public int getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(int weatherId) {
        this.weatherId = weatherId;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
