package com.charlest.openweathermap.weatherrepo;

import android.util.Log;

import com.charlest.openweathermap.BuildConfig;
import com.charlest.openweathermap.appcore.ApiServiceEndPoints;
import com.charlest.openweathermap.appcore.AppInjector;
import com.charlest.openweathermap.appcore.RequestResult;
import com.charlest.openweathermap.roomdb.entities.WeatherData;
import com.charlest.openweathermap.weatherdetail.data.IFetchWeatherDataCallback;
import com.charlest.openweathermap.weatherlist.data.FetchWeatherListResponse;
import com.charlest.openweathermap.weatherlist.data.IFetchWeatherListRequestCallback;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class WeatherRepositoryRetrofitImpl implements IWeatherRepository.IWeatherRemoteRepository {

    private ApiServiceEndPoints apiServiceEndPoints;

    private CompositeDisposable compositeDisposable;

    @Inject
    public WeatherRepositoryRetrofitImpl(ApiServiceEndPoints apiServiceEndPoints) {
        compositeDisposable = new CompositeDisposable();
        this.apiServiceEndPoints = apiServiceEndPoints;
    }

    @Override
    public void fetchWeatherList(String cityIds, String apiKey, final IFetchWeatherListRequestCallback iFetchWeatherListRequestCallback) {
        Disposable disposable = apiServiceEndPoints.fetchWeatherList(cityIds, apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Response<FetchWeatherListResponse>>() {
                    @Override
                    public void accept(Response<FetchWeatherListResponse> fetchWeatherListResponseResponse) throws Exception {
                        if(fetchWeatherListResponseResponse.isSuccessful()) {
                            FetchWeatherListResponse fetchWeatherListResponse = fetchWeatherListResponseResponse.body();

                            if(fetchWeatherListResponse != null && fetchWeatherListResponse.hasData()) {
                                fetchWeatherListResponse.setResultOk();
                                iFetchWeatherListRequestCallback.onFetchWeatherList(fetchWeatherListResponse);
                            } else {
                                iFetchWeatherListRequestCallback.onFetchWeatherList(
                                        new FetchWeatherListResponse(RequestResult.RESULT_FAILED));
                            }
                        } else {
                            iFetchWeatherListRequestCallback.onFetchWeatherList(
                                    new FetchWeatherListResponse(RequestResult.RESULT_ERROR));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable)  {
                        throwable.printStackTrace();
                        iFetchWeatherListRequestCallback.onFetchWeatherList(
                                new FetchWeatherListResponse(RequestResult.RESULT_ERROR));
                    }
                });

        compositeDisposable.add(disposable);
    }

    @Override
    public void fetchWeatherData(int cityId, String apiKey, final IFetchWeatherDataCallback iFetchWeatherDataCallback) {
        Disposable disposable = apiServiceEndPoints.fetchWeatherData(cityId, apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Response<WeatherData>>() {
                    @Override
                    public void accept(Response<WeatherData> fetchWeatherListResponseResponse) throws Exception {
                        if(fetchWeatherListResponseResponse.isSuccessful()) {
                            WeatherData fetchWeatherDataResponse = fetchWeatherListResponseResponse.body();

                            if(fetchWeatherDataResponse != null ) {
                                iFetchWeatherDataCallback.onFetchWeatherData(fetchWeatherDataResponse, new RequestResult(RequestResult.RESULT_OK));
                            } else {
                                iFetchWeatherDataCallback.onFetchWeatherData(null,
                                        new RequestResult( RequestResult.RESULT_FAILED));
                            }
                        } else {
                            iFetchWeatherDataCallback.onFetchWeatherData(null,
                                    new RequestResult(RequestResult.RESULT_ERROR));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable)  {
                        throwable.printStackTrace();
                        iFetchWeatherDataCallback.onFetchWeatherData(null,
                                new RequestResult(RequestResult.RESULT_ERROR));
                    }
                });

        compositeDisposable.add(disposable);
    }

    @Override
    public void dispose() {
        if (compositeDisposable != null)
            compositeDisposable.clear();
    }
}
