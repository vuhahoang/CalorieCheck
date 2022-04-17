package com.example.caloriecheck.Model;

public class Ingredients {
    String name,measures,image;

    public Ingredients() {
    }

    public Ingredients(String name, String measures, String image) {
        this.name = name;
        this.measures = measures;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeasures() {
        return measures;
    }

    public void setMeasures(String measures) {
        this.measures = measures;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
