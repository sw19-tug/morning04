package com.group04.dictionary04;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.group04.dictionary04.R;
import com.group04.dictionary04.TestViewActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
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
        onView(withId(R.id.button)).check(matches(isDisplayed()));
        onView(withId(R.id.button)).check(matches(isClickable()));
        //TODO: test for all button visibility in xml file, they are displayed upon advancing
        //onView(withId(R.id.button3)).check(matches(isDisplayed()));
        //onView(withId(R.id.button4)).check(matches(isDisplayed()));
        //onView(withId(R.id.button5)).check(matches(isDisplayed()));
    }

    @Test
    public void testTextFields(){
        onView(withId(R.id.textView3)).check(matches(isDisplayed()));
        onView(withId(R.id.textView3)).check(matches(withText("Language 1")));
        onView(withId(R.id.textView4)).check(matches(isDisplayed()));
        onView(withId(R.id.textView4)).check(matches(withText("VOCAB")));
        onView(withId(R.id.textView5)).check(matches(isDisplayed()));
        onView(withId(R.id.textView5)).check(matches(withText("Second Language")));
    }

    @Test
    public void testEditText(){
        onView(withId(R.id.editText2)).check(matches(isDisplayed()));
        onView(withId(R.id.editText2)).check(matches(withHint("your answer...")));
        onView(withId(R.id.editText2)).check(matches(isClickable()));
    }
}
