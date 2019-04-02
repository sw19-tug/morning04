package com.group04.dictionary04.model;

import com.group04.dictionary.enums.DifficultyIdentifier;
import com.group04.dictionary.enums.LanguageIdentifier;
import com.group04.dictionary04.interfaces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class default_Dictionary implements Dictionary {
    private List<default_Entry> entries = new ArrayList<>();
    private HashMap<LanguageIdentifier, default_Language> languages = new HashMap<>();
    List<Exam> exams = new ArrayList<>();

    public default_Dictionary() {
        default_Language de = new default_Language("German", LanguageIdentifier.DE);
        default_Language en = new default_Language("English", LanguageIdentifier.EN);
        default_Language fr = new default_Language("French", LanguageIdentifier.FR);
        default_Language it = new default_Language("Italy", LanguageIdentifier.IT);
        default_Language sp = new default_Language("Spanish", LanguageIdentifier.SP);

        languages.put(LanguageIdentifier.DE, de);
        languages.put(LanguageIdentifier.DE, en);
        languages.put(LanguageIdentifier.DE, fr);
        languages.put(LanguageIdentifier.DE, it);
        languages.put(LanguageIdentifier.DE, sp);
    }

    @Override
    public Entry getEntry(String id1, String id2) {
        for(default_Entry entry : entries) {
            if((entry.getId1().equals(id1) && entry.getId2().equals(id2)) || (entry.getId2().equals(id1) && entry.getId1().equals(id2))) {
                return entry;
            }
        }

        return null;
    }

    @Override
    public List<Entry> getEntries(Filter filter) {
        return null;
    }

    @Override
    public Pair getTranslation(Entry entry) {
        return null;
    }

    @Override
    public default_Language getLanguage(LanguageIdentifier ident) {
        return languages.get(ident);
    }

    @Override
    public Exam getExam(Filter filter) {
        return null;
    }

    @Override
    public Exam generateExam(Filter filter) {
        return null;
    }

    @Override
    public void addTranslation(String voc1, String voc2, LanguageIdentifier lang1, LanguageIdentifier lang2) {
        default_Language first = getLanguage(lang1);
        default_Language second = getLanguage(lang2);

        Vocabulary ovoc1 = first.addVocabulary(voc1);
        Vocabulary ovoc2 = second.addVocabulary(voc2);

        default_Entry entry = new default_Entry();
        entry.setId1(ovoc1.getId());
        entry.setId2(ovoc2.getId());

        entries.add(entry);
    }

    @Override
    public void addDifficulty(Entry entry, DifficultyIdentifier diff) {

    }

    @Override
    public void addTag(Entry entry, String tag) {

    }

    @Override
    public void updateEntry(Entry entry) {

    }

    @Override
    public List<Entry> getEntries() {
        return null;
    }

    @Override
    public void setEntries(List<Entry> entries) {

    }

    @Override
    public List<Language> getLanguages() {
        return null;
    }

    @Override
    public void setLanguages(List<Language> languages) {

    }

    @Override
    public List<Exam> getExams() {
        return exams;
    }

    @Override
    public void setExams(List<Exam> exams) {

    }
}
