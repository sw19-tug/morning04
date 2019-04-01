package com.group04.dictionary04.interfaces;

import com.group04.dictionary.enums.DifficultyIdentifier;
import com.group04.dictionary.enums.LanguageIdentifier;

import java.util.ArrayList;
import java.util.List;

public interface Dictionary {
    List<Entry> entries = new ArrayList<>();
    List<Language> languages = new ArrayList<>();
    List<Exam> exams = new ArrayList<>();

    Entry getEntry(String id1, String id2);
    List<Entry> getEntries(Filter filter);
    Pair getTranslation(Entry entry);
    Exam getExam(Filter filter);
    Exam generateExam(Filter filter);

    void addTranslation(String voc1, String voc2, LanguageIdentifier lang1, LanguageIdentifier lang2);
    void addDifficulty(Entry entry, DifficultyIdentifier diff);
    void addTag(Entry entry, String tag);
    void updateEntry(Entry entry);

    /* xxxxxxxxxxxx Getter & Setter xxxxxxxxxxxxxxxxx */

    List<Entry> getEntries();
    void setEntries(List<Entry> entries);
    List<Language> getLanguages();
    void setLanguages(List<Language> languages);
    List<Exam> getExams();
    void setExams(List<Exam> exams);
}
