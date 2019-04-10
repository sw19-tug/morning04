package com.group04.dictionary04.model;

import com.group04.dictionary04.interfaces.Entry;

import java.util.ArrayList;
import java.util.List;

public class default_Exam implements com.group04.dictionary04.interfaces.Exam {
    List<default_Entry> vocsToTest;
    List<default_Entry> failedVocs;
    String result = null;
    String filter = null;
    final static int limitVocs = 5;

    public default_Exam(){
        vocsToTest = new ArrayList<>();
        failedVocs = new ArrayList<>();
    }

    /*
    public default_Exam(default_Filter filter){
       if (filter == null)
       {

       }
    }
    */

    @Override
    public List<default_Entry> getVocsToTest() {
        return vocsToTest;
    }

    @Override
    public void setVocsToTest(List<default_Entry> vocsToTest) {

    }

    @Override
    public List<default_Entry> getFailedVocs() {
        return failedVocs;
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
