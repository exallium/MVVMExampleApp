package com.exallium.mvvmexampleapp.di.data;

import com.exallium.mvpexampleapp.services.NoteService;
import com.exallium.mvvmexampleapp.data.TestNoteDataMapper;
import com.exallium.mvvmexampleapp.data.TestNoteQueryMapper;

public class TestDataModule extends DataModule {
    @Override
    NoteService.DataMapper provideNoteDataMapper() {
        return new TestNoteDataMapper();
    }

    @Override
    NoteService.QueryMapper provideNoteQueryMapper() {
        return new TestNoteQueryMapper();
    }
}
