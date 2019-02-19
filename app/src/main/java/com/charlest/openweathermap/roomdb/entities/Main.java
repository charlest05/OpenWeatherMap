package com.charlest.openweathermap.roomdb.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Main {


    @Expose
    @SerializedName("temp")
    private double temperature;


    @Expose
    @SerializedName("pressure")
    private double pressure;


    @Expose
    @SerializedName("humidity")
    private double humidity;


    @Expose
    @SerializedName("temp_min")
    private double minTemperature;


    @Expose
    @SerializedName("temp_max")
    private double maxTemperature;

    public Main(double temperature, double pressure, double humidity, double minTemperature, double maxTemperature) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }
}
