package com.group04.dictionary04.model;

import com.group04.dictionary04.interfaces.Entry;

import java.util.List;

public class default_Exam implements com.group04.dictionary04.interfaces.Exam {
    List<default_Entry> vocsToTest = null;
    List<default_Entry> failedVocs = null;
    String result = null;
    String filter = null;

    public default_Exam(default_Filter filter){
       if (filter == null)
       {

       }
    }

    @Override
    public List<default_Entry> getVocsToTest() {
        return null;
    }

    @Override
    public void setVocsToTest(List<default_Entry> vocsToTest) {

    }

    @Override
    public List<default_Entry> getFailedVocs() {
        return null;
    }

    @Override
    public void setFailedVocs(List<default_Entry> failedVocs) {

    }

    @Override
    public String getResult() {
        return null;
    }

    @Override
    public void setResult(String result) {

    }

    @Override
    public String getFilter() {
        return null;
    }

    @Override
    public void setFilter(String filter) {

    }
}
