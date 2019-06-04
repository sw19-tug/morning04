package com.group04.dictionary04;


import com.group04.dictionary04.enums.LanguageIdentifier;
import com.group04.dictionary04.interfaces.Exam;
import com.group04.dictionary04.model.*;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.group04.dictionary04.enums.DifficultyIdentifier.BEGINNER;

public class AdvancedTestUnitTest {
    default_Dictionary dictionary;
    static int total_entries = 15;


    @Before
    public void setUp() {
        dictionary = new default_Dictionary();
        for (int i = 0; i < total_entries; i++) {
            dictionary.addTranslation("first_de" + i, "second_en" + i, "" + (i % 4), LanguageIdentifier.DE, LanguageIdentifier.EN);
        }

        List<default_Entry> all_added_entries = dictionary.getEntries();
    }

}