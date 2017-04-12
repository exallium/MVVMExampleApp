package com.exallium.mvvmexampleapp.notes;

import android.content.Intent;
import android.os.Bundle;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.exallium.mvvmexampleapp.BundleHelper;
import com.exallium.mvvmexampleapp.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class NoteEditActivityTest {

    @Rule
    public ActivityTestRule<NoteEditActivity> activityRule = new NoteEditActivityTestRule();

    @Test
    public void onSave_whenTitleIsEmpty_viewDisplaysError() throws Throwable {
        // GIVEN
        onView(withId(R.id.activity_note_edit_title)).perform(typeText(""));
        onView(withId(R.id.activity_note_edit_body)).perform(typeText("body"));

        // WHEN
        openActionBarOverflowOrOptionsMenu(getTargetContext());
        onView(withText(R.string.save)).perform(click());

        // THEN
        onView(withText(R.string.note_empty_title_exception)).check(matches(isDisplayed()));
    }

    private static final class NoteEditActivityTestRule extends ActivityTestRule<NoteEditActivity> {

        public NoteEditActivityTestRule() {
            super(NoteEditActivity.class);
        }

        @Override
        protected Intent getActivityIntent() {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            BundleHelper.setNoteId(bundle, 1);
            intent.putExtras(bundle);
            return intent;
        }
    }
}
