package com.group04.dictionary04.interfaces;

import com.group04.dictionary.enums.DifficultyIdentifier;
import com.group04.dictionary.enums.LanguageIdentifier;

import java.util.ArrayList;
import java.util.List;

public interface Filter {
    LanguageIdentifier langID1 = null;
    LanguageIdentifier langID2 = null;
    List<String> tags = null;
    DifficultyIdentifier difficulty = null;

    public LanguageIdentifier getLangID1();
    public void setLangID1(LanguageIdentifier langID1);
    public LanguageIdentifier getLangID2();
    public void setLangID2(LanguageIdentifier langID2);
    public List<String> getTags();
    public void setTags(List<String> tags);
    public DifficultyIdentifier getDifficulty();
    public void setDifficulty(DifficultyIdentifier difficulty);
}
