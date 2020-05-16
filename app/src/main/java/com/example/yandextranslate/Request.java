package com.example.yandextranslate;

public class Request {
    String text, lang;


    public Request(String text, String lang) {
        this.text = text;
        this.lang = lang;
    }

    String format = "plain";
    String key = "trnsl.1.1.20200516T093521Z.dad98d4a1f4d758d.9d55d15e5e792c81c27a544ab8a48ec67585f749";

    public byte[] toByteArray() {
        String post = "key="+key+"&text="+text+"&lang="+lang+"&format="+format;
        return post.getBytes();
    }
}
