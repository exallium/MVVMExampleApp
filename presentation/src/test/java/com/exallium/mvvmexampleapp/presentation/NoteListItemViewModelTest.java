package com.exallium.mvvmexampleapp.presentation;

import com.exallium.mvpexampleapp.domain.notes.Note;
import com.exallium.mvvmexampleapp.presentation.notes.NoteListItemViewModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class NoteListItemViewModelTest {

    NoteListItemViewModel testSubject;

    @Mock
    Note note;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void ctor_setsTitleFieldToNoteTitle() throws Exception {
        // GIVEN
        when(note.title()).thenReturn("title");

        // WHEN
        testSubject = new NoteListItemViewModel(note);

        // THEN
        assertEquals(testSubject.title.get(), "title");
    }
}