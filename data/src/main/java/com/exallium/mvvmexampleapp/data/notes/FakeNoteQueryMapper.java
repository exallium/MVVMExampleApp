package com.exallium.mvvmexampleapp.data.notes;

import com.exallium.mvpexampleapp.domain.notes.Note;
import com.exallium.mvpexampleapp.services.NoteService;

import io.reactivex.Observable;
import io.reactivex.Single;

public class FakeNoteQueryMapper implements NoteService.QueryMapper {
    @Override
    public Single<Note> getNoteById(long id) {
        return Single.just(buildNoteFromId(id));
    }

    @Override
    public Observable<Note> getNotes() {
        return Observable.range(0, 10).map(this::buildNoteFromId);
    }

    private Note buildNoteFromId(long id) {
        return Note.builder().setId(id)
                .setTitle("Note " + id)
                .setBody("Body " + id)
                .setCreated(0)
                .setUpdated(0)
                .build();
    }
}
