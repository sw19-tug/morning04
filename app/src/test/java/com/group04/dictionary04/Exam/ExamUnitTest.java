package com.group04.dictionary04.Exam;

import com.group04.dictionary04.enums.LanguageIdentifier;
import com.group04.dictionary04.model.default_Entry;
import com.group04.dictionary04.model.default_Exam;
import com.group04.dictionary04.model.default_Filter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

public class ExamUnitTest {
    default_Exam exam;


    @Before
    public void setUp() {
        exam = new default_Exam();
        default_Entry entry = new default_Entry();
        exam.addVocToTest(entry);
        default_Filter filter = new default_Filter();
        exam.setFilter(filter);
    }

    @Before
    public void setVocsToTestUnitTest(){
        default_Entry entry = new default_Entry("test1", "test2");
        List<default_Entry> vocsToTest = Collections.singletonList(entry);
        exam.setVocsToTest(vocsToTest);
        Assert.assertNotNull(exam.getVocsToTest());
    }

    @Test
    public void setFilterTest(){
        exam.setFilter(null);
        Assert.assertNull(exam.getFilter());

    }

    @Test
    public void getVocsToTestUnitTest(){
        Assert.assertNotNull(exam.getVocsToTest());
    }

    @Test
    public void getFailedVocsTest(){
        List<default_Entry> failedVocs = exam.getFailedVocs();
        Assert.assertNotNull(failedVocs);
    }

    @Test
    public void setFailedVocsTest(){

        exam.setFailedVocs(null);
        Assert.assertNull(exam.getFailedVocs());
    }

    @Test
    public void getFilterTest(){
        Assert.assertNotNull(exam.getFilter());
    }

}

