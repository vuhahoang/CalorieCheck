package com.example.caloriecheck.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Dayrecipe implements Serializable {
    String img,day;
    ArrayList<String> recipeModels;

    public Dayrecipe(String img, String day, ArrayList<String> recipeModels) {
        this.img = img;
        this.day = day;
        this.recipeModels = recipeModels;
    }

    public Dayrecipe() {
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public ArrayList<String> getRecipeModels() {
        return recipeModels;
    }

    public void setRecipeModels(ArrayList<String> recipeModels) {
        this.recipeModels = recipeModels;
    }
}
