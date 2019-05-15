package com.group04.dictionary04.interfaces;

import com.group04.dictionary04.enums.DifficultyIdentifier;
import com.group04.dictionary04.enums.LanguageIdentifier;

import java.util.List;

public interface Filter {


    public Integer getLimit_pairs();
    public void setLimit_pairs(Integer limit);
    public LanguageIdentifier getLangID1();
    public void setLangID1(LanguageIdentifier langID);
    public LanguageIdentifier getLangID2();
    public void setLangID2(LanguageIdentifier langID);
    public List<String> getTags();
    public void setTags(List<String> tags);
    public DifficultyIdentifier getDifficulty();
    public void setDifficulty(DifficultyIdentifier difficulty);
}
