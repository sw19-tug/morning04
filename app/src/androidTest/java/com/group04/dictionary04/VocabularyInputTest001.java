package com.group04.dictionary04;

import android.support.test.rule.ActivityTestRule;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.assertEquals;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static org.hamcrest.Matchers.is;


@RunWith(AndroidJUnit4.class)
public class VocabularyInputTest001 {

    @Rule
    public ActivityTestRule<InputViewActivity> InputViewActivityTestRule = new ActivityTestRule<>(InputViewActivity.class);

    @Test
    public void testButtonsVisible() {

        String german_spinner_text = "German";
        String english_spinner_text = "English";
        String spanish_spinner_text = "Spanish";

        onView(withId(R.id.button_input)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_lang1_input)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_lang2_input)).check(matches(isDisplayed()));
        onView(withId(spinner1_input)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(german_spinner_text))).perform(click());
        onView(withId(spinner1_input)).check(matches(withSpinnerText(containsString(german_spinner_text))));
        onView(withId(spinner1_input)).check(matches(withSpinnerText(containsString(english_spinner_text))));
        onView(withId(spinner1_input)).check(matches(withSpinnerText(containsString(spanish_spinner_text))));

        onView(withId(spinner2_input)).perform(click());
        onView(withId(spinner2_input)).check(matches(withSpinnerText(containsString(german_spinner_text))));
        onView(withId(spinner2_input)).check(matches(withSpinnerText(containsString(english_spinner_text))));
        onView(withId(spinner2_input)).check(matches(withSpinnerText(containsString(spanish_spinner_text))));

        String sucessful_text = "Successful";
        onView(withText(sucessful_text)).inRoot(withDecorView(not(is(InputViewActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }
}

