package com.exallium.mvvmexampleapp.data;

import com.exallium.mvpexampleapp.domain.notes.Note;
import com.exallium.mvpexampleapp.services.NoteService;

import io.reactivex.Single;

public class TestNoteDataMapper implements NoteService.DataMapper {
    @Override
    public Single<Note> saveNote(Note note) {
        return Single.just(note);
    }

    @Override
    public Single<Note> deleteNote(Note note) {
        return Single.just(note);
    }
}
