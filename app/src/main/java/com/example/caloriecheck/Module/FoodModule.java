package com.example.caloriecheck.Module;

import com.example.caloriecheck.Repository.IFoodRepository;
import com.example.caloriecheck.Repository.Imp.FoodRepositoryImp;
import com.google.firebase.database.FirebaseDatabase;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class FoodModule {
    @Provides
    IFoodRepository provideFoodRepo(FirebaseDatabase firebaseDatabase){
        return new FoodRepositoryImp(firebaseDatabase);
    }
}
