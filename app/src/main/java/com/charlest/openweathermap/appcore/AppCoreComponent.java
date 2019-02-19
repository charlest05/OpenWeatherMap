package com.charlest.openweathermap.appcore;

import com.charlest.openweathermap.localtiontracker.LocationTrackerService;
import com.charlest.openweathermap.LocationUpdatesViewModel;
import com.charlest.openweathermap.weatherdetail.ui.WeatherDetailViewModel;
import com.charlest.openweathermap.weatherlist.ui.WeatherListViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppCoreComponent {

    void inject(WeatherListViewModel weatherListViewModel);

    void inject(WeatherDetailViewModel weatherDetailViewModel);

    void inject(LocationTrackerService trackerService);

    void inject(LocationUpdatesViewModel locationUpdatesViewModel);
}
