package com.charlest.openweathermap.localtiontracker.data;

import androidx.lifecycle.LiveData;

public interface ICurrentLocationRepository {

    void insertCurrentLocation(CurrentLocation currentLocation);

    LiveData<CurrentLocation> getCurrentLocation();

}
