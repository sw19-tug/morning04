package com.group04.dictionary04.interfaces;

import com.group04.dictionary.enums.LanguageIdentifier;
import com.group04.dictionary04.model.default_Vocabulary;

import java.util.List;

public interface Language {
    String displayName = null;
    LanguageIdentifier langName = null;
    List<default_Vocabulary> vocabularies = null;

    default_Vocabulary getVocByID(String id);
    default_Vocabulary addVocabulary(String value);

    /* xxxxxxxxxxxx Getter & Setter xxxxxxxxxxxxxxxxx */

    String getDisplayName();
    void setDisplayName(String displayName);
    LanguageIdentifier getLangName();
    void setLangName(LanguageIdentifier langName);
    List<default_Vocabulary> getVocabularies();
    void setVocabularies(List<default_Vocabulary> vocabularies);
}
