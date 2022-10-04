package com.example.caloriecheck.Repository.Imp;

import com.example.caloriecheck.Repository.IFoodRepository;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FoodRepositoryImp implements IFoodRepository {
    private final FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public FoodRepositoryImp(FirebaseDatabase firebaseDatabase) {
        this.firebaseDatabase = firebaseDatabase;
        this.databaseReference = firebaseDatabase.getReference("Foods");
    }

    @Override
    public DatabaseReference getFoodDb() {
        return databaseReference;
    }
}
