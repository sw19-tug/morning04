package com.group04.dictionary04.model;

public class default_Vocabulary implements com.group04.dictionary04.interfaces.Vocabulary {

    private String id = null;
    private String value = null;

    public default_Vocabulary() {

    }

    public default_Vocabulary(String _id, String value) {

    }


    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }
}
