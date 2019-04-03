package com.group04.dictionary04.model;

import com.group04.dictionary.enums.LanguageIdentifier;
import com.group04.dictionary04.interfaces.Vocabulary;

import java.util.ArrayList;
import java.util.List;

public class default_Language implements com.group04.dictionary04.interfaces.Language {


    private String displayName = null;
    private LanguageIdentifier langName = null;
    private List<default_Vocabulary> vocabularies = new ArrayList<>();


    public default_Language(String _displayName, LanguageIdentifier _langName) {
        setDisplayName(_displayName);
        setLangName(_langName);
    }


    public String getLangAbbrev(){
        switch (this.langName){
            case DE: return "DE";
            case FR: return "FR";
            case EN: return "EN";
            case IT: return "IT";
            case SP: return "SP";
        }
        return null;
    }

    @Override
    public default_Vocabulary getVocByID(String id) {
        String index = id.substring(3, id.length() - 1);
        int i = Integer.parseInt(index);
        return vocabularies.get(i);
    }

    @Override
    public default_Vocabulary addVocabulary(String value) {

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
    public List<default_Vocabulary> getVocabularies() {
        return this.vocabularies;
    }

    public List<String> getVocabularyStrings() {
        List<String> list = new ArrayList<>();

        for(Vocabulary voc : vocabularies) {
            list.add(voc.getValue());
        }

        return list;
    }

    @Override
    public void setVocabularies(List<default_Vocabulary> vocabularies) {
        this.vocabularies = vocabularies;
    }

}
