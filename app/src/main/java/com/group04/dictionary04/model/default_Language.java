package com.group04.dictionary04.model;

import com.group04.dictionary.enums.LanguageIdentifier;
import com.group04.dictionary04.interfaces.Vocabulary;

import java.util.ArrayList;
import java.util.List;

public class default_Language implements com.group04.dictionary04.interfaces.Language {

    String displayName = null;
    LanguageIdentifier langName = null;
    List<Vocabulary> vocabularies = null;

    public default_Language(String _displayName, LanguageIdentifier _langName) {
        setDisplayName(_displayName);
        setLangName(_langName);
        List<Vocabulary> vocabularies = new ArrayList<>();
    }


    public String getLangAbbrev(){
        switch (this.langName){
            case DE: return "DE";
            case FR: return "FR";
            case EN: return "EN";
            case IT: return "IT";
            case SP: return "SP";
        }
    }

    @Override
    public default_Vocabulary getVocByID(String id) {
        id.
        return null;
    }

    @Override
    public Vocabulary addVocabulary(String value) {
        default_Vocabulary voc = new default_Vocabulary();
        voc.setValue(value);

        int i = vocabularies.size();
        voc.setId(this.getLangAbbrev() + "-" + i);

        this.vocabularies.add(voc);
        return voc;
    }

    @Override
    public String getDisplayName() {
        return this.displayName;
    }

    @Override
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public LanguageIdentifier getLangName() {
        return this.langName;
    }

    @Override
    public void setLangName(LanguageIdentifier langName) {
        this.langName = langName;
    }

    @Override
    public List<Vocabulary> getVocabularies() {
        return this.vocabularies;
    }

    @Override
    public void setVocabularies(List<Vocabulary> vocabularies) {
        this.vocabularies = vocabularies;
    }

}
