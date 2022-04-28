package com.example.caloriecheck.Model;

import java.util.ArrayList;

public class Plan {

    String name,condition,img;
    ArrayList<Dayrecipe> dayrecipes;

    public Plan(String name, String condition, String img, ArrayList<Dayrecipe> dayrecipes) {
        this.name = name;
        this.condition = condition;
        this.img = img;
        this.dayrecipes = dayrecipes;
    }

    public Plan(String name, String condition, String img) {
        this.name = name;
        this.condition = condition;
        this.img = img;
    }

    public Plan() {
    }

    public ArrayList<Dayrecipe> getDayrecipes() {
        return dayrecipes;
    }

    public void setDayrecipes(ArrayList<Dayrecipe> dayrecipes) {
        this.dayrecipes = dayrecipes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
