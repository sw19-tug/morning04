package com.group04.dictionary04.model;

import android.util.Log;
import com.group04.dictionary.enums.DifficultyIdentifier;
import com.group04.dictionary.enums.LanguageIdentifier;
import com.group04.dictionary04.interfaces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class default_Dictionary implements Dictionary {
    private List<default_Entry> entries = new ArrayList<>();
    private HashMap<LanguageIdentifier, default_Language> languages = new HashMap<>();

    List<default_Exam> exams = new ArrayList<>();


    public default_Dictionary() {
        default_Language de = new default_Language("German", LanguageIdentifier.DE);
        default_Language en = new default_Language("English", LanguageIdentifier.EN);
        default_Language fr = new default_Language("French", LanguageIdentifier.FR);
        default_Language it = new default_Language("Italy", LanguageIdentifier.IT);
        default_Language sp = new default_Language("Spanish", LanguageIdentifier.SP);

        languages.put(LanguageIdentifier.DE, de);

        languages.put(LanguageIdentifier.EN, en);
        languages.put(LanguageIdentifier.FR, fr);
        languages.put(LanguageIdentifier.IT, it);
        languages.put(LanguageIdentifier.SP, sp);
    }

    
    @Override
    public default_Entry getEntry(String id1, String id2) {

        for(default_Entry entry : entries) {
            if((entry.getId1().equals(id1) && entry.getId2().equals(id2)) || (entry.getId2().equals(id1) && entry.getId1().equals(id2))) {
                return entry;
            }
        }

        return null;
    }

    @Override
    public List<default_Entry> getEntries(default_Filter filter) {
        return null;
    }

    @Override
    public default_Pair getTranslation(default_Entry entry) {
        return null;
    }

    @Override
    public default_Language getLanguage(LanguageIdentifier ident) {
        return languages.get(ident);
    }

    @Override
    public default_Exam getExam(default_Filter filter) {
        return null;
    }

    @Override
    public default_Exam generateExam(default_Filter filter) {

        return null;
    }

    @Override
    public void addTranslation(String voc1, String voc2, LanguageIdentifier lang1, LanguageIdentifier lang2) {
        default_Language first = getLanguage(lang1);
        default_Language second = getLanguage(lang2);


        default_Vocabulary ovoc1 = first.addVocabulary(voc1);
        default_Vocabulary ovoc2 = second.addVocabulary(voc2);

        default_Entry entry = new default_Entry();
        entry.setId1(ovoc1.getId());
        entry.setId2(ovoc2.getId());


        entries.add(entry);
    }

    @Override
    public void addDifficulty(default_Entry entry, DifficultyIdentifier diff) {

    }

    @Override
    public void addTag(default_Entry entry, String tag) {

    }

    @Override
    public void updateEntry(default_Entry entry) {

    }

    @Override
    public List<default_Entry> getEntries() {
        return entries;
    }

    @Override
    public void setEntries(List<default_Entry> entries) {

    }

    @Override
    public List<default_Language> getLanguages() {
        return null;
    }

    @Override
    public void setLanguages(List<default_Language> languages) {

    }

    @Override
    public List<default_Exam> getExams() {
        return exams;
    }

    @Override
    public void setExams(List<default_Exam> exams) {

    }
}
