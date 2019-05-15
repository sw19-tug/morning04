package com.group04.dictionary04.interfaces;

import com.group04.dictionary04.enums.DifficultyIdentifier;
import com.group04.dictionary04.enums.LanguageIdentifier;
import com.group04.dictionary04.model.*;

import java.util.List;

public interface Dictionary {

    default_Entry getEntry(String id1, String id2);
    List<default_Entry> getEntries(default_Filter filter);
    default_Entry getTranslation(default_Entry entry);
    default_Language getLanguage(LanguageIdentifier ident);
    default_Exam getExam(default_Filter filter);
    default_Exam generateExam(default_Filter filter);

    void addTranslationWithDiffAndTag(String voc1, String voc2, LanguageIdentifier lang1, LanguageIdentifier lang2,DifficultyIdentifier diff, String tag);
    void addTranslation(String voc1, String voc2, LanguageIdentifier lang1, LanguageIdentifier lang2);
    void addDifficulty(default_Entry entry, DifficultyIdentifier diff);
    void addTag(default_Entry entry, String tag);
    void updateEntry(default_Entry entry);

    /* xxxxxxxxxxxx Getter & Setter xxxxxxxxxxxxxxxxx */

    List<default_Entry> getEntries();
    void setEntries(List<default_Entry> entries);
    List<default_Language> getLanguages();
    void setLanguages(List<default_Language> languages);
    List<default_Exam> getExams();
    void setExams(List<default_Exam> exams);
}
