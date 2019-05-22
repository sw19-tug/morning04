package com.group04.dictionary04;

import android.support.test.rule.ActivityTestRule;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static org.hamcrest.Matchers.arrayContaining;
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
public class VocabularyInputTest {

    @Rule
    public ActivityTestRule<InputViewActivity> InputViewActivityTestRule = new ActivityTestRule<>(InputViewActivity.class);

    @Test
    public void testButtonsVisible() {

        // test buttons are visible

        String german_spinner_text = "German";
        String english_spinner_text = "English";
        String spanish_spinner_text = "Spanish";
        String french_spinner_text = "French";
        String italy_spinner_text = "Italy";

        // submit button
        onView(withId(R.id.button_input)).check(matches(isDisplayed()));

        // first input text field
        onView(withId(R.id.txt_lang1_input)).check(matches(isDisplayed()));

        // second input text field
        onView(withId(R.id.txt_lang2_input)).check(matches(isDisplayed()));

        // dropdown spinner 1
        onView(withId(R.id.spinner1_input)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(german_spinner_text))).perform(click());
        onView(withId(R.id.spinner1_input)).check(matches(withSpinnerText(containsString(german_spinner_text)))); // default value

        // dropdown spinner 2
        onView(withId(R.id.spinner2_input)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(german_spinner_text))).perform(click()); // clickable
        onView(withId(R.id.spinner1_input)).check(matches(withSpinnerText(containsString(german_spinner_text)))); // default value

        // dropdown spinner 1_change_language
        onView(withId(R.id.spinner1_input)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(spanish_spinner_text))).perform(click()); // clickable
        onView(withId(R.id.spinner2_input)).check(matches(withSpinnerText(containsString(german_spinner_text))));

        // dropdown spinner 2_change_language
        onView(withId(R.id.spinner2_input)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(french_spinner_text))).perform(click()); // clickable
        onView(withId(R.id.spinner1_input)).check(matches(withSpinnerText(containsString(spanish_spinner_text))));
//        String sucessful_text = "Successful";
//        onView(withText(sucessful_text)).inRoot(withDecorView(not(is(InputViewActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void testInputPair()
    {
//        onData(allOf(is(instanceOf(String.class)), is("English"))).perform(click());

        onView(withId(R.id.txt_lang1_input)).perform(typeText("German Word"));
        onView(withId(R.id.txt_lang2_input)).perform(typeText("English Word"));
        onView(withId(R.id.ratingBar_difficulty)).perform(click());
        onView(withId(R.id.txt_tag_input)).perform(typeText("holiday"),closeSoftKeyboard());

        onView(withId(R.id.button_input)).perform(click());

    }

}

