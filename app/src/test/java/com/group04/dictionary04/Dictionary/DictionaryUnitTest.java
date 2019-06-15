package com.group04.dictionary04.Dictionary;


import com.group04.dictionary04.enums.LanguageIdentifier;
import com.group04.dictionary04.interfaces.Exam;
import com.group04.dictionary04.model.*;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.group04.dictionary04.enums.DifficultyIdentifier.BEGINNER;

public class DictionaryUnitTest{
    default_Dictionary dictionary;
    static int total_entries = 15;


    @Before
    public void setUp() {
        dictionary = new default_Dictionary();
        for (int i = 0; i < total_entries; i++){
            dictionary.addTranslation("first_de" + i, "second_en" +i,"" + (i%4), LanguageIdentifier.DE, LanguageIdentifier.EN);
        }
    }

    @Test
     public void getEntryTest(){
        List<default_Entry> all_added_entries = dictionary.getEntries();
        assert all_added_entries.size()-4 == total_entries : "Inserting Elements at setup failed!\n";
        for (int i = 4; i < total_entries; i++) {
            assert dictionary.getEntries().get(i).getId1().getId().contains("DE") : "Languageidentifier does not match ID\n";
            assert dictionary.getEntries().get(i).getId2().getId().contains("EN") : "Languageidentifier does not match ID\n";
        }
    }


    @Test
    public void getEntryNameTest() {
        for (int i = 4; i < total_entries; i++) {

            assert dictionary.getEntries().get(i).getId1().getValue().contains("first_de" + (i-4)) : "DE ENTRY " + (i-4) + " NOT MATCH\n";
            assert dictionary.getEntries().get(i).getId2().getValue().contains("second_en" + (i-4)) : "EN ENTRY" + (i-4) + "NOT MATCH\n";
        }
    }

    @Test
    public void getRatingTest() {
        for (int i = 4; i < total_entries; i++) {
            assert dictionary.getEntries().get(i).getRating().contains(""+(i%4)): "RATING OF ENTRY " + (i-4) + " NOT MATCH\n";
        }
    }

    @Test
    public void setRatingTest(){

        for(int i = 0; i < total_entries; i++)
            dictionary.getEntries().get(i).setRating(""+(i%4));

        for(int i = 0; i < total_entries; i++)
            assert dictionary.getEntries().get(i).getRating().equals(""+(i%4)) : "SET RATING FOR" + i +"NOT MATCH\n";
    }

    @Test
    public void getDifficulty() {
        assert dictionary.getEntries().get(4).getRating().contains(String.valueOf(BEGINNER.getValue() - 1)) : "GET DIFFICULTY NOT MATCH\n";

    }

    @Test
    public void deleteExam() {
        default_Exam exam = dictionary.generateExam(null);
        dictionary.getExams().add(exam);
        List<default_Exam> exams = dictionary.getExams();
        assert exams.size() == 1;
        dictionary.deleteExam(exam);
        assert exams.size() == 0;
    }


}
