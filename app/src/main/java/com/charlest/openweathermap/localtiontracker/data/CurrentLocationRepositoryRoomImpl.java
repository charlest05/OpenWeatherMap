package com.charlest.openweathermap.localtiontracker.data;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;

public class CurrentLocationRepositoryRoomImpl implements ICurrentLocationRepository {

    private CurrentLocationDao currentLocationDao;

    private Executor dbExecutor;

    @Inject
    public CurrentLocationRepositoryRoomImpl(CurrentLocationDao currentLocationDao, Executor dbExecutor) {
        this.currentLocationDao = currentLocationDao;
        this.dbExecutor = dbExecutor;
    }

    @Override
    public void insertCurrentLocation(final CurrentLocation currentLocation) {
        dbExecutor.execute(new Runnable() {
            @Override
            public void run() {
                currentLocationDao.insertCurrentLocation(currentLocation);
            }
        });
    }

    @Override
    public LiveData<CurrentLocation> getCurrentLocation() {
        return currentLocationDao.getCurrentLocation();
    }
}
