package com.group04.dictionary04.model;

import android.util.Log;
import com.group04.dictionary04.enums.DifficultyIdentifier;
import com.group04.dictionary04.enums.LanguageIdentifier;
import com.group04.dictionary04.interfaces.Dictionary;
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
        String index = id.substring(3, id.length());
        int i = Integer.parseInt(index);
        return vocabularies.get(i);
    }

    @Override
    public default_Vocabulary addVocabulary(String value) {
        //TODO bug we first have to check if the vocabulary is existent
        default_Vocabulary found = null;
        for(default_Vocabulary voc : vocabularies) {
            if(voc.getValue().equals(value)) {
                found = voc;
                break;
            }
        }

        if(found != null) {
            return found;
        } else {
            default_Vocabulary voc = new default_Vocabulary();
            voc.setValue(value);

            int i = vocabularies.size();
            voc.setId(this.getLangAbbrev() + "-" + i);

            this.vocabularies.add(voc);
            return voc;
        }
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

    public List<default_Vocabulary> getVocabulariesQuery(String search) {
        if(search != null && search.length() > 0) {
            List<default_Vocabulary> list = new ArrayList<>();

            for(default_Vocabulary voc : vocabularies) {
                if(voc.getValue().toLowerCase().contains(search.toLowerCase())) {
                    list.add(voc);
                }

            }

            return list;
        } else {
            return getVocabularies();
        }

    }

    public List<default_Entry> getVocabulariesQueryByTagRating(DifficultyIdentifier difficulty, String search, Dictionary dict) {
        //if(search != null && search.length() > 0) {
            List<default_Entry> list = new ArrayList<>();
            int difficulty_value = difficulty.getValue();
            for(default_Vocabulary vocIt : vocabularies) {
                    for(default_Entry entryIt : dict.getEntries())
                    {
                        Log.d("log", "check matching in Entry " +
                                entryIt.getId1().getId() + " " + entryIt.getId1().getValue() + " " +
                                entryIt.getId2().getId() + " " + entryIt.getId2().getValue() + " Rating:" + entryIt.getRating());


                        if(entryIt.getRating() == null) // no rating at the current record - so add it into the list
                        {
                            if((vocIt.getId().equals(entryIt.getId1().getId()) || vocIt.getId().equals(entryIt.getId2().getId())))
                            {
                                Log.d("log", "MATCHING FOUND BUT NO RATING GIVVEN AND ADD TO LIST " + vocIt.getId());
                                if(search == null || search.length() == 0
                                        || (entryIt.getTag() != null && entryIt.getTag().toLowerCase().contains(search.toLowerCase()))
                                        || entryIt.getId1().getValue().toLowerCase().contains(search.toLowerCase())
                                        || entryIt.getId2().getValue().toLowerCase().contains(search.toLowerCase()))
                                    list.add(entryIt);
                            }
                        }
                        else // there is a rating
                        {
                            if((vocIt.getId().equals(entryIt.getId1().getId()) || vocIt.getId().equals(entryIt.getId2().getId())) &&
                                    difficulty_value == Integer.valueOf(entryIt.getRating()))
                            {
                                Log.d("log", "MATCHING FOUND AND ADD TO LIST " + vocIt.getId());
                                if(search == null || search.length() == 0
                                        || (entryIt.getTag() != null && entryIt.getTag().toLowerCase().contains(search.toLowerCase()))
                                        || entryIt.getId1().getValue().toLowerCase().contains(search.toLowerCase())
                                        || entryIt.getId2().getValue().toLowerCase().contains(search.toLowerCase()))
                                    list.add(entryIt);
                            }
                        }
                    }
                    //list.add(voc);
                }


            return list;
       // }
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
