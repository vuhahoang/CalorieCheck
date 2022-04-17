package com.example.caloriecheck.Model;

public class Plan {

    String name,condition,img;

    public Plan(String name, String condition, String img) {
        this.name = name;
        this.condition = condition;
        this.img = img;
    }

    public Plan() {
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
