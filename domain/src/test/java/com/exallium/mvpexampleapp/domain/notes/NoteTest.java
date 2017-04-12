package com.exallium.mvpexampleapp.domain.notes;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NoteTest {

    @Test
    public void builder_getsNoteWithDefaults() {
        // WHEN
        Note result = Note.builder().build();

        assertEquals(result.id(), 0);
        assertEquals(result.title(), "");
        assertEquals(result.body(), "");
        assertEquals(result.created(), 0);
        assertEquals(result.updated(), 0);
    }

}