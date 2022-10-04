package com.example.caloriecheck.Module;

import com.example.caloriecheck.Repository.INoteRepository;
import com.example.caloriecheck.Repository.IRecipeRepository;
import com.example.caloriecheck.Repository.Imp.NoteRepositoryImp;
import com.example.caloriecheck.Repository.Imp.RecipeRepositoryImp;
import com.google.firebase.database.FirebaseDatabase;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class NoteModule {
    @Provides
    INoteRepository provideNoteRepo(FirebaseDatabase firebaseDatabase){
        return new NoteRepositoryImp(firebaseDatabase);
    }
}
