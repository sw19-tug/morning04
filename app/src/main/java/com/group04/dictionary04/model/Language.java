package com.group04.dictionary04.model;

import com.group04.dictionary.enums.LanguageIdentifier;
import com.group04.dictionary04.interfaces.Vocabulary;

import java.util.List;

public class Language implements com.group04.dictionary04.interfaces.Language {

    @Override
    public Vocabulary getVocByID(String id) {
        return null;
    }

    @Override
    public String addVocabulary(String value) {
        return null;
    }

    @Override
    public String getDisplayName() {
        return null;
    }

    @Override
    public void setDisplayName(String displayName) {

    }

    @Override
    public LanguageIdentifier getLangName() {
        return null;
    }

    @Override
    public void setLangName(LanguageIdentifier langName) {

    }

    @Override
    public List<Vocabulary> getVocabularies() {
        return null;
    }

    @Override
    public void setVocabularies(List<Vocabulary> vocabularies) {

    }
}
