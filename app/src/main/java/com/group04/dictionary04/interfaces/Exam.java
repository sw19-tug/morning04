package com.group04.dictionary04.interfaces;

import java.util.List;

public interface Exam {
    List<Entry> vocsToTest = null;
    List<Entry> failedVocs = null;
    String result = null;
    String filter = null;

    List<Entry> getVocsToTest();
    void setVocsToTest(List<Entry> vocsToTest);
    List<Entry> getFailedVocs();
    void setFailedVocs(List<Entry> failedVocs);
    String getResult();
    void setResult(String result);
    String getFilter();
    void setFilter(String filter);
}
