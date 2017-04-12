package com.exallium.mvvmexampleapp.di.services;

import com.exallium.mvpexampleapp.domain.notes.NoteRepository;
import com.exallium.mvpexampleapp.services.NoteService;
import com.exallium.mvvmexampleapp.di.data.DataModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = DataModule.class)
public class ServiceModule {
    @Provides
    @Singleton
    NoteRepository provideNoteRepository(NoteService.DataMapper dataMapper, NoteService.QueryMapper queryMapper) {
        return new NoteService(dataMapper, queryMapper);
    }
}
