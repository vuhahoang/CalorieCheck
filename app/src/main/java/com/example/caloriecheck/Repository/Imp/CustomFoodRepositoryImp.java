package com.example.caloriecheck.Repository.Imp;

import com.example.caloriecheck.Model.FoodModel;
import com.example.caloriecheck.Repository.ICustomFoodRepository;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import javax.inject.Inject;

public class CustomFoodRepositoryImp implements ICustomFoodRepository {
    private final FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Inject
    public CustomFoodRepositoryImp(FirebaseDatabase firebaseDatabase) {
        this.firebaseDatabase = firebaseDatabase;
        this.databaseReference = firebaseDatabase.getReference("FoodCustoms");
    }


    @Override
    public void addFood(FoodModel foodModel) {
        databaseReference.child(foodModel.getName()).setValue(foodModel);
    }

    @Override
    public Query search(String searchField) {
        DatabaseReference databaseReference = firebaseDatabase.getReference("FoodCustoms");
        Query check = databaseReference.orderByChild("name").equalTo(searchField);
        return check;
    }
}
