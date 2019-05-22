package com.group04.dictionary04.matcher;

import com.group04.dictionary04.enums.LanguageIdentifier;
import com.group04.dictionary04.model.default_Vocabulary;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

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
}
