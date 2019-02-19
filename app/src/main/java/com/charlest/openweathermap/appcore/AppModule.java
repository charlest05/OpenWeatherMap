package com.charlest.openweathermap.appcore;

import android.app.Application;

import com.charlest.openweathermap.BuildConfig;
import com.charlest.openweathermap.localtiontracker.data.CurrentLocationDao;
import com.charlest.openweathermap.localtiontracker.data.CurrentLocationRepositoryRoomImpl;
import com.charlest.openweathermap.localtiontracker.data.ICurrentLocationRepository;
import com.charlest.openweathermap.roomdb.AppDatabase;
import com.charlest.openweathermap.roomdb.dao.WeatherDataDao;
import com.charlest.openweathermap.weatherrepo.IWeatherRepository;
import com.charlest.openweathermap.weatherrepo.WeatherRepositoryRetrofitImpl;
import com.charlest.openweathermap.weatherrepo.WeatherRepositoryRoomImpl;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import androidx.annotation.NonNull;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    private static final String BASE_URL = "http://api.openweathermap.org/";

    private AppInjector appInjector;

    public AppModule(AppInjector appInjector) {
        this.appInjector = appInjector;
    }

    @NonNull
    @Provides
    @Singleton
    public ApiServiceEndPoints provideApiServiceEndPoints() {

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();

        if(BuildConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClient.addInterceptor(httpLoggingInterceptor);
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();

        return retrofit.create(ApiServiceEndPoints.class);

    }

    @NonNull
    @Provides
    @Singleton
    public AppDatabase provideAppDatabase() {
        return AppDatabase.getInstance(appInjector);
    }

    @NonNull
    @Provides
    @Singleton
    public IWeatherRepository.IWeatherLocalRepository provideWeatherLocalRepository(AppDatabase appDatabase, Executor executor) {
        return new WeatherRepositoryRoomImpl(appDatabase.weatherDataDao(), executor);
    }

    @NonNull
    @Provides
    @Singleton
    public IWeatherRepository.IWeatherRemoteRepository provideWeatherRemoteRepository(ApiServiceEndPoints apiServiceEndPoints) {
        return new WeatherRepositoryRetrofitImpl(apiServiceEndPoints);
    }

    @NonNull
    @Provides
    @Singleton
    public WeatherDataDao provideWeatherDataDao(AppDatabase appDatabase) {
        return appDatabase.weatherDataDao();
    }

    @NonNull
    @Provides
    @Singleton
    public Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    @NonNull
    @Provides
    @Singleton
    public CurrentLocationDao provideCurrentLocationDao(AppDatabase appDatabase) {
        return appDatabase.currentLocationDao();
    }

    @NonNull
    @Provides
    @Singleton
    public ICurrentLocationRepository provideCurrentLocationRepository(CurrentLocationDao currentLocationDao, Executor executor) {
        return new CurrentLocationRepositoryRoomImpl(currentLocationDao, executor);
    }
}
