package com.example.caloriecheck.Repository;

import com.example.caloriecheck.Model.RecipeModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public interface IRecipeRepository {
    ArrayList<RecipeModel> getRecipeList(int size, List<String> idRecipes);

    FirebaseDatabase getFirebaseDatabase();

    DatabaseReference getDatabaseReference();
}
