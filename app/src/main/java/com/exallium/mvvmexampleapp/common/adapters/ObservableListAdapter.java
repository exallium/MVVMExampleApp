package com.exallium.mvvmexampleapp.common.adapters;

import android.databinding.ObservableList;
import android.support.annotation.CallSuper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class ObservableListAdapter<VM> extends RecyclerView.Adapter<ObservableListAdapter.ViewHolder<VM>> {

    private final ObservableList<VM> observableViewModelList;

    private final ObservableList.OnListChangedCallback<ObservableList<VM>> observableListOnListChangedCallback
            = new ObservableList.OnListChangedCallback<ObservableList<VM>>() {
        @Override
        public void onChanged(ObservableList<VM> vms) {
            notifyDataSetChanged();
        }

        @Override
        public void onItemRangeChanged(ObservableList<VM> vms, int i, int i1) {
            notifyItemRangeChanged(i, i1);
        }

        @Override
        public void onItemRangeInserted(ObservableList<VM> vms, int i, int i1) {
            notifyItemRangeInserted(i, i1);
        }

        @Override
        public void onItemRangeMoved(ObservableList<VM> vms, int i, int i1, int i2) {
            notifyItemRangeRemoved(i, i2);
            notifyItemRangeInserted(i1, i2);
        }

        @Override
        public void onItemRangeRemoved(ObservableList<VM> vms, int i, int i1) {
            notifyItemRangeRemoved(i, i1);
        }
    };

    public ObservableListAdapter(ObservableList<VM> observableViewModelList) {
        this.observableViewModelList = observableViewModelList;
        observableViewModelList.addOnListChangedCallback(observableListOnListChangedCallback);
    }

    @CallSuper
    public void cleanup() {
        observableViewModelList.removeOnListChangedCallback(observableListOnListChangedCallback);
    }

    @Override
    public void onBindViewHolder(ViewHolder<VM> holder, int position) {
        holder.onBind(observableViewModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return observableViewModelList.size();
    }

    public static abstract class ViewHolder<VM> extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }

        public abstract void onBind(VM vm);
    }
}
