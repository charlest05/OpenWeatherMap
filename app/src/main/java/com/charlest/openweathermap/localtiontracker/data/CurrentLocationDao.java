package com.charlest.openweathermap.localtiontracker.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface CurrentLocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCurrentLocation(CurrentLocation currentLocation);

    @Query("SELECT * FROM CURRENT_LOCATION")
    LiveData<CurrentLocation> getCurrentLocation();

}
