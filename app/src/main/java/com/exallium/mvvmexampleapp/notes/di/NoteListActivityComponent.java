package com.exallium.mvvmexampleapp.notes.di;

import com.exallium.mvvmexampleapp.di.PerScreen;
import com.exallium.mvvmexampleapp.di.app.AppComponent;
import com.exallium.mvvmexampleapp.notes.NoteListActivity;

import dagger.Component;

@PerScreen
@Component(dependencies = AppComponent.class, modules = NoteListActivityModule.class)
public interface NoteListActivityComponent {
    void inject(NoteListActivity noteListActivity);
}
