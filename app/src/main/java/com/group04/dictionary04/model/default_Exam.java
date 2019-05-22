package com.group04.dictionary04.model;

import com.group04.dictionary04.interfaces.Entry;

import java.util.ArrayList;
import java.util.List;

public class default_Exam implements com.group04.dictionary04.interfaces.Exam {
    List<default_Entry> vocsToTest;
    List<default_Entry> failedVocs;
    String result = null;
    default_Filter filter = null;
    final static int limitVocs = 15;

    public default_Exam(){
        vocsToTest = new ArrayList<>();
        failedVocs = new ArrayList<>();
    }

    @Override
    public void addVocToTest(default_Entry entry)
    {
        this.vocsToTest.add(entry);
    }

    @Override
    public List<default_Entry> getVocsToTest() {
        return vocsToTest;
    }

    @Override
    public void setVocsToTest(List<default_Entry> vocsToTest) {
        this.vocsToTest = vocsToTest;
    }

    @Override
    public List<default_Entry> getFailedVocs() {
        return failedVocs;
    }

    @Override
    public void setFailedVocs(List<default_Entry> failedVocs) {
        this.failedVocs = failedVocs;
    }

    @Override
    public String getResult() {
        return null;
    }

    @Override
    public void setResult(String result) {

    }

    @Override
    public default_Filter getFilter() {
        return this.filter;
    }

    @Override
    public void setFilter(default_Filter filter) {
        this.filter = filter;
    }

    @Override
    public int countVocsToTest(){
        return vocsToTest.size();
    }

    @Override
    public int countFailedVocs(){

        return failedVocs.size();
    }
}
