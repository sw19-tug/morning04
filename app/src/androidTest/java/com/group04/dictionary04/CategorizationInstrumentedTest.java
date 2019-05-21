package com.group04.dictionary04;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.group04.dictionary04.R;
import com.group04.dictionary04.SearchViewActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;


@RunWith(AndroidJUnit4.class)
public class CategorizationInstrumentedTest
{
    @Rule
    public ActivityTestRule<SearchViewActivity> SearchViewActivityTestRule = new ActivityTestRule<>(SearchViewActivity.class);


    @Test
    public void testVisibility() {
        onView(withId(R.id.txt_lang1_input)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_sort)).check(matches(isDisplayed()));
        onView(withId(R.id.radiogroup)).check(matches(isDisplayed()));
        onView(withId(R.id.spinner_sort)).check(matches(isDisplayed()));
        onView(withId(R.id.search)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_filter)).check(matches(isDisplayed()));
        onView(withId(R.id.radiogroup2)).check(matches(isDisplayed()));
        onView(withId(R.id.spinner_lang)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_filter)).check(matches(isDisplayed()));
        onView(withId(R.id.rb_ascending)).check(matches(isDisplayed()));
        onView(withId(R.id.rb_descending)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_tag)).check(matches(isDisplayed()));
    }

    @Test
    public void testButtonsSpinnersClickable() {
        onView(withId(R.id.btn_filter)).check(matches(isClickable()));
        onView(withId(R.id.spinner_sort)).check(matches(isClickable()));
        onView(withId(R.id.spinner_lang)).check(matches(isClickable()));
    }

    //TODO these tests currently fail, not sure if we need them
    @Test
    public void testList() {
        onView(withId(R.id.list_items)).check(matches(isDisplayed()));
        onView(withId(R.id.list_items)).check(matches(isClickable()));
    }
}