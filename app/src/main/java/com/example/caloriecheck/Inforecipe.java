package com.example.caloriecheck;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.caloriecheck.CustomApdapter.IngredientsAdapter;
import com.example.caloriecheck.Model.Ingredients;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Inforecipe extends AppCompatActivity {
  TextView tvname,tvkcal,tvtime,tvlever;
  RecyclerView rcingredients;
  IngredientsAdapter ingredientsAdapter;
  ArrayList<Ingredients> ingredients;
  FirebaseDatabase firebaseDatabase;
  DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inforecipe);
        tvname = findViewById(R.id.tvnamerecipe);
        tvkcal = findViewById(R.id.tvkcalrecipeinfo);
        tvtime = findViewById(R.id.tvtimerecipeinfo);
        tvlever = findViewById(R.id.tvleverrecipeinfo);
        rcingredients = findViewById(R.id.rcingredients);
        ingredients = new ArrayList<>();
        ingredientsAdapter = new IngredientsAdapter(ingredients,Inforecipe.this);
        rcingredients.setAdapter(ingredientsAdapter);
        Intent i = getIntent();
        int id = i.getIntExtra("keyid",0);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Recipe").child(id+"").child("ingredients");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Ingredients ingredient = dataSnapshot.getValue(Ingredients.class);
                    ingredients.add(ingredient);
                    ingredientsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });

    }
}