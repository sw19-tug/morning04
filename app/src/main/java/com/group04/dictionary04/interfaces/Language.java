package com.group04.dictionary04.interfaces;

import com.group04.dictionary.enums.LanguageIdentifier;

import java.util.List;

public interface Language {

    Vocabulary getVocByID(String id);
    Vocabulary addVocabulary(String value);

    /* xxxxxxxxxxxx Getter & Setter xxxxxxxxxxxxxxxxx */

    String getLangAbbrev();
    String getDisplayName();
    void setDisplayName(String displayName);
    LanguageIdentifier getLangName();
    void setLangName(LanguageIdentifier langName);
    List<Vocabulary> getVocabularies();
    void setVocabularies(List<Vocabulary> vocabularies);
}
