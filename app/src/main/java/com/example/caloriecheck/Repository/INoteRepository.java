package com.example.caloriecheck.Repository;

import android.content.SharedPreferences;

import com.example.caloriecheck.Model.Diary;
import com.google.firebase.database.FirebaseDatabase;

public interface INoteRepository {
    void addNote(SharedPreferences sharedPreferences, Diary diary);

    FirebaseDatabase getFirebaseDatabase();
}
