package com.group04.dictionary04;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.RatingBar;

import com.group04.dictionary04.enums.DifficultyIdentifier;
import com.group04.dictionary04.enums.LanguageIdentifier;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.group04.dictionary04.matcher.VocabularyMatcher.withLanguage;
import static com.group04.dictionary04.matcher.VocabularyMatcher.withRating;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;


@RunWith(AndroidJUnit4.class)
public class MainActivityRatingTest
{
    @Rule
    public ActivityTestRule<RatingViewActivity> RatingViewActivityTestRule = new ActivityTestRule<>(RatingViewActivity.class);


    @Test
    public void testButtons() {
        onView(withId(R.id.btn_ascending)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_ascending)).check(matches(isNotChecked()));
        onView(withId(R.id.btn_descending)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_descending)).check(matches(isNotChecked()));
        onView(withId(R.id.btn_filter)).check(matches(isDisplayed()));
    }

    @Test
    public void testButtonsClickable() {
        onView(withId(R.id.btn_descending)).check(matches(isClickable()));
        onView(withId(R.id.btn_ascending)).check(matches(isClickable()));
        onView(withId(R.id.btn_filter)).check(matches(isClickable()));
    }

    @Test
    public void testRatingBar() {
        onView(withId(R.id.ratingBar_rating)).check(matches(isDisplayed()));
    }

    @Test
    public void testSpinner() {
        String german_spinner_text = "German";
        onView(withId(R.id.spinner_langt)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(german_spinner_text))).perform(click());
        onView(withId(R.id.spinner_langt)).check(matches(withSpinnerText(containsString(german_spinner_text))));
    }

    @Test
    public void textFieldsVisible(){
        onView(withId(R.id.tv_lang)).check(matches(withText("Choose Language:")));
        onView(withId(R.id.tv_lang)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_sort)).check(matches(withText("Sort:")));
        onView(withId(R.id.tv_sort)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_rate)).check(matches(withText("Rate:")));
        onView(withId(R.id.tv_rate)).check(matches(isDisplayed()));
    }

    @Test
    public void testList() {
        onView(withId(R.id.list_items)).check(matches(isDisplayed()));
        onView(withId(R.id.list_items)).check(matches(isClickable()));
    }

    @Test
    public void testChangeRating() {
        onView(withId(R.id.spinner_langt)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("German"))).perform(click());
        onView(withId(R.id.spinner_langt)).check(matches(withSpinnerText(containsString("German"))));
        onView(withId(R.id.btn_ascending)).perform(click());

        onView(withId(R.id.ratingBar_rating)).perform(new SetRating());
        onView(withId(R.id.btn_filter)).perform(ViewActions.click());

        // check Rating of all Entries in the List
        onData(withRating(DifficultyIdentifier.BEGINNER)) // set by SetRating function "perform" method
                .inAdapterView(withId(R.id.list_items))
                .atPosition(0)
                .check(matches(isDisplayed()));
    }


    // for testChangeRating Test
    public final class SetRating implements ViewAction {

        @Override
        public Matcher<View> getConstraints() {
            Matcher <View> isRatingBarConstraint = ViewMatchers.isAssignableFrom(RatingBar.class);
            return isRatingBarConstraint;
        }

        @Override
        public String getDescription() {
            return "Custom view action to set rating.";
        }

        @Override
        public void perform(UiController uiController, View view) {
            RatingBar ratingBar = (RatingBar) view;
            ratingBar.setRating(1);
        }
    }


}
