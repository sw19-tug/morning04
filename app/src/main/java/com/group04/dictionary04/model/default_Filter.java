package com.group04.dictionary04.model;

import com.group04.dictionary04.enums.DifficultyIdentifier;
import com.group04.dictionary04.enums.LanguageIdentifier;

import java.util.List;

public class default_Filter implements com.group04.dictionary04.interfaces.Filter {


    private LanguageIdentifier langID1 = null;
    private LanguageIdentifier langID2 = null;
    private Integer limit_pairs = 0; // maximum number of translations ; 0 = unlimited
    private List<String> tags = null;
    private DifficultyIdentifier difficulty = null;


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
        return langID1;
    }

    @Override
    public void setLangID1(LanguageIdentifier langID) {
        langID1 = langID;
    }

    @Override
    public LanguageIdentifier getLangID2() {
        return langID2;
    }

    @Override
    public void setLangID2(LanguageIdentifier langID) {
        langID2 = langID;
    }

    @Override
    public List<String> getTags() {
        return tags;
    }

    @Override
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public DifficultyIdentifier getDifficulty() {
        return difficulty;
    }

    @Override
    public void setDifficulty(DifficultyIdentifier difficulty) {
        this.difficulty = difficulty;
    }
}
