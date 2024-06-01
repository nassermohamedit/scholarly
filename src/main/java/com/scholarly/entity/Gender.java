package com.scholarly.entity;

public enum Gender {

    NOT_KNOWN(0),
    MALE(1),
    FEMALE(2),
    NOT_APPLICABLE(9);

    public final int code;

    Gender(int code) {
        this.code = code;
    }

    public static Gender fromCode(int code) {
        for (Gender gender : values()) {
            if (gender.code == code) {
                return gender;
            }
        }
        throw new IllegalArgumentException("Invalid gender code: " + code);
    }


}
