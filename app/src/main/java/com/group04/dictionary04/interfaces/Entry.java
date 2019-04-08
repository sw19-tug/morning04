package com.group04.dictionary04.interfaces;

import com.group04.dictionary04.model.default_Vocabulary;

public interface Entry {

    default_Vocabulary getId1();
    void setId1(default_Vocabulary id1);
    default_Vocabulary getId2();
    void setId2(default_Vocabulary id2);
    String getTag();
    void setTag(String tag) ;
    String getRating();
    void setRating(String rating);
}
