package com.example.caloriecheck.Repository.Imp;

import com.example.caloriecheck.Repository.I_InfoRecipeRepository;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Inject;

public class InfoRecipeRepositoryImp implements I_InfoRecipeRepository {
    private final FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Inject
    public InfoRecipeRepositoryImp(FirebaseDatabase firebaseDatabase) {
        this.firebaseDatabase = firebaseDatabase;
    }

    @Override
    public FirebaseDatabase getFirebaseDatabase() {
        return this.firebaseDatabase;
    }

    @Override
    public DatabaseReference getDatabaseReference() {
            return firebaseDatabase.getReference("Recipe");

    }
}
