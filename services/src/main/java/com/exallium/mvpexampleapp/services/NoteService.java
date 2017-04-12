package com.exallium.mvpexampleapp.services;

import com.exallium.mvpexampleapp.domain.notes.Note;
import com.exallium.mvpexampleapp.domain.notes.NoteRepository;

import io.reactivex.Observable;
import io.reactivex.Single;

public class NoteService implements NoteRepository {

    private final DataMapper dataMapper;
    private final QueryMapper queryMapper;

    public NoteService(DataMapper dataMapper, QueryMapper queryMapper) {
        this.dataMapper = dataMapper;
        this.queryMapper = queryMapper;
    }

    @Override
    public Single<Note> saveNote(Note note) {
        return dataMapper.saveNote(note);
    }

    @Override
    public Single<Note> deleteNote(Note note) {
        return dataMapper.deleteNote(note);
    }

    @Override
    public Single<Note> getNoteById(long id) {
        return queryMapper.getNoteById(id);
    }

    @Override
    public Observable<Note> getNotes() {
        return queryMapper.getNotes();
    }

    public interface DataMapper {
        Single<Note> saveNote(Note note);
        Single<Note> deleteNote(Note note);
    }

    public interface QueryMapper {
        Single<Note> getNoteById(long id);
        Observable<Note> getNotes();
    }
}
