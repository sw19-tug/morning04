package com.group04.dictionary04.enums;

public enum DifficultyIdentifier {
    BEGINNER(1), INTERMEDIATE(2), ADVANCED(3), NATIVE(4);

    private final int value;
    private DifficultyIdentifier(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
