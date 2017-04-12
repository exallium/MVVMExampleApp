package com.exallium.mvvmexampleapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.exallium.mvpexampleapp.domain.notes.Note;
import com.exallium.mvvmexampleapp.notes.NoteEditActivity;
import com.exallium.mvvmexampleapp.presentation.Router;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class ActivityRouter implements Router {

    private final PublishSubject<RouteAction> routeActions = PublishSubject.create();
    private final Context context;

    public ActivityRouter(Context context) {
        this.context = context;
    }

    @Override
    public void goBack() {
        routeActions.onNext(new GoBackAction());
    }

    @Override
    public void goToNoteAdd() {
        goToNoteModify(Note.builder().build());
    }

    @Override
    public void goToNoteModify(Note note) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        BundleHelper.setNoteId(bundle, note.id());
        intent.putExtras(bundle);
        String clsName = NoteEditActivity.class.getName();
        intent.setClassName(context.getPackageName(), clsName);
        routeActions.onNext(new RouteAction(intent));
    }

    public Observable<RouteAction> getRouteActions() {
        return routeActions;
    }

    public static class RouteAction {
        private final Intent intent;

        public RouteAction(@Nullable Intent intent) {
            this.intent = intent;
        }

        public Intent getIntent() {
            return intent;
        }
    }

    public static class GoBackAction extends RouteAction {
        public GoBackAction() {
            super(null);
        }
    }
}
