package com.charlest.openweathermap;

import com.charlest.openweathermap.appcore.AppInjector;
import com.charlest.openweathermap.localtiontracker.data.CurrentLocation;
import com.charlest.openweathermap.localtiontracker.data.ICurrentLocationRepository;
import com.charlest.openweathermap.weatherdetail.ui.WeatherDetailViewModel;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class LocationUpdatesViewModel extends ViewModel {

    @Inject
    ICurrentLocationRepository mCurrentLocationRepository;

    public LocationUpdatesViewModel() {
        AppInjector.getAppInjector().getAppCoreComponent().inject(this);
    }

    LiveData<CurrentLocation> getCurrentLocation() {
        return mCurrentLocationRepository.getCurrentLocation();
    }

}
