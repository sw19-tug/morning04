package com.group04.dictionary04.model;

public class default_Entry implements com.group04.dictionary04.interfaces.Entry {
    String id1 = null;
    String id2 = null;
    String tag = null;
    String rating = null;

    @Override
    public String getId1() {
        return this.id1;
    }

    @Override
    public void setId1(String id1) {
        this.id1 = id1;
    }

    @Override
    public String getId2() {
        return this.id2;
    }

    @Override
    public void setId2(String id2) {
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
