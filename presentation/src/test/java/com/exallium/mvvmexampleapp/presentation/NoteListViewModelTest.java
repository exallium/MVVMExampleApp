package com.exallium.mvvmexampleapp.presentation;

import com.exallium.mvpexampleapp.domain.notes.Note;
import com.exallium.mvpexampleapp.domain.notes.NoteListModel;
import com.exallium.mvvmexampleapp.presentation.notes.NoteListViewModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;
import io.reactivex.schedulers.TestScheduler;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class NoteListViewModelTest {

    @InjectMocks
    NoteListViewModel testSubject;

    @Mock
    NoteListModel noteListModel;

    TestScheduler testScheduler = new TestScheduler();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        RxUtils.overrideAllSchedulers(testScheduler);
    }

    @Test
    public void refresh_whenModelHasData_updatesNotesField() throws Exception {
        // GIVEN
        when(noteListModel.getAllNotes()).thenReturn(Observable.range(1, 10)
                .map(id -> Note.builder().setId(id).build()));

        // WHEN
        testSubject.refresh();
        testScheduler.triggerActions();

        // THEN
        assertEquals(10, testSubject.notes.size());
    }

    @Test
    public void cancel_whenIsRefreshing_noUpdates() throws Exception {
        // GIVEN
        when(noteListModel.getAllNotes()).thenReturn(Observable.range(1, 10)
                .map(id -> Note.builder().setId(id).build()));
        testSubject.refresh();

        // WHEN
        testSubject.cancel();
        testScheduler.triggerActions();

        // THEN
        assertEquals(0, testSubject.notes.size());
    }

}