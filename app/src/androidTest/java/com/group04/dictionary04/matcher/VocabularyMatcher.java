package com.group04.dictionary04.matcher;

import android.util.Log;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import com.group04.dictionary04.enums.DifficultyIdentifier;
import com.group04.dictionary04.enums.LanguageIdentifier;
import com.group04.dictionary04.model.default_Entry;
import com.group04.dictionary04.model.default_Vocabulary;

public class VocabularyMatcher {

    public static Matcher withLanguage(final LanguageIdentifier li){
        return new TypeSafeMatcher<default_Vocabulary>(){

            @Override
            public void describeTo(Description description) {

            }

            @Override
            protected boolean matchesSafely(default_Vocabulary item) {
                return li.toString().matches(item.getLanguage().toString());
            }
        };
    }


    public static Matcher withRating(final DifficultyIdentifier difficulty){
        return new TypeSafeMatcher<default_Entry>(){

            @Override
            public void describeTo(Description description) {

            }

            @Override
            protected boolean matchesSafely(default_Entry item) {
                Log.d("aaa", "Value " + item.getId1().getValue() + "  RATING " + item.getRating());
                return difficulty.toString().matches(item.getRating());
            }
        };
    }


}
