package com.example.caloriecheck.Model;

public class User {
    private String Name,Username,Email,Password;
    private int Age,Height,Weight,Target,WeightTarget,K,WeightStarts;
    private double R;

    public User(String name, String username, String email,String password, int age, int height, int weight, int target, int weightTarget, double r,int k, int weightstarts) {
        Name = name;
        Username = username;
        Email = email;
        Password = password;
        K = k;
        Age = age;
        Height = height;
        Weight = weight;
        Target = target;
        WeightTarget = weightTarget;
        R = r;
        WeightStarts = weightstarts;
    }


    public int getWeightStarts() {
        return WeightStarts;
    }

    public void setWeightStarts(int weightStarts) {
        WeightStarts = weightStarts;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getK() {
        return K;
    }

    public void setK(int k) {
        K = k;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public int getHeight() {
        return Height;
    }

    public void setHeight(int height) {
        Height = height;
    }

    public int getWeight() {
        return Weight;
    }

    public void setWeight(int weight) {
        Weight = weight;
    }

    public int getTarget() {
        return Target;
    }

    public void setTarget(int target) {
        Target = target;
    }

    public int getWeightTarget() {
        return WeightTarget;
    }

    public void setWeightTarget(int weightTarget) {
        WeightTarget = weightTarget;
    }

    public double getR() {
        return R;
    }

    public void setR(double r) {
        R = r;
    }
}
