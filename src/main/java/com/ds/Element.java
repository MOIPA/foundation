package com.ds;

public class Element implements IElement {

    private int code;
    private String content;

    public Element(int code, String content) {
        this.code = code;
        this.content = content;
    }

    public Element() {
        this.code = 0;
        this.content = "default";
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String show() {
        return "code:" + code + " ==> content:" + content;
    }

    @Override
    public String toString() {
        return this.show();
    }
}
