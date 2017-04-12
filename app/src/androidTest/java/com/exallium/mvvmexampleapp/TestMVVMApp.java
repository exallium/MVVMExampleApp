package com.exallium.mvvmexampleapp;

import com.exallium.mvvmexampleapp.di.app.AppModule;
import com.exallium.mvvmexampleapp.di.app.DaggerAppComponent;
import com.exallium.mvvmexampleapp.di.data.TestDataModule;
import com.exallium.mvvmexampleapp.di.services.ServiceModule;

public class TestMVVMApp extends MVVMApp {
    @Override
    public void setAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .serviceModule(new ServiceModule())
                .dataModule(new TestDataModule())
                .appModule(new AppModule(this))
                .build();
    }
}
