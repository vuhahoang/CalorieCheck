package com.example.caloriecheck.Repository;

import com.example.caloriecheck.Model.RecipeModel;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public interface IRecipeAdminRepository {
    void addRecipe(RecipeModel recipeModel);

    FirebaseDatabase getDatabaseInstance();
}
