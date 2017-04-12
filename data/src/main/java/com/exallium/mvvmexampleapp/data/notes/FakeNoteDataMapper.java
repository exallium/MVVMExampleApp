package com.exallium.mvvmexampleapp.data.notes;

import com.exallium.mvpexampleapp.domain.notes.Note;
import com.exallium.mvpexampleapp.services.NoteService;

import io.reactivex.Single;

public class FakeNoteDataMapper implements NoteService.DataMapper {
    @Override
    public Single<Note> saveNote(Note note) {
        return Single.just(note);
    }

    @Override
    public Single<Note> deleteNote(Note note) {
        return Single.just(note);
    }
}
