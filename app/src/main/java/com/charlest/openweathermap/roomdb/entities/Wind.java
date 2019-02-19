package com.charlest.openweathermap.roomdb.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wind {

    @Expose
    @SerializedName("speed")
    private double speed;

    @Expose
    @SerializedName("deg")
    private double deg;

    @Expose
    @SerializedName("gust")
    private double gust;

    public Wind(double speed, double deg, double gust) {
        this.speed = speed;
        this.deg = deg;
        this.gust = gust;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getGust() {
        return gust;
    }

    public void setGust(double gust) {
        this.gust = gust;
    }

    public double getDeg() {
        return deg;
    }

    public void setDeg(double deg) {
        this.deg = deg;
    }
}
