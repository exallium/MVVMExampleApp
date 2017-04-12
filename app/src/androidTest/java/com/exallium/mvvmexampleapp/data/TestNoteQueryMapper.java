package com.exallium.mvvmexampleapp.data;

import com.exallium.mvpexampleapp.domain.notes.Note;
import com.exallium.mvpexampleapp.services.NoteService;

import io.reactivex.Observable;
import io.reactivex.Single;

public class TestNoteQueryMapper implements NoteService.QueryMapper {
    @Override
    public Single<Note> getNoteById(long id) {
        return Single.just(Note.builder().setId(id).setTitle("n" + id).setBody("nb" + id).build());
    }

    @Override
    public Observable<Note> getNotes() {
        return Observable.range(1, 10).map(s -> Note.builder().setId(s).setTitle("s" + s).build());
    }
}
