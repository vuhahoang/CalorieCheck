package com.example.caloriecheck.Repository;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public interface I_InfoRecipeRepository {
    FirebaseDatabase getFirebaseDatabase();

    DatabaseReference getDatabaseReference();
}
