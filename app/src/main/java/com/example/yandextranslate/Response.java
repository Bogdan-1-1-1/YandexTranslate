package com.example.yandextranslate;

import java.util.Arrays;

public class Response {
    int code;
    String lang;
    String[] text;

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(Arrays.toString(text));
        str.deleteCharAt(0);
        str.deleteCharAt(str.length()-1);
        return str.toString();
    }
}
