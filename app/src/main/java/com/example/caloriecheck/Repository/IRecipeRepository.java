package com.example.caloriecheck.Repository;

import com.example.caloriecheck.Model.RecipeModel;

import java.util.ArrayList;
import java.util.List;

public interface IRecipeRepository {
    ArrayList<RecipeModel> getRecipeList(int size, List<String> idRecipes);
}
