package com.group04.dictionary04.model;

import com.group04.dictionary.enums.DifficultyIdentifier;
import com.group04.dictionary.enums.LanguageIdentifier;
import com.group04.dictionary04.interfaces.Entry;
import com.group04.dictionary04.interfaces.Exam;
import com.group04.dictionary04.interfaces.Filter;
import com.group04.dictionary04.interfaces.Language;
import com.group04.dictionary04.interfaces.Pair;

import java.util.ArrayList;
import java.util.List;

public class Dictionary implements com.group04.dictionary04.interfaces.Dictionary {

    @Override
    public Entry getEntry(String id1, String id2) {
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
    public Exam getExam(Filter filter) {
        return null;
    }

    @Override
    public void addTranslation(String voc1, String voc2, LanguageIdentifier lang1, LanguageIdentifier lang2) {

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
