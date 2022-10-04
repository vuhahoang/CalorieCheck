package com.example.caloriecheck.Repository.Imp;

import android.content.SharedPreferences;

import com.example.caloriecheck.Model.Diary;
import com.example.caloriecheck.Repository.INoteRepository;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Inject;

public class NoteRepositoryImp implements INoteRepository {
    private final FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Inject
    public NoteRepositoryImp(FirebaseDatabase firebaseDatabase) {
        this.firebaseDatabase = firebaseDatabase;
        databaseReference = firebaseDatabase.getReference("User");
    }

    @Override
    public void addNote(SharedPreferences sharedPreferences, Diary diary) {
        String username = sharedPreferences.getString("username","");
        databaseReference.child(username).child("Notes").child(diary.getDay() +diary.getMonth()).setValue(diary);
    }

    @Override
    public FirebaseDatabase getFirebaseDatabase() {
        return this.firebaseDatabase;
    }
}
