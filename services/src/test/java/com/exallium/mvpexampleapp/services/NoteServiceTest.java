package com.exallium.mvpexampleapp.services;

import com.exallium.mvpexampleapp.domain.notes.Note;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class NoteServiceTest {

    @InjectMocks
    NoteService testSubject;

    @Mock
    NoteService.QueryMapper queryMapper;

    @Mock
    NoteService.DataMapper dataMapper;

    @Mock
    Note note;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveNote_delegatesToDataMapper() throws Exception {
        // WHEN
        testSubject.saveNote(note);

        // THEN
        verify(dataMapper).saveNote(note);
    }

    @Test
    public void deleteNote_delegatesToDataMapper() throws Exception {
        // WHEN
        testSubject.deleteNote(note);

        // THEN
        verify(dataMapper).deleteNote(note);
    }

    @Test
    public void getNoteById_delegatesToQueryMapper() throws Exception {
        // WHEN
        testSubject.getNoteById(0);

        // THEN
        verify(queryMapper).getNoteById(0);
    }

    @Test
    public void getNotes_delegatesToQueryMapper() throws Exception {
        // WHEN
        testSubject.getNotes();

        // THEN
        verify(queryMapper).getNotes();
    }
}