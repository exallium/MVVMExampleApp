package com.exallium.mvvmexampleapp.notes.di;

import com.exallium.mvpexampleapp.domain.notes.NoteEditModel;
import com.exallium.mvpexampleapp.domain.notes.NoteRepository;
import com.exallium.mvvmexampleapp.ActivityRouter;
import com.exallium.mvvmexampleapp.AppExceptionFactory;
import com.exallium.mvvmexampleapp.di.PerScreen;
import com.exallium.mvvmexampleapp.presentation.notes.NoteEditViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class NoteEditActivityModule {

    private final long noteId;

    public NoteEditActivityModule(long noteId) {
        this.noteId = noteId;
    }

    @Provides
    @PerScreen
    NoteEditViewModel provideNoteEditViewModel(NoteEditModel noteEditModel, ActivityRouter router) {
        return new NoteEditViewModel(noteId, router, noteEditModel);
    }

    @Provides
    @PerScreen
    NoteEditModel provideNoteEditModel(NoteRepository noteRepository, AppExceptionFactory appExceptionFactory) {
        return new NoteEditModel(noteRepository, appExceptionFactory);
    }
}
