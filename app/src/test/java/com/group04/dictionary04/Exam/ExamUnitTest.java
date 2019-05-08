package com.group04.dictionary04.Exam;

import com.group04.dictionary04.enums.LanguageIdentifier;
import com.group04.dictionary04.model.default_Entry;
import com.group04.dictionary04.model.default_Exam;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

public class ExamUnitTest {
    default_Exam exam;


    @Before
    public void setUp() {
        //exam = new default_Exam();

    }

    @Test
    public void getVocsToTestUnitTest(){
        Assert.assertNull(exam.getVocsToTest());
    }

    @Test
    public void setVocsToTestUnitTest(){
        default_Entry entry = new default_Entry("test1", "test2");
        List<default_Entry> vocsToTest = Collections.singletonList(entry);
        exam.setVocsToTest(vocsToTest);
    }

    @Test
    public void getFailedVocsTest(){
        List<default_Entry> failedVocs = exam.getFailedVocs();
        Assert.assertNull(failedVocs);
    }

    @Test
    public void setFailedVocsTest(){

    }

    @Test
    public void getResultTest(){

    }

    @Test
    public void setResultTest(){

    }

    @Test
    public void getFilterTest(){

    }

    @Test
    public void setFilterTest(){

    }
}

