package com.aeo.artwork.bean;

import java.util.ArrayList;

public class SubContent {
    private String text;
    private ArrayList<String> img;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ArrayList<String> getImg() {
        return img;
    }

    public void setImg(ArrayList<String> img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "SubContent{" +
                "text='" + text + '\'' +
                ", img=" + img +
                '}';
    }
}
