package com.exallium.mvvmexampleapp.presentation.notes;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.exallium.mvpexampleapp.domain.notes.Note;
import com.exallium.mvpexampleapp.domain.notes.NoteListModel;
import com.exallium.mvvmexampleapp.presentation.Router;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class NoteListViewModel {

    private final Router router;
    private final NoteListModel noteListModel;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public final ObservableList<NoteListItemViewModel> notes = new ObservableArrayList<>();

    public NoteListViewModel(Router router, NoteListModel noteListModel) {
        this.router = router;
        this.noteListModel = noteListModel;
    }

    public void addNote() {
        router.goToNoteAdd();
    }

    public void refresh() {
        compositeDisposable.clear();
        notes.clear();

        compositeDisposable.add(noteListModel.getAllNotes()
                .map(this::buildItemViewModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(notes::add)
                .subscribe());
    }

    public void cancel() {
        compositeDisposable.clear();
    }

    private NoteListItemViewModel buildItemViewModel(Note note) {
        return new NoteListItemViewModel(note);
    }
}
