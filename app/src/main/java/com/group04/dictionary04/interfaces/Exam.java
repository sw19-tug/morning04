package com.group04.dictionary04.interfaces;

import com.group04.dictionary04.model.default_Entry;
import com.group04.dictionary04.model.default_Filter;

import java.util.List;

public interface Exam {

    List<default_Entry> getVocsToTest();


    void setVocsToTest(List<default_Entry> vocsToTest);
    List<default_Entry> getFailedVocs();
    void setFailedVocs(List<default_Entry> failedVocs);

    String getResult();
    void setResult(String result);
    String getFilter();
    void setFilter(String filter);
}
