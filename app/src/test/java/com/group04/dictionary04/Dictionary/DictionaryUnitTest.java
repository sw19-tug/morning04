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
        for (int i = 1; i <= total_entries; i++){
            dictionary.addTranslation("first_de" + i, "second_en" +1, LanguageIdentifier.DE, LanguageIdentifier.EN);
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

    /*@Test
    public void getTranslationTest() {
        default_Entry entry = new default_Entry();
        /*default_Vocabulary voc1 = new default_Vocabulary();
        default_Vocabulary voc2 = new default_Vocabulary();
        voc1.setId("EN-1");
        voc2.setId("DE-1");
        entry.setId1(voc1);
        entry.setId2(voc2);

        dictionary.addTranslation("test-voc1", "test-voc1-otherlanguage");

        default_Entry returnEntry = dictionary.getTranslation(entry);
        assert ( returnEntry.getId1() != null && returnEntry.getId2() != null ) : "Pair has empty Voc-entries!\n";
    }*/

    /*@Test
    public void generateExamTest() {
        Exam random_exam = dictionary.generateExam(null);

        default_Filter filter = new default_Filter();
        filter.setLimit_pairs(10);
        Exam random_exam10 = dictionary.generateExam(null);

        filter.setLangID2(LanguageIdentifier.EN);
        filter.setLangID1(LanguageIdentifier.DE);
        Exam en_de_exam = dictionary.generateExam(filter);

        assert !random_exam.getVocsToTest().isEmpty() : "Empty Exam with Filter=null\n";
        assert !(random_exam10.getVocsToTest().size() == 10 ): "Vocs list size does not match filter list size\n";

        for (default_Entry it : en_de_exam.getVocsToTest()){
            default_Entry entry = (default_Entry) dictionary.getTranslation(it); // Does this even work as expected
            assert entry.getId2().getId().contains("EN") : "Wrong Language for Voc1\n";
            assert entry.getId1().getId().contains("DE") : "Wrong Language for Voc2\n";
        }
    }*/

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