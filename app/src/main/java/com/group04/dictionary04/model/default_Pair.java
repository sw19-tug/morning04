package com.group04.dictionary04.model;

import com.group04.dictionary04.interfaces.Vocabulary;

public class default_Pair implements com.group04.dictionary04.interfaces.Pair {
    Vocabulary voc1 = null;
    Vocabulary voc2 = null;


    @Override
    public Vocabulary getVoc1() {
        return this.voc1;
    }

    @Override
    public void setVoc1(Vocabulary voc1) {
        this.voc1 = voc1;
    }

    @Override
    public Vocabulary getVoc2() {
        return this.voc2;
    }

    @Override
    public void setVoc2(Vocabulary voc2) {
        this.voc2 = voc2;
    }
}
