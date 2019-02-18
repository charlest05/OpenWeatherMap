package com.charlest.openweathermap.roomdb.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wind {

    @Expose
    @SerializedName("speed")
    private double speed;

    @Expose
    @SerializedName("deg")
    private int deg;

    @Expose
    @SerializedName("gust")
    private double gust;

    public Wind(double speed, int deg, double gust) {
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

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }

    public double getGust() {
        return gust;
    }

    public void setGust(double gust) {
        this.gust = gust;
    }
}
