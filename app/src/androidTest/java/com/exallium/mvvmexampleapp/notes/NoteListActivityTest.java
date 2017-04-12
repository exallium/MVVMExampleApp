package com.exallium.mvvmexampleapp.notes;

import android.content.ComponentName;
import android.support.test.espresso.intent.Intents;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.ViewGroup;

import com.exallium.mvvmexampleapp.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class NoteListActivityTest {

    @Rule
    public ActivityTestRule<NoteListActivity> activityRule = new ActivityTestRule<>(NoteListActivity.class);

    @Before
    public void setUp() throws Exception {
        Intents.init();
    }

    @After
    public void tearDown() throws Exception {
        Intents.release();
    }

    @Test
    public void onCreate_loadsDataFromBackend() throws Exception {
        // THEN
        onView(withId(R.id.activity_note_list_recycler)).check((view, noViewFoundException) ->
                assertThat(((ViewGroup) view).getChildCount(), is(10)));
    }

    @Test
    public void addButtonClick_opensNoteListActivity() throws Exception {
        // WHEN
        onView(withId(R.id.activity_note_list_add_note)).perform(click());

        // THEN
        intended(hasComponent(new ComponentName(getTargetContext(), NoteEditActivity.class)));
    }
}
