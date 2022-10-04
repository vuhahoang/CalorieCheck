package com.example.caloriecheck.Repository.Imp;

import com.example.caloriecheck.Model.RecipeModel;
import com.example.caloriecheck.Repository.IRecipeAdminRepository;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import javax.inject.Inject;

public class RecipeAdminRepositoryImp implements IRecipeAdminRepository {
    private final FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Inject
    public RecipeAdminRepositoryImp(FirebaseDatabase firebaseDatabase) {
        this.firebaseDatabase = firebaseDatabase;
        this.databaseReference = firebaseDatabase.getReference("Recipe");
    }

    @Override
    public void addRecipe(RecipeModel recipeModel) {
        databaseReference.child(recipeModel.getId()+"").setValue(recipeModel);
    }

    @Override
    public FirebaseDatabase getDatabaseInstance() {
        return this.firebaseDatabase;
    }

}
