package com.charlest.openweathermap.roomdb.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sys {

    @Expose
    @SerializedName("type")
    private int type;

    @Expose
    @SerializedName("id")
    private int sysid;

    @Expose
    @SerializedName("message")
    private double message;

    @Expose
    @SerializedName("country")
    private String country;

    @Expose
    @SerializedName("sunrise")
    private long sunrise;

    @Expose
    @SerializedName("sunset")
    private long sunset;

    public Sys(int type, int sysid, double message, String country, long sunrise, long sunset) {
        this.type = type;
        this.sysid = sysid;
        this.message = message;
        this.country = country;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSysid() {
        return sysid;
    }

    public void setSysid(int sysid) {
        this.sysid = sysid;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getSunrise() {
        return sunrise;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }
}
