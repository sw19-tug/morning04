package com.group04.dictionary04;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.group04.dictionary04.R;
import com.group04.dictionary04.TestViewActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
public class AdvancedTestInstrumentedTest {

    @Rule
    public ActivityTestRule<AdvancedTestViewActivity> AdvancedTestViewActivityTestRule = new ActivityTestRule<>(AdvancedTestViewActivity.class);

    @Test
    public void testButtons() {
        onView(withId(R.id.btn_add)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_start)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_filter2)).check(matches(isDisplayed()));
    }

    @Test
    public void testButtonsClickable()
    {
        onView(withId(R.id.btn_add)).check(matches(isClickable()));
        onView(withId(R.id.btn_start)).check(matches(isClickable()));
        onView(withId(R.id.btn_filter2)).check(matches(isClickable()));
    }


    @Test
    public void testSpinners()
    {
        onView(withId(R.id.spinner_from)).check(matches(isDisplayed()));
        onView(withId(R.id.spinner_to)).check(matches(isDisplayed()));
        onView(withId(R.id.ratingBar_rate)).check(matches(isDisplayed()));
    }


    @Test
    public void testSpinnersClickable()
    {
        onView(withId(R.id.spinner_from)).check(matches(isClickable()));
        onView(withId(R.id.spinner_to)).check(matches(isClickable()));
        onView(withId(R.id.ratingBar_rate)).check(matches(isClickable()));
    }



    @Test
    public void testTextFieldsVisible()
    {
        onView(withId(R.id.search)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_to)).check(matches(isDisplayed()));
        onView(withId(R.id.et_tag)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_tag)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_rating)).check(matches(isDisplayed()));
        onView(withId(R.id.search)).check(matches(withText("")));
        onView(withId(R.id.et_tag)).check(matches(withText("")));
    }




    @Test
    public void performSearch()
    {
        onView(withId(R.id.et_tag)).perform(typeText("test"),closeSoftKeyboard());
        onView(withId(R.id.btn_filter2)).perform(click());

        //positive Button
        onView(withId(android.R.id.button1)).perform(click());
    }



    @Test
    public void performSaveExam()
    {

        onView(withId(R.id.button4)).perform(click());

        //check if allertDialog has popped up
        int titelId = AdvancedTestViewActivityTestRule.getActivity().getResources().getIdentifier("alertTitle", "id", "android");
        onView(withId(titelId)).inRoot(isDialog()).check(matches(withText("Done..."))).check(matches(isDisplayed()));
        onView(withText("Do you want to save your exam progress?")).check(matches(isDisplayed()));

        //negative(NO) btn
        onView(withId(android.R.id.button2)).perform(click());

    }
}