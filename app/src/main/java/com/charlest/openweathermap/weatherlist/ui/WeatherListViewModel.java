package com.charlest.openweathermap.weatherlist.ui;

import android.util.Log;

import com.charlest.openweathermap.BuildConfig;
import com.charlest.openweathermap.appcore.AppInjector;
import com.charlest.openweathermap.roomdb.entities.WeatherData;
import com.charlest.openweathermap.weatherlist.data.FetchWeatherListResponse;
import com.charlest.openweathermap.weatherlist.data.IFetchWeatherListRequestCallback;
import com.charlest.openweathermap.weatherrepo.IWeatherRepository;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WeatherListViewModel extends ViewModel {

    private static final String CITY_IDS = "2643743,3067696,3669859";

    private MutableLiveData<WeatherListUIState> weatherListUIStateMutableLiveData =  new MutableLiveData<WeatherListUIState>();

    @Inject
    IWeatherRepository.IWeatherLocalRepository mWeatherLocalRepository;

    @Inject
    IWeatherRepository.IWeatherRemoteRepository mWeatherRemoteRepository;

    public WeatherListViewModel() {
        AppInjector.getAppInjector().getAppCoreComponent().inject(this);
    }

    public void refreshData() {
        weatherListUIStateMutableLiveData.postValue(WeatherListUIState.LOADING_WEATHER_DATA);
        mWeatherRemoteRepository.fetchWeatherList(CITY_IDS, BuildConfig.ApiKey, new IFetchWeatherListRequestCallback() {
            @Override
            public void onFetchWeatherList(FetchWeatherListResponse fetchWeatherListResponse) {
                if(fetchWeatherListResponse.isResultOk()) {

                    mWeatherLocalRepository.insertWeatherData(fetchWeatherListResponse.getWeatherDataList());
                    weatherListUIStateMutableLiveData.postValue(WeatherListUIState.FETCH_WEATHER_DATA_SUCCESS);
                } else if (fetchWeatherListResponse.isResultFailed()) {
                    weatherListUIStateMutableLiveData.postValue(WeatherListUIState.NO_RESULTS_FOUND);
                } else {
                    weatherListUIStateMutableLiveData.postValue(WeatherListUIState.ERROR);
                }
            }
        });
    }

    public LiveData<List<WeatherData>> getWeatherList() {
        return mWeatherLocalRepository.fetchWeatherData();
    }

    public void disposeNetworkCalls() {
        mWeatherRemoteRepository.dispose();
    }

    public MutableLiveData<WeatherListUIState> getWeatherListUIStateMutableLiveData() {
        return weatherListUIStateMutableLiveData;
    }
}
