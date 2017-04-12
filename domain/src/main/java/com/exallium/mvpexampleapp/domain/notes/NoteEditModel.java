package com.exallium.mvpexampleapp.domain.notes;

import io.reactivex.Single;

public class NoteEditModel {

    private final NoteRepository noteRepository;
    private final NoteExceptionFactory factory;

    public NoteEditModel(NoteRepository noteRepository, NoteExceptionFactory factory) {
        this.noteRepository = noteRepository;
        this.factory = factory;
    }

    public Single<Note> getNoteById(long id) {
        return noteRepository.getNoteById(id)
                .onErrorReturnItem(Note.builder().setId(id).build());
    }

    public Single<Note> save(long id, String title, String body) {
        if (title.isEmpty()) {
            return Single.error(factory.getEmptyTitleException());
        }

        return getNoteById(id)
                .map(note -> note.buildUpon()
                        .setTitle(title)
                        .setBody(body)
                        .setUpdated(System.currentTimeMillis())
                        .setCreated(note.created() == 0L ? System.currentTimeMillis() : note.created())
                        .build())
                .flatMap(noteRepository::saveNote);
    }
}
