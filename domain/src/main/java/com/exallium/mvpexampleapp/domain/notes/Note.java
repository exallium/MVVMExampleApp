package com.exallium.mvpexampleapp.domain.notes;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Note {

    public static Note.Builder builder() {
        return new AutoValue_Note.Builder().setId(0).setTitle("").setBody("").setCreated(0).setUpdated(0);
    }

    public Note.Builder buildUpon() {
        return builder().setId(id()).setTitle(title()).setBody(body()).setCreated(created()).setUpdated(updated());
    }

    public abstract long id();
    public abstract String title();
    public abstract String body();
    public abstract long created();
    public abstract long updated();

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setId(long id);
        public abstract Builder setTitle(String title);
        public abstract Builder setBody(String body);
        public abstract Builder setCreated(long createdMillis);
        public abstract Builder setUpdated(long updatedMillis);
        public abstract Note build();
    }
}
