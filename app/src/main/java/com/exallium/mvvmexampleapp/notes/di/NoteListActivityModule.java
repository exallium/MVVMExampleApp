package com.exallium.mvvmexampleapp.notes.di;

import com.exallium.mvpexampleapp.domain.notes.NoteListModel;
import com.exallium.mvpexampleapp.domain.notes.NoteRepository;
import com.exallium.mvvmexampleapp.ActivityRouter;
import com.exallium.mvvmexampleapp.di.PerScreen;
import com.exallium.mvvmexampleapp.presentation.notes.NoteListViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class NoteListActivityModule {
    @Provides
    @PerScreen
    NoteListViewModel provideNoteListViewModel(ActivityRouter router, NoteListModel noteListModel) {
        return new NoteListViewModel(router, noteListModel);
    }

    @Provides
    @PerScreen
    NoteListModel provideNoteListModel(NoteRepository noteRepository) {
        return new NoteListModel(noteRepository);
    }
}
