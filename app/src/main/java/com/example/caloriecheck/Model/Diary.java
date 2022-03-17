package com.example.caloriecheck.Model;

public class Diary {
    String day,month,year,dow;
    String content;

    public Diary() {
    }

    public Diary(String day, String month, String year, String content) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.content = content;
    }

    public Diary(String day, String month, String year, String dow, String content) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.dow = dow;
        this.content = content;
    }

    public String getDow() {
        return dow;
    }

    public void setDow(String dow) {
        this.dow = dow;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
