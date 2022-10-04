package com.example.caloriecheck.Module;

import com.example.caloriecheck.Repository.IRecipeAdminRepository;
import com.example.caloriecheck.Repository.I_InfoRecipeRepository;
import com.example.caloriecheck.Repository.Imp.InfoRecipeRepositoryImp;
import com.example.caloriecheck.Repository.Imp.RecipeAdminRepositoryImp;
import com.google.firebase.database.FirebaseDatabase;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class RecipeAdminModule {
    @Provides
    IRecipeAdminRepository provideRecipeAdminRepo(FirebaseDatabase firebaseDatabase){
        return new RecipeAdminRepositoryImp(firebaseDatabase);
    }
}
