package com.group04.dictionary04.Dictionary;


import com.group04.dictionary04.enums.LanguageIdentifier;
import com.group04.dictionary04.interfaces.Exam;
import com.group04.dictionary04.model.*;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class DictionaryUnitTest{
    default_Dictionary dictionary;
    static int total_entries = 15;


    @Before
    public void setUp() {
        dictionary = new default_Dictionary();
        for (int i = 0; i < total_entries; i++){
            dictionary.addTranslation("first_de" + i, "second_en" + i, "" + (i%4), LanguageIdentifier.DE, LanguageIdentifier.EN);
        }
    }

    @Test
    public void getEntryTest(){
        List<default_Entry> all_added_entries = dictionary.getEntries();
        assert all_added_entries.size() == total_entries : "Inserting Elements at setup failed!\n";
        for (default_Entry it : all_added_entries)
        {
            assert it.getId1().getId().contains("DE") : "Languageidentifier does not match ID\n";
            assert it.getId2().getId().contains("EN") : "Languageidentifier does not match ID\n";
        }
    }

    @Test
    public void getLanguageByIndex(){
        List<default_Entry> all_added_entries = dictionary.getEntries();
        assert all_added_entries.size() == total_entries : "Inserting Elements at setup failed!\n";
        for (int i = 0; i < all_added_entries.size(); i++)
        {
            assert dictionary.getEntries().get(i).getId1().getValue().equals("first_de" + i) : "Wrong translation retrieved";
            assert dictionary.getEntries().get(i).getId2().getValue().equals("second_en" + i) : "Wrong translation retrieved";
        }
    }

    @Test
    public void getLanguageByName(){
        assert dictionary.getLanguageByName("German").getLangName().equals(LanguageIdentifier.DE) : "Wrong language retrieved";
        assert dictionary.getLanguageByName("English").getLangName().equals(LanguageIdentifier.EN) : "Wrong language retrieved";
        assert dictionary.getLanguageByName("French").getLangName().equals(LanguageIdentifier.FR) : "Wrong language retrieved";
        assert dictionary.getLanguageByName("Italy").getLangName().equals(LanguageIdentifier.IT) : "Wrong language retrieved";
        assert dictionary.getLanguageByName("Spanish").getLangName().equals(LanguageIdentifier.SP) : "Wrong language retrieved";
    }

    @Test
    public void getTranslationString() {
        for (int i = 0; i < total_entries; i++){
            String test = dictionary.getTranslationString(dictionary.getEntries().get(i).getId1());
            assert test.equals(dictionary.getEntries().get(i).getId2().getLanguage()
                    + ": " + dictionary.getEntries().get(i).getId2() + "\n") : "Wront translation string retrieved";
        }
    }

    @Test
    public void addDifficultyTest() {

    }

    @Test
    public void addTagTest() {

    }

    @Test
    public void updateEntryTest() {

    }
}
