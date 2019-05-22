package com.group04.dictionary04.Exam;

import org.junit.Before;

public class ExamUnitTest {

    @Before
    public void setup() {

    }

    /*@Test
    public void generateExamTest() {
        Exam random_exam = dictionary.generateExam(null);

        default_Filter filter = new default_Filter();
        filter.setLimit_pairs(10);
        Exam random_exam10 = dictionary.generateExam(null);

        filter.setLangID2(LanguageIdentifier.EN);
        filter.setLangID1(LanguageIdentifier.DE);
        Exam en_de_exam = dictionary.generateExam(filter);

        assert !random_exam.getVocsToTest().isEmpty() : "Empty Exam with Filter=null\n";
        assert !(random_exam10.getVocsToTest().size() == 10 ): "Vocs list size does not match filter list size\n";

        for (default_Entry it : en_de_exam.getVocsToTest()){
            default_Entry entry = (default_Entry) dictionary.getTranslation(it); // Does this even work as expected
            assert entry.getId2().getId().contains("EN") : "Wrong Language for Voc1\n";
            assert entry.getId1().getId().contains("DE") : "Wrong Language for Voc2\n";
        }
    }*/
}

