package com.exallium.mvvmexampleapp;

import android.app.Application;

import com.exallium.mvvmexampleapp.di.app.AppComponent;
import com.exallium.mvvmexampleapp.di.app.AppModule;
import com.exallium.mvvmexampleapp.di.app.DaggerAppComponent;
import com.exallium.mvvmexampleapp.di.data.DataModule;
import com.exallium.mvvmexampleapp.di.services.ServiceModule;

import timber.log.Timber;

public class MVVMApp extends Application {

    protected static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setAppComponent();
        setUpTimber();
    }

    public void setAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .appModule(new AppModule(this))
            .dataModule(new DataModule())
            .serviceModule(new ServiceModule())
            .build();
    }

    public void setUpTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
