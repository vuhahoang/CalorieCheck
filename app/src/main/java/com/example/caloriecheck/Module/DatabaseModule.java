package com.example.caloriecheck.Module;

import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {
        @Provides
        @Singleton
        FirebaseDatabase provideDB(){
            return FirebaseDatabase.getInstance();
        }
}
