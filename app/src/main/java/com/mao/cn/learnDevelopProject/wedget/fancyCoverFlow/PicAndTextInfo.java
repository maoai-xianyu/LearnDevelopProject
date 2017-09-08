package com.mao.cn.learnDevelopProject.wedget.fancyCoverFlow;

public class PicAndTextInfo {

    private int picture;
    private String textContent;

    public PicAndTextInfo(int picture, String textContent) {
        this.picture = picture;
        this.textContent = textContent;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }
}
