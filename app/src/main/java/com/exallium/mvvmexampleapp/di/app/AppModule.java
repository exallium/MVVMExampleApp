package com.exallium.mvvmexampleapp.di.app;

import android.content.Context;

import com.exallium.mvvmexampleapp.ActivityRouter;
import com.exallium.mvvmexampleapp.AppExceptionFactory;
import com.exallium.mvvmexampleapp.di.services.ServiceModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {ServiceModule.class})
public class AppModule {

    private final Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    ActivityRouter provideActivityRouter() {
        return new ActivityRouter(context);
    }
    @Provides
    @Singleton
    AppExceptionFactory provideAppExceptionFactory() {
        return new AppExceptionFactory(context.getResources());
    }
}
