package com.example.caloriecheck.Model;

public class FoodModel {
    private int img;
    private String name,lever,time,calorie,protein,carb,fat,gram;

    public FoodModel() {
    }

    public FoodModel(String name, String calorie, String protein, String carb, String fat,String gram ) {
        this.name = name;
        this.calorie = calorie;
        this.protein = protein;
        this.carb = carb;
        this.fat = fat;
        this.gram = gram;
    }

    public FoodModel(String name, String calorie, String protein, String carb, String fat ) {
        this.name = name;
        this.calorie = calorie;
        this.protein = protein;
        this.carb = carb;
        this.fat = fat;

    }

    public FoodModel(int img, String calorie, String name, String lever, String time) {
        this.img = img;
        this.calorie = calorie;
        this.name = name;
        this.lever = lever;
        this.time = time;
    }

    public String getGram() {
        return gram;
    }

    public void setGram(String gram) {
        this.gram = gram;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getCarb() {
        return carb;
    }

    public void setCarb(String carb) {
        this.carb = carb;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getCalorie() {
        return calorie;
    }

    public void setCalorie(String calorie) {
        this.calorie = calorie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLever() {
        return lever;
    }

    public void setLever(String lever) {
        this.lever = lever;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "FoodModel{" +
                "name='" + name + '\'' +
                ", calorie='" + calorie + '\'' +
                ", protein='" + protein + '\'' +
                ", carb='" + carb + '\'' +
                ", fat='" + fat + '\'' +
                ", gram='" + gram + '\'' +
                '}';
    }
}
