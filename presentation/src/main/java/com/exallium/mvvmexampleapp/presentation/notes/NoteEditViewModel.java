package com.exallium.mvvmexampleapp.presentation.notes;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.exallium.mvpexampleapp.domain.notes.NoteEditModel;
import com.exallium.mvvmexampleapp.presentation.Router;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class NoteEditViewModel {

    private final long noteId;
    private final Router router;
    private final NoteEditModel noteEditModel;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public final ObservableField<String> title = new ObservableField<>("");
    public final ObservableField<String> titleError = new ObservableField<>(null);
    public final ObservableBoolean titleErrorEnabled = new ObservableBoolean(false);
    public final ObservableField<String> body = new ObservableField<>("");

    public NoteEditViewModel(long noteId, Router router, NoteEditModel noteEditModel) {
        this.noteId = noteId;
        this.router = router;
        this.noteEditModel = noteEditModel;
    }

    public void refresh() {
        compositeDisposable.clear();
        noteEditModel.getNoteById(noteId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(note -> {
                    title.set(note.title());
                    body.set(note.body());
                });
    }

    public void save() {
        noteEditModel.save(noteId, title.get(), body.get()).subscribe(note -> {
            router.goBack();
        }, throwable -> {
            titleError.set(throwable.getMessage());
            titleErrorEnabled.set(true);
        });
    }

    public void cancel() {
        compositeDisposable.clear();
    }
}
