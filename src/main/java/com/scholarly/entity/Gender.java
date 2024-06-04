package com.scholarly.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {

    NOT_KNOWN((short) 0),
    MALE((short) 1),
    FEMALE((short) 2),
    NOT_APPLICABLE((short) 9);

    public final short code;

    Gender(short code) {
        this.code = code;
    }

    public static Gender fromCode(short code) {
        for (Gender gender : values()) {
            if (gender.code == code) {
                return gender;
            }
        }
        throw new IllegalArgumentException("Invalid gender code: " + code);
    }

    @JsonValue
    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
