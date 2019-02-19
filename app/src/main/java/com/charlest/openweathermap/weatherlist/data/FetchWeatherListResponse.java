package com.charlest.openweathermap.weatherlist.data;

import com.charlest.openweathermap.appcore.RequestResult;
import com.charlest.openweathermap.roomdb.entities.WeatherData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FetchWeatherListResponse extends RequestResult {

    @Expose
    @SerializedName("cnt")
    private int count;

    @Expose
    @SerializedName("list")
    private List<WeatherData> weatherDataList;

    public FetchWeatherListResponse(int resultCode, int count, List<WeatherData> weatherDataList) {
        super(resultCode);
        this.count = count;
        this.weatherDataList = weatherDataList;
    }

    public FetchWeatherListResponse(int resultCode) {
        super(resultCode);
    }

    public int getCount() {
        return count;
    }

    public boolean hasData() {
        return count > 0;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<WeatherData> getWeatherDataList() {
        return weatherDataList;
    }

    public void setWeatherDataList(List<WeatherData> weatherDataList) {
        this.weatherDataList = weatherDataList;
    }


}
