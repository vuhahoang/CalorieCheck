package com.example.caloriecheck.Repository;

import com.example.caloriecheck.Model.FoodModel;
import com.google.firebase.database.Query;

public interface ICustomFoodRepository {
    void addFood(FoodModel foodModel);


    Query search(String searchField);
}
