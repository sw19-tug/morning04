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
public class TestActivityInstrumentedTest {

    @Rule
    public ActivityTestRule<TestViewActivity> TestViewActivityTestRule = new ActivityTestRule<>(TestViewActivity.class);

    @Test
    public void testButtons() {
        onView(withId(R.id.button0)).check(matches(isDisplayed()));
        onView(withId(R.id.button5)).check(matches(isDisplayed()));
        onView(withId(R.id.button4)).check(matches(isDisplayed()));
        onView(withId(R.id.button3)).check(matches(isDisplayed()));
    }

    @Test
    public void testButtonsClickable()
    {
        onView(withId(R.id.button0)).check(matches(isClickable()));
        onView(withId(R.id.button5)).check(matches(isClickable()));
        onView(withId(R.id.button4)).check(matches(isClickable()));
        onView(withId(R.id.button3)).check(matches(isClickable()));
    }



    @Test
    public void testTextFieldsVisible()
    {
        onView(withId(R.id.textView3)).check(matches(isDisplayed()));
        onView(withId(R.id.textView4)).check(matches(isDisplayed()));
        onView(withId(R.id.textView5)).check(matches(isDisplayed()));
        onView(withId(R.id.editText2)).check(matches(isDisplayed()));
        onView(withId(R.id.editText2)).check(matches(withText("")));
    }




    @Test
    public void performQuestion()
    {
        onView(withId(R.id.editText2)).perform(typeText("car"),closeSoftKeyboard());
        onView(withId(R.id.button0)).perform(click());

        //positive Button
        onView(withId(android.R.id.button1)).perform(click());
    }



    @Test
    public void performSaveExam()
    {

        onView(withId(R.id.button4)).perform(click());

        //check if allertDialog has popped up
        int titelId = TestViewActivityTestRule.getActivity().getResources().getIdentifier("alertTitle", "id", "android");
        onView(withId(titelId)).inRoot(isDialog()).check(matches(withText("Done..."))).check(matches(isDisplayed()));
        onView(withText("Do you want to save your exam progress?")).check(matches(isDisplayed()));

        //negative(NO) btn
        onView(withId(android.R.id.button2)).perform(click());

    }
}
