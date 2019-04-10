package com.group04.dictionary04.LearningView;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.group04.dictionary04.R;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class LearningViewInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.group04.dictionary04", appContext.getPackageName());
    }

    @Test
    public void spinnerLanguageSelectionDisplayed()
    {
        onView(withId(R.id.languageSelection)).check(matches(isDisplayed()));
    }

    @Test
    public void spinnerVocabualryListDisplayed()
    {
        onView(withId(R.id.vocList)).check(matches(isDisplayed()));
    }



}