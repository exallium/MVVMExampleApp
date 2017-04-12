package com.exallium.mvvmexampleapp.notes;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableList;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.exallium.mvvmexampleapp.R;
import com.exallium.mvvmexampleapp.common.adapters.ObservableListAdapter;
import com.exallium.mvvmexampleapp.databinding.NoteListItemBinding;
import com.exallium.mvvmexampleapp.presentation.notes.NoteListItemViewModel;

public class NoteListAdapter extends ObservableListAdapter<NoteListItemViewModel> {

    public NoteListAdapter(ObservableList<NoteListItemViewModel> observableViewModelList) {
        super(observableViewModelList);
    }

    @Override
    public ViewHolder<NoteListItemViewModel> onCreateViewHolder(ViewGroup parent, int viewType) {
        NoteListItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.note_list_item, parent, false);
        return new NoteListItemViewHolder(binding);
    }

    private static class NoteListItemViewHolder extends ViewHolder<NoteListItemViewModel> {

        private final NoteListItemBinding noteBinding;

        NoteListItemViewHolder(NoteListItemBinding noteBinding) {
            super(noteBinding.getRoot());
            this.noteBinding = noteBinding;
        }

        @Override
        public void onBind(NoteListItemViewModel noteListItemViewModel) {
            noteBinding.setViewModel(noteListItemViewModel);
        }
    }
}
