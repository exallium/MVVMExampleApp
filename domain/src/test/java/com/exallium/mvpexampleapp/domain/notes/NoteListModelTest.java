package com.exallium.mvpexampleapp.domain.notes;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class NoteListModelTest {
    @InjectMocks
    NoteListModel testSubject;

    @Mock
    NoteRepository noteRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllNotes_delegatesToNoteRepository() throws Exception {
        // WHEN
        testSubject.getAllNotes();

        // THEN
        verify(noteRepository).getNotes();
    }
}