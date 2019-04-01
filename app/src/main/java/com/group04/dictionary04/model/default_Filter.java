package com.group04.dictionary04.model;

import com.group04.dictionary.enums.DifficultyIdentifier;
import com.group04.dictionary.enums.LanguageIdentifier;

import java.util.List;

public class default_Filter implements com.group04.dictionary04.interfaces.Filter {

    LanguageIdentifier langID1 = null;
    LanguageIdentifier langID2 = null;
    Integer limit_pairs = 0; // maximum number of translations ; 0 = unlimited
    List<String> tags = null;
    DifficultyIdentifier difficulty = null;

    @Override
    public Integer getLimit_pairs() {
        return limit_pairs;
    }

    @Override
    public void setLimit_pairs(Integer limit) {
        limit_pairs = limit;
    }
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
