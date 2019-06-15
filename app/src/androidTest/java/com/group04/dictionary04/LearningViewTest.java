package com.group04.dictionary04;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.group04.dictionary04.enums.LanguageIdentifier;
import com.group04.dictionary04.model.default_Vocabulary;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static com.group04.dictionary04.matcher.VocabularyMatcher.withLanguage;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.AllOf.allOf;


@RunWith(AndroidJUnit4.class)
public class LearningViewTest
{
    @Rule
    public ActivityTestRule<LearningViewActivity> LearningViewActivityTestRule = new ActivityTestRule<>(LearningViewActivity.class);


    @Test
    public void testButtons() {
        onView(withId(R.id.languageSelection)).check(matches(isDisplayed()));
        onView(withId(R.id.languageSelection)).check(matches(isClickable()));
        onView(withId(R.id.vocList)).check(matches(isDisplayed()));
        onView(withId(R.id.vocList)).check(matches(isClickable()));
    }

    @Test
    public void testButtonsClickable() {
        onView(withId(R.id.languageSelection)).check(matches(isClickable()));
    }

    @Test
    public void testSpinner() {
        String german_spinner_text = "German";
        onView(withId(R.id.languageSelection)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(german_spinner_text))).perform(click());
        onView(withId(R.id.languageSelection)).check(matches(withSpinnerText(containsString(german_spinner_text))));
        onData(withLanguage(LanguageIdentifier.DE)).inAdapterView(withId(R.id.vocList)).atPosition(0).check(matches(isDisplayed()));
    }
}
