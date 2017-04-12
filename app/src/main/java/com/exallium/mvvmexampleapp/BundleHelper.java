package com.exallium.mvvmexampleapp;

import android.os.Bundle;

public class BundleHelper {

    private static final String NOTE_ID = "bundleHelper.NOTE_ID";

    public static void setNoteId(Bundle bundle, long id) {
        bundle.putLong(NOTE_ID, id);
    }

    public static long getNoteId(Bundle bundle) {
        return bundle.getLong(NOTE_ID, -1);
    }

}
