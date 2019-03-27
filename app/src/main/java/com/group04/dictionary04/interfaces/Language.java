package com.group04.dictionary04.interfaces;

import com.group04.dictionary.enums.LanguageIdentifier;

import java.util.List;

public interface Language {
    String displayName = null;
    LanguageIdentifier langName = null;
    List<Vocabulary> vocabularies = null;

    Vocabulary getVocByID(String id);
    String addVocabulary(String value);

    /* xxxxxxxxxxxx Getter & Setter xxxxxxxxxxxxxxxxx */

    String getDisplayName();
    void setDisplayName(String displayName);
    LanguageIdentifier getLangName();
    void setLangName(LanguageIdentifier langName);
    List<Vocabulary> getVocabularies();
    void setVocabularies(List<Vocabulary> vocabularies);
}
