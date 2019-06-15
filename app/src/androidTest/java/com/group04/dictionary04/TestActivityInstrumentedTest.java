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
        onView(withId(R.id.button1)).check(matches(isDisplayed()));
        onView(withId(R.id.button5)).check(matches(isDisplayed()));
        onView(withId(R.id.button4)).check(matches(isDisplayed()));
        onView(withId(R.id.button3)).check(matches(isDisplayed()));
    }

    @Test
    public void testButtonsClickable()
    {
        onView(withId(R.id.button0)).check(matches(isClickable()));
        onView(withId(R.id.button1)).check(matches(isClickable()));
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

        onView(withId(android.R.id.button1)).perform(click());
    }



    @Test
    public void performSaveExam()
    {

        onView(withId(R.id.button4)).perform(click());

        int titleId = TestViewActivityTestRule.getActivity().getResources().getIdentifier("alertTitle", "id", "android");
        onView(withId(titleId)).inRoot(isDialog()).check(matches(withText("Done..."))).check(matches(isDisplayed()));
        onView(withText("Do you want to save your exam progress?")).check(matches(isDisplayed()));

        onView(withId(android.R.id.button2)).perform(click());

    }



    @Test
    public void performHintBasic()
    {
        onView(withId(R.id.button1)).check(matches(isDisplayed()));
        onView(withId(R.id.button1)).perform(click());
        onView(withText("Hint is the first letter(s) of the answer:")).check(matches(isDisplayed()));
        onView(withId(android.R.id.button2)).perform(click());
    }

    @Test
    public void performHintAdvanced() {
        onView(withId(R.id.button1)).check(matches(isDisplayed()));
        onView(withId(R.id.button1)).perform(click());
        String y = TestViewActivityTestRule.getActivity().exam.getVocsToTest().get(TestViewActivityTestRule.getActivity().index).getId2().getValue().substring(0, 1);
        onView(withText(y)).check(matches(isDisplayed()));
    }

    @Test
    public void performHintMax(){
       int x = TestViewActivityTestRule.getActivity().exam.getVocsToTest().get(TestViewActivityTestRule.getActivity().index).getId2().getValue().length();
        onView(withId(R.id.button1)).check(matches(isDisplayed()));
        onView(withId(R.id.button1)).perform(click());

       for(int i = 1; i < x ; i++)
       {
           onView(withText("MORE HELP")).perform(click());
           if(i == x-1)

           {
             String y = TestViewActivityTestRule.getActivity().exam.getVocsToTest().get(TestViewActivityTestRule.getActivity().index).getId2().getValue().substring(0, i);
             String z = "Sorry, you have used all your hints, final hint:\n " + y;
             onView(withText(z)).check(matches(isDisplayed()));
           }

       }
    }



}
