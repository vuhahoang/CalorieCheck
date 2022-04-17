package com.example.caloriecheck.Model;

import java.util.ArrayList;

public class RecipeModel {
  int calorie,carb,fat,id,protein,rim,love;
  String image,introduce,title,type,tag;
  ArrayList<Ingredients> ingredients;

    public RecipeModel() {
    }

    public RecipeModel(int calorie, int carb, int fat, int id, int protein, int rim, int love, String image, String introduce, String title, String type, String tag, ArrayList<Ingredients> ingredients) { 
        this.calorie = calorie;
        this.carb = carb;
        this.fat = fat;
        this.id = id;
        this.protein = protein;
        this.rim = rim;
        this.love = love;
        this.image = image;
        this.introduce = introduce;
        this.title = title;
        this.type = type;
        this.tag = tag;
        this.ingredients = ingredients;
    }



    public int getLove() {
        return love;
    }

    public void setLove(int love) {
        this.love = love;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    public int getCarb() {
        return carb;
    }

    public void setCarb(int carb) {
        this.carb = carb;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getRim() {
        return rim;
    }

    public void setRim(int rim) {
        this.rim = rim;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Ingredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }
}
