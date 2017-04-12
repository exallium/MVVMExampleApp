package com.exallium.mvpexampleapp.domain.notes;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.NoSuchElementException;
import java.util.Objects;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class NoteEditModelTest {

    @InjectMocks
    NoteEditModel testSubject;

    @Mock
    NoteRepository noteRepository;

    @Mock
    NoteExceptionFactory noteExceptionFactory;

    TestObserver<Note> subscriber = new TestObserver<>();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getNoteById_whenUpstreamError_returnsNoteWithId() throws Exception {
        // GIVEN
        when(noteRepository.getNoteById(anyLong())).thenReturn(Single.error(new NoSuchElementException()));

        // WHEN
        testSubject.getNoteById(1).subscribe(subscriber);

        // THEN
        subscriber.assertNoErrors();
        subscriber.assertComplete();
        subscriber.assertValue(Note.builder().setId(1).build());
    }

    @Test
    public void getNoteById_whenUpstreamHasNote_returnsUpstreamNote() throws Exception {
        // GIVEN
        Note expected = Note.builder().setTitle("expected").setId(2).build();
        when(noteRepository.getNoteById(2)).thenReturn(Single.just(expected));

        // WHEN
        testSubject.getNoteById(2).subscribe(subscriber);

        // THEN
        subscriber.assertValue(expected);
    }

    @Test
    public void save_whenNoteHasEmptyTitle_emitsError() throws Exception {
        // GIVEN
        Exception e = new Exception();
        when(noteExceptionFactory.getEmptyTitleException()).thenReturn(e);

        // WHEN
        testSubject.save(1, "", "body").subscribe(subscriber);

        // THEN
        subscriber.assertError(e);
    }

    @Test
    public void save_whenNoteIsValid_emitsNote() throws Exception {
        // GIVEN
        Note expected = Note.builder().setTitle("expected").setId(2).build();
        when(noteRepository.getNoteById(anyLong())).thenReturn(Single.just(expected));
        when(noteRepository.saveNote(any(Note.class))).then(invocation -> Single.just(invocation.getArgument(0)));

        // WHEN
        testSubject.save(2, "title", "body").subscribe(subscriber);

        // THEN
        subscriber.assertNoErrors();
        subscriber.assertValue(note -> Objects.equals(note.body(), "body") && Objects.equals(note.title(), "title") && note.id() == 2);

    }
}