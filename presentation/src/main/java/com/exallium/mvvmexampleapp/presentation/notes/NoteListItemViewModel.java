package com.exallium.mvvmexampleapp.presentation.notes;

import android.databinding.ObservableField;

import com.exallium.mvpexampleapp.domain.notes.Note;

public class NoteListItemViewModel {
    public final ObservableField<String> title = new ObservableField<>("");

    NoteListItemViewModel(Note note) {
        title.set(note.title());
    }
}
