package com.exallium.mvvmexampleapp.notes.di;

import com.exallium.mvvmexampleapp.di.PerScreen;
import com.exallium.mvvmexampleapp.di.app.AppComponent;
import com.exallium.mvvmexampleapp.notes.NoteEditActivity;

import dagger.Component;

@PerScreen
@Component(dependencies = AppComponent.class, modules = NoteEditActivityModule.class)
public interface NoteEditActivityComponent {
    void inject(NoteEditActivity noteEditActivity);
}
