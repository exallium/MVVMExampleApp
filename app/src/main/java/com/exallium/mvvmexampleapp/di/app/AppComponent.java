package com.exallium.mvvmexampleapp.di.app;

import com.exallium.mvpexampleapp.domain.notes.NoteRepository;
import com.exallium.mvvmexampleapp.ActivityRouter;
import com.exallium.mvvmexampleapp.AppExceptionFactory;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    NoteRepository noteRepository();
    ActivityRouter activityRouter();
    AppExceptionFactory appExceptionFactory();
}
