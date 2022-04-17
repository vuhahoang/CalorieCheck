package com.example.caloriecheck.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caloriecheck.CustomApdapter.FoodAdapterHor;
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

public class AllFoodTabFragment extends Fragment {
    ArrayList<RecipeModel> recipeModelspopular,recipeModelscouter,recipeModelsnew;
    FoodAdapterHor foodAdapterHorpopular,foodAdapterHorcouter,foodAdapterHornew;
    RecyclerView rcphobien,rckiemsoat,rcmonmoi;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.all_tab_fragment, container, false);
        rcphobien = view.findViewById(R.id.rcphobieninall);
        rckiemsoat = view.findViewById(R.id.rckiemsoatinall);
        rcmonmoi = view.findViewById(R.id.rcnewfoodinall);
        recipeModelspopular = new ArrayList<>();
        recipeModelscouter = new ArrayList<>();
        recipeModelsnew = new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Recipe");
        foodAdapterHorpopular = new FoodAdapterHor(recipeModelspopular,getContext());
        foodAdapterHorcouter = new FoodAdapterHor(recipeModelscouter,getContext());
        foodAdapterHornew = new FoodAdapterHor(recipeModelsnew,getContext());
        rcphobien.setAdapter(foodAdapterHorpopular);
        rckiemsoat.setAdapter(foodAdapterHorcouter);
        rcmonmoi.setAdapter(foodAdapterHornew);
        foodAdapterHorpopular.notifyDataSetChanged();
        foodAdapterHorcouter.notifyDataSetChanged();
        foodAdapterHornew.notifyDataSetChanged();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    RecipeModel recipeModel = dataSnapshot.getValue(RecipeModel.class);
                    if(recipeModel.getTag().equals("new")){
                        recipeModelsnew.add(recipeModel);
                    }else if(recipeModel.getTag().equals("popular")){
                        recipeModelspopular.add(recipeModel);
                    }else if(recipeModel.getCalorie() < 300){
                        recipeModelscouter.add(recipeModel);
                    }
                    foodAdapterHorpopular.notifyDataSetChanged();
                    foodAdapterHorcouter.notifyDataSetChanged();
                    foodAdapterHornew.notifyDataSetChanged();


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
}
