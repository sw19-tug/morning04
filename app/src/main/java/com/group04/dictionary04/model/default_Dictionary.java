package com.group04.dictionary04.model;

import com.group04.dictionary04.enums.DifficultyIdentifier;
import com.group04.dictionary04.enums.LanguageIdentifier;
import com.group04.dictionary04.interfaces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class default_Dictionary implements Dictionary {
    private List<default_Entry> entries = new ArrayList<>();
    private HashMap<LanguageIdentifier, default_Language> languages = new HashMap<>();

    List<default_Exam> exams = new ArrayList<>();


    public default_Dictionary() {
        default_Language de = new default_Language("German", LanguageIdentifier.DE);
        default_Language en = new default_Language("English", LanguageIdentifier.EN);
        default_Language fr = new default_Language("French", LanguageIdentifier.FR);
        default_Language it = new default_Language("Italy", LanguageIdentifier.IT);
        default_Language sp = new default_Language("Spanish", LanguageIdentifier.SP);

        languages.put(LanguageIdentifier.DE, de);

        languages.put(LanguageIdentifier.EN, en);
        languages.put(LanguageIdentifier.FR, fr);
        languages.put(LanguageIdentifier.IT, it);
        languages.put(LanguageIdentifier.SP, sp);
    }


    @Override
    public default_Entry getEntry(String id1, String id2) {

        for(default_Entry entry : entries) {
            if((entry.getId1().equals(id1) && entry.getId2().equals(id2)) || (entry.getId2().equals(id1) && entry.getId1().equals(id2))) {
                return entry;
            }
        }

        return null;
    }

    @Override
    public List<default_Entry> getEntries(default_Filter filter) {
        return null;
    }

    @Override
    public default_Entry getTranslation(default_Entry entry) {
        for(default_Entry temp : entries) {
            if(temp.getId1().getId().equals(entry.getId1().getId()) && temp.getId2().getId().equals(entry.getId2().getId())) {
                return temp;
            } else if(temp.getId1().getId().equals(entry.getId2().getId()) && temp.getId2().getId().equals(entry.getId1().getId())) {
                return temp;
            }
        }

        return null;
    }

    @Override
    public default_Language getLanguage(LanguageIdentifier ident) {
        return languages.get(ident);
    }

    @Override
    public default_Exam getExam(default_Filter filter) {
        return null;
    }

    @Override
    public default_Exam generateExam(default_Filter filter) {

        return null;
    }

    //TODO do we really need this function?
    @Override
    public void addTranslation(String voc1, String voc2, LanguageIdentifier lang1, LanguageIdentifier lang2) {
        default_Language first = getLanguage(lang1);
        default_Language second = getLanguage(lang2);


        default_Vocabulary ovoc1 = first.addVocabulary(voc1);
        ovoc1.setLanguage(lang1);
        default_Vocabulary ovoc2 = second.addVocabulary(voc2);
        ovoc2.setLanguage(lang2);
        default_Entry entry = new default_Entry();
        entry.setId1(ovoc1);
        entry.setId2(ovoc2);


        entries.add(entry);
    }

    @Override
    public void addTranslationWithDiffAndTag(String voc1, String voc2, LanguageIdentifier lang1, LanguageIdentifier lang2,DifficultyIdentifier diff, String tag)
    {
        default_Language first = getLanguage(lang1);
        default_Language second = getLanguage(lang2);


        default_Vocabulary ovoc1 = first.addVocabulary(voc1);
        ovoc1.setLanguage(lang1);
        default_Vocabulary ovoc2 = second.addVocabulary(voc2);
        ovoc2.setLanguage(lang2);
        default_Entry entry = new default_Entry();
        entry.setId1(ovoc1);
        entry.setId2(ovoc2);
        entry.setRating(diff.toString());
        entry.setTag(tag);


        entries.add(entry);
    }



    @Override
    public void addDifficulty(default_Entry entry, DifficultyIdentifier diff) {
        entry.setRating(diff.toString());
    }

    @Override
    public void addTag(default_Entry entry, String tag) {
        entry.setTag(tag);
    }

    @Override
    public void updateEntry(default_Entry entry) {

    }

    @Override
    public List<default_Entry> getEntries() {
        return entries;
    }

    @Override
    public void setEntries(List<default_Entry> entries) {

    }

    @Override
    public List<default_Language> getLanguages() {
        return new ArrayList<default_Language>(languages.values());
    }


    public String getTranslationString(default_Vocabulary voc) {
        String idToSearch = voc.getId();
        String returnString = "";

        for(default_Entry entry : entries) {
            if(entry.getId1().getId().equals(idToSearch)) {
                returnString += entry.getId2().getLanguage() + ": " + entry.getId2() + "\n";
            } else if(entry.getId2().getId().equals(idToSearch)) {
                returnString += entry.getId1().getLanguage() + ": " + entry.getId1() + "\n";
            }
        }

        return returnString;
    }

    public default_Language getLanguageByIndex(String name) {
        for(default_Language language : languages.values()) {
            if(language.getDisplayName().equals(name))
                return language;
        }

        return null;
    }

    public List<String> getLanguagesStrings() {
        List<String> list = new ArrayList<>();

        for(default_Language language : languages.values()) {
            list.add(language.getDisplayName());
        }

        return list;
    }

    @Override
    public void setLanguages(List<default_Language> languages) {

    }

    @Override
    public List<default_Exam> getExams() {
        return exams;
    }

    @Override
    public void setExams(List<default_Exam> exams) {

    }
}