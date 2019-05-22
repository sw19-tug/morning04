package com.group04.dictionary04.model;

public class default_Entry implements com.group04.dictionary04.interfaces.Entry {
    default_Vocabulary id1 = null;
    default_Vocabulary id2 = null;
    String tag = null;
    String rating = null;

    public default_Entry() {

    }

    public default_Entry(String _id, String id2) {

    }


    @Override
    public String toString() {
        return id1.getValue();
    }

    @Override
    public default_Vocabulary getId1() {
        return this.id1;
    }

    @Override
    public void setId1(default_Vocabulary id1) {
        this.id1 = id1;
    }

    @Override
    public default_Vocabulary getId2() {
        return this.id2;
    }

    @Override
    public void setId2(default_Vocabulary id2) {
        this.id2 = id2;
    }

    @Override
    public String getTag() {
        return this.tag;
    }

    @Override
    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String getRating() {
        return this.rating;
    }

    @Override
    public void setRating(String rating) {
        this.rating = rating;
    }
}
