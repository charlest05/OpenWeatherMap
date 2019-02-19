package com.charlest.openweathermap.localtiontracker.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "CURRENT_LOCATION")
public class CurrentLocation {

    @PrimaryKey(autoGenerate = true)
    private int id = 1;

    private double latitude;

    private double longitude;

    public CurrentLocation(int id, double latitude, double longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getFormattedCoordinates() {
        return "[" + latitude + ", " + longitude + "]";
    }
}
