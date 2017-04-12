package com.exallium.mvvmexampleapp.presentation;

import com.exallium.mvpexampleapp.domain.notes.Note;

public interface Router {
    void goBack();
    void goToNoteAdd();
    void goToNoteModify(Note note);
}
