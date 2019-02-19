package com.charlest.openweathermap.weatherdetail.ui;

import com.charlest.openweathermap.BuildConfig;
import com.charlest.openweathermap.appcore.AppInjector;
import com.charlest.openweathermap.appcore.RequestResult;
import com.charlest.openweathermap.roomdb.entities.WeatherData;
import com.charlest.openweathermap.weatherdetail.data.IFetchWeatherDataCallback;
import com.charlest.openweathermap.weatherlist.data.FetchWeatherListResponse;
import com.charlest.openweathermap.weatherlist.data.IFetchWeatherListRequestCallback;
import com.charlest.openweathermap.weatherlist.ui.WeatherListUIState;
import com.charlest.openweathermap.weatherrepo.IWeatherRepository;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WeatherDetailViewModel extends ViewModel {

    private int cityID = -1;

    private MutableLiveData<WeatherDetailUIState> weatherListUIStateMutableLiveData =  new MutableLiveData<>();

    @Inject
    IWeatherRepository.IWeatherLocalRepository mWeatherLocalRepository;

    @Inject
    IWeatherRepository.IWeatherRemoteRepository mWeatherRemoteRepository;

    public WeatherDetailViewModel() {
        AppInjector.getAppInjector().getAppCoreComponent().inject(this);
    }

    public void refreshData() {
        weatherListUIStateMutableLiveData.postValue(WeatherDetailUIState.LOADING_WEATHER_DATA);
        mWeatherRemoteRepository.fetchWeatherData(cityID, BuildConfig.ApiKey, new IFetchWeatherDataCallback() {
            @Override
            public void onFetchWeatherData(WeatherData weatherData, RequestResult requestResult) {
                if(requestResult.isResultOk()) {
                    mWeatherLocalRepository.updateWeatherData(weatherData);
                    weatherListUIStateMutableLiveData.postValue(WeatherDetailUIState.FETCH_WEATHER_DATA_SUCCESS);
                } else if (requestResult.isResultFailed()) {
                    weatherListUIStateMutableLiveData.postValue(WeatherDetailUIState.NO_RESULTS_FOUND);
                } else {
                    weatherListUIStateMutableLiveData.postValue(WeatherDetailUIState.ERROR);
                }
            }
        });
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    public void disposeNetworkCalls() {
        mWeatherRemoteRepository.dispose();
    }

    public MutableLiveData<WeatherDetailUIState> getWeatherListUIStateMutableLiveData() {
        return weatherListUIStateMutableLiveData;
    }

    public LiveData<WeatherData> getWeatherData() {
        return mWeatherLocalRepository.getWeatherData(cityID);
    }
}
