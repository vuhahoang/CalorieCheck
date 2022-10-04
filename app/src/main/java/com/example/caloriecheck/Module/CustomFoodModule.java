package com.example.caloriecheck.Module;

import com.example.caloriecheck.Repository.ICustomFoodRepository;
import com.example.caloriecheck.Repository.Imp.CustomFoodRepositoryImp;
import com.google.firebase.database.FirebaseDatabase;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class CustomFoodModule {
    @Provides
    ICustomFoodRepository provideCustomFoodRepo(FirebaseDatabase firebaseDatabase){
        return new CustomFoodRepositoryImp(firebaseDatabase);
    }
}
