package com.group04.dictionary04.model;

import com.group04.dictionary04.interfaces.Vocabulary;

public class default_Pair implements com.group04.dictionary04.interfaces.Pair {

    private default_Vocabulary voc1 = null;
    private default_Vocabulary voc2 = null;
  
    @Override
    public default_Vocabulary getVoc1() {

        return this.voc1;
    }

    @Override
    public void setVoc1(default_Vocabulary voc1) {
        this.voc1 = voc1;
    }

    @Override
    public default_Vocabulary getVoc2() {
        return this.voc2;
    }

    @Override
    public void setVoc2(default_Vocabulary voc2) {
        this.voc2 = voc2;
    }
}
