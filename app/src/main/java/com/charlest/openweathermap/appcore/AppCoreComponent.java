package com.charlest.openweathermap.appcore;

import com.charlest.openweathermap.weatherlist.ui.WeatherListViewModel;
import com.charlest.openweathermap.weatherrepo.IWeatherRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppCoreComponent {

    void inject(IWeatherRepository.IWeatherRemoteRepository iWeatherRemoteRepository);

    void inject(IWeatherRepository.IWeatherLocalRepository iWeatherLocalRepository);

    void inject(WeatherListViewModel weatherListViewModel);
}
