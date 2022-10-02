package com.example.caloriecheck.Repository.Imp;

import androidx.annotation.NonNull;

import com.example.caloriecheck.Model.RecipeModel;
import com.example.caloriecheck.Repository.IRecipeRepository;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RecipeRepositoryImp implements IRecipeRepository {
    private final FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Inject
    public RecipeRepositoryImp(FirebaseDatabase firebaseDatabase) {
        this.firebaseDatabase = firebaseDatabase;
    }

    @Override
    public ArrayList<RecipeModel> getRecipeList(int size, List<String> idRecipes) {
        databaseReference = firebaseDatabase.getReference("Recipe");
        ArrayList<RecipeModel> recipeModelList = new ArrayList<>();
        for(int j = 0;j<size;j++){
            databaseReference = databaseReference.child(idRecipes.get(j));
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    RecipeModel recipeModel = snapshot.getValue(RecipeModel.class);
                    recipeModelList.add(recipeModel);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        return recipeModelList;
    }
}
