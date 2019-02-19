package com.charlest.openweathermap.appcore;

import android.app.Application;

public class AppInjector extends Application {

    private static AppInjector appInjector;

    private AppCoreComponent appCoreComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appInjector = this;

        appCoreComponent = DaggerAppCoreComponent.builder()
                    .appModule(new AppModule(appInjector))
                .build();
    }

    public static AppInjector getAppInjector() {
        return appInjector;
    }

    public AppCoreComponent getAppCoreComponent() {
        return appCoreComponent;
    }
}
