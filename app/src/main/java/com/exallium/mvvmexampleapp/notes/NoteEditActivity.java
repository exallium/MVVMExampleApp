package com.exallium.mvvmexampleapp.notes;

import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.exallium.mvvmexampleapp.ActivityRouter;
import com.exallium.mvvmexampleapp.BundleHelper;
import com.exallium.mvvmexampleapp.MVVMApp;
import com.exallium.mvvmexampleapp.R;
import com.exallium.mvvmexampleapp.databinding.ActivityNoteEditBinding;
import com.exallium.mvvmexampleapp.notes.di.DaggerNoteEditActivityComponent;
import com.exallium.mvvmexampleapp.notes.di.NoteEditActivityModule;
import com.exallium.mvvmexampleapp.presentation.notes.NoteEditViewModel;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class NoteEditActivity extends AppCompatActivity {

    @Inject
    NoteEditViewModel noteEditViewModel;

    @Inject
    ActivityRouter router;

    private ActivityNoteEditBinding binding;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final Observable.OnPropertyChangedCallback onTitleErrorChanged = new Observable.OnPropertyChangedCallback() {
        @Override
        public void onPropertyChanged(Observable observable, int i) {
            binding.activityNoteEditTitleInput.setError(noteEditViewModel.titleError.get());
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_note_edit);

        long noteId = BundleHelper.getNoteId(getIntent().getExtras());
        DaggerNoteEditActivityComponent.builder()
                .appComponent(MVVMApp.getAppComponent())
                .noteEditActivityModule(new NoteEditActivityModule(noteId))
                .build()
                .inject(this);

        binding.setViewModel(noteEditViewModel);
    }

    @Override
    protected void onStart() {
        super.onStart();

        noteEditViewModel.titleError.addOnPropertyChangedCallback(onTitleErrorChanged);

        compositeDisposable.add(RxTextView.textChanges(binding.activityNoteEditTitle).skipInitialValue().subscribe(charSequence ->
                noteEditViewModel.title.set(charSequence.toString())));

        compositeDisposable.add(RxTextView.textChanges(binding.activityNoteEditBody).skipInitialValue().subscribe(charSequence ->
                noteEditViewModel.body.set(charSequence.toString())));

        compositeDisposable.add(router.getRouteActions().subscribe(routeAction -> {
            if (routeAction instanceof ActivityRouter.GoBackAction) {
                onBackPressed();
            } else {
                startActivity(routeAction.getIntent());
            }
        }));
    }

    @Override
    protected void onResume() {
        super.onResume();
        noteEditViewModel.refresh();
    }

    @Override
    protected void onPause() {
        noteEditViewModel.cancel();
        super.onPause();
    }

    @Override
    protected void onStop() {
        noteEditViewModel.titleError.removeOnPropertyChangedCallback(onTitleErrorChanged);
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.menu_note_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_save) {
            noteEditViewModel.save();
        }
        return super.onOptionsItemSelected(item);
    }
}
