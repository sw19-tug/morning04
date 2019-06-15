package com.group04.dictionary04.model;

import android.view.View;
import android.widget.ListView;
import com.group04.dictionary04.enums.LanguageIdentifier;

import java.util.regex.Matcher;

public class default_Vocabulary implements com.group04.dictionary04.interfaces.Vocabulary {

    private String id = null;
    private String value = null;
    private LanguageIdentifier language = null;

    public  String getLangString(){
        switch (this.language){
            case DE: return "German";
            case FR: return "French";
            case EN: return "English";
            case IT: return "Italian";
            case SP: return "Spanish";
        }
        return null;
    }


    public default_Vocabulary() {

    }

    public default_Vocabulary(String _id, String value) {

    }

    public default_Vocabulary(String _id, String value, LanguageIdentifier lang) {

    }

    public LanguageIdentifier getLanguage() {
        return language;
    }

    public void setLanguage(LanguageIdentifier language) {
        this.language = language;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
