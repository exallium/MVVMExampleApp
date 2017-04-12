package com.exallium.mvvmexampleapp.di.data;

import com.exallium.mvpexampleapp.services.NoteService;
import com.exallium.mvvmexampleapp.data.notes.FakeNoteDataMapper;
import com.exallium.mvvmexampleapp.data.notes.FakeNoteQueryMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    @Provides
    @Singleton
    NoteService.DataMapper provideNoteDataMapper() {
        return new FakeNoteDataMapper();
    }

    @Provides
    @Singleton
    NoteService.QueryMapper provideNoteQueryMapper() {
        return new FakeNoteQueryMapper();
    }
}
