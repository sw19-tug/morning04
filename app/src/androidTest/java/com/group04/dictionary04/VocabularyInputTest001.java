package com.group04.dictionary04;

import android.support.test.rule.ActivityTestRule;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
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

        onView(withId(R.id.button_input)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_german_input)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_english_input)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_spanish_input)).check(matches(isDisplayed()));

        onView(withText("Successful")).inRoot(withDecorView(not(is(InputViewActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }
}

