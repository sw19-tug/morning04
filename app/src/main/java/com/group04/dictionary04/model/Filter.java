package com.group04.dictionary04.model;

import com.group04.dictionary.enums.DifficultyIdentifier;
import com.group04.dictionary.enums.LanguageIdentifier;

import java.util.List;

public class Filter implements com.group04.dictionary04.interfaces.Filter {

    @Override
    public LanguageIdentifier getLangID1() {
        return null;
    }

    @Override
    public void setLangID1(LanguageIdentifier langID1) {

    }

    @Override
    public LanguageIdentifier getLangID2() {
        return null;
    }

    @Override
    public void setLangID2(LanguageIdentifier langID2) {

    }

    @Override
    public List<String> getTags() {
        return null;
    }

    @Override
    public void setTags(List<String> tags) {

    }

    @Override
    public DifficultyIdentifier getDifficulty() {
        return null;
    }

    @Override
    public void setDifficulty(DifficultyIdentifier difficulty) {

    }
}
