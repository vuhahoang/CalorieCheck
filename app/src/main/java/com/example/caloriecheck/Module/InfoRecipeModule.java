package com.example.caloriecheck.Module;

import com.example.caloriecheck.Repository.I_InfoRecipeRepository;
import com.example.caloriecheck.Repository.Imp.InfoRecipeRepositoryImp;
import com.google.firebase.database.FirebaseDatabase;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class InfoRecipeModule {
    @Provides
    I_InfoRecipeRepository provideInfoRecipeRepo(FirebaseDatabase firebaseDatabase){
        return new InfoRecipeRepositoryImp(firebaseDatabase);
    }
}
