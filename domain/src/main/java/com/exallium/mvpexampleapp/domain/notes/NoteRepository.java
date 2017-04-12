package com.exallium.mvpexampleapp.domain.notes;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface NoteRepository {
    Single<Note> saveNote(Note note);
    Single<Note> deleteNote(Note note);
    Single<Note> getNoteById(long id);
    Observable<Note> getNotes();
}
