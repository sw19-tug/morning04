package com.group04.dictionary04;

import android.support.test.rule.ActivityTestRule;
import android.util.Log;

import com.group04.dictionary04.database.DatabaseController;
import com.group04.dictionary04.model.default_Dictionary;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.containsString;

public class SavedExamsTest {

    private default_Dictionary dict = null;

    @Rule
    public ActivityTestRule<SavedExamsViewActivity> SavedExamsViewTestRule = new ActivityTestRule<>(SavedExamsViewActivity.class);


    @Test
    public void testList() {
        onView(withId(R.id.vocList)).check(matches(isDisplayed()));
        onView(withId(R.id.textView_top)).check(matches(isDisplayed()));
        onView(withId(R.id.vocList)).check(matches(isClickable()));
    }

    @Test
    public void testAllertDialog() {
        onView(withId(R.id.vocList)).perform(click());

        //check if allertDialog has popped up
        int titelId = SavedExamsViewTestRule.getActivity().getResources().getIdentifier("alertTitle", "id", "android");
        onView(withId(titelId)).inRoot(isDialog()).check(matches(withText("Saved Exams"))).check(matches(isDisplayed()));
    }


    @Test
    public void deleteTest() {

        onView(withId(R.id.vocList)).perform(click());
        onView(withId(android.R.id.button2)).perform(click());


    }
}
