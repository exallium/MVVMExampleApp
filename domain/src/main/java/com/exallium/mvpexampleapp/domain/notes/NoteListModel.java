package com.exallium.mvpexampleapp.domain.notes;

import io.reactivex.Observable;

public class NoteListModel {

    private final NoteRepository noteRepository;

    public NoteListModel(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Observable<Note> getAllNotes() {
        return noteRepository.getNotes();
    }
}
