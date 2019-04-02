package com.group04.dictionary04;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
public class MainActivityRatingTest
{
    @Rule
    public ActivityTestRule<RatingViewActivity> RatingViewActivityTestRule = new ActivityTestRule<>(RatingViewActivity.class);


    @Test
    public void testButtonsVisible() {
        onView(withId(R.id.btn_ascending)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_descending)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_filter)).check(matches(isDisplayed()));
    }

    @Test
    public void textFieldsVisible(){
        onView(withId(R.id.tv_sort)).check(matches(withText("Rate:")));
        onView(withId(R.id.tv_sort)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_rate)).check(matches(withText("Sort:")));
        onView(withId(R.id.tv_rate)).check(matches(isDisplayed()));
    }
}
