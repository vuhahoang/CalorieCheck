package com.example.caloriecheck.Repository;

import com.google.firebase.database.DatabaseReference;

public interface IFoodRepository {
    DatabaseReference getFoodDb();
}
