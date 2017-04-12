package com.exallium.mvvmexampleapp;

import android.content.res.Resources;

import com.exallium.mvpexampleapp.domain.notes.NoteExceptionFactory;

public class AppExceptionFactory implements NoteExceptionFactory {

    private final Resources resources;

    public AppExceptionFactory(Resources resources) {
        this.resources = resources;
    }

    @Override
    public Exception getEmptyTitleException() {
        return new Exception(resources.getString(R.string.note_empty_title_exception));
    }
}
