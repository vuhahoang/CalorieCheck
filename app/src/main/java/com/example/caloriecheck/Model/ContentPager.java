package com.example.caloriecheck.Model;

import java.io.Serializable;

public class ContentPager implements Serializable {

    String color,colortext,title,content1,content2;

    public ContentPager(String color, String colortext, String title, String content1, String content2) {
        this.color = color;
        this.colortext = colortext;
        this.title = title;
        this.content1 = content1;
        this.content2 = content2;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColortext() {
        return colortext;
    }

    public void setColortext(String colortext) {
        this.colortext = colortext;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent1() {
        return content1;
    }

    public void setContent1(String content1) {
        this.content1 = content1;
    }

    public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2;
    }
}
