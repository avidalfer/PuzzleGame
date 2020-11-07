package com.example.puzzlegame.model;

public enum Language {
    EN(0),
    ES(1),
    CAT(2);

    private int code;

    Language(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
