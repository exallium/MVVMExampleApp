package com.exallium.mvvmexampleapp.notes;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.jakewharton.rxbinding2.view.RxView;
import com.exallium.mvvmexampleapp.ActivityRouter;
import com.exallium.mvvmexampleapp.MVVMApp;
import com.exallium.mvvmexampleapp.R;
import com.exallium.mvvmexampleapp.databinding.ActivityNoteListBinding;
import com.exallium.mvvmexampleapp.notes.di.DaggerNoteListActivityComponent;
import com.exallium.mvvmexampleapp.presentation.notes.NoteListViewModel;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class NoteListActivity extends AppCompatActivity {

    @Inject
    NoteListViewModel noteListViewModel;

    @Inject
    ActivityRouter router;

    private final CompositeDisposable disposable = new CompositeDisposable();

    private ActivityNoteListBinding binding;
    private NoteListAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_note_list);

        DaggerNoteListActivityComponent.builder()
                .appComponent(MVVMApp.getAppComponent())
                .build()
                .inject(this);

        binding.activityNoteListRecycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NoteListAdapter(noteListViewModel.notes);
        binding.activityNoteListRecycler.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        disposable.add(router.getRouteActions().subscribe(routeAction -> {
            if (routeAction instanceof ActivityRouter.GoBackAction) {
                onBackPressed();
            } else {
                startActivity(routeAction.getIntent());
            }
        }));
        disposable.add(RxView.clicks(binding.activityNoteListAddNote).subscribe((Object) ->
                noteListViewModel.addNote()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        noteListViewModel.refresh();
    }

    @Override
    protected void onPause() {
        noteListViewModel.cancel();
        super.onPause();
    }

    @Override
    protected void onStop() {
        disposable.clear();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        adapter.cleanup();
        super.onDestroy();
    }
}
