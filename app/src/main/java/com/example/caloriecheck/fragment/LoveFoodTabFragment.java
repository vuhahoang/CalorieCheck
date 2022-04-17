package com.example.caloriecheck.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.caloriecheck.CustomApdapter.FoodAdapterVer;
import com.example.caloriecheck.Model.FoodModel;
import com.example.caloriecheck.Model.RecipeModel;
import com.example.caloriecheck.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class LoveFoodTabFragment extends Fragment {
    ArrayList<RecipeModel> recipeModels;
    FoodAdapterVer foodAdapterVer;
    RecyclerView rclove;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

  

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_love_food_tab, container, false);
        rclove = view.findViewById(R.id.rclove);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Recipe");
        recipeModels = new ArrayList<>();
        foodAdapterVer = new FoodAdapterVer(recipeModels,getContext());
        rclove.setAdapter(foodAdapterVer);
        foodAdapterVer.notifyDataSetChanged();


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    RecipeModel recipeModel = dataSnapshot.getValue(RecipeModel.class);
                    if(recipeModel.getTag().equals("love")){
                        recipeModels.add(recipeModel);
                    }
                    foodAdapterVer.notifyDataSetChanged();


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        return view;
    }
}