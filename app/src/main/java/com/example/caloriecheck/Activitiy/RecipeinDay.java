package com.example.caloriecheck.Activitiy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.caloriecheck.CustomApdapter.FoodAdapterVer;
import com.example.caloriecheck.Model.RecipeModel;
import com.example.caloriecheck.R;
import com.example.caloriecheck.Repository.IRecipeRepository;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RecipeinDay extends AppCompatActivity {
    ImageView imgtitle ;
    TextView tvtitle;
    RecyclerView rc;
    ArrayList<RecipeModel> recipeModels;
    FoodAdapterVer foodAdapterVer;
    @Inject
    IRecipeRepository recipeRepository;
//    FirebaseDatabase firebaseDatabase;
//    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipein_day);
        imgtitle = findViewById(R.id.imgRecipeinDay);
        tvtitle = findViewById(R.id.tvnameRecipeinday);
        rc = findViewById(R.id.rcRecipeinDay);

        Intent i = getIntent();
        tvtitle.setText(i.getStringExtra("tvtitle"));
        Picasso.get().load(i.getStringExtra("imgtitle")).into( imgtitle);
        recipeModels = new ArrayList<>();
        ArrayList<String> recipeid =  i.getStringArrayListExtra("idrecipe");
        foodAdapterVer = new FoodAdapterVer(recipeModels,this);

        for(int j = 0;j<recipeid.size();j++){
            DatabaseReference databaseReference = recipeRepository.getDatabaseReference().child(recipeid.get(j));
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull  DataSnapshot snapshot) {
                    RecipeModel recipeModel = snapshot.getValue(RecipeModel.class);
                    recipeModels.add(recipeModel);
                    foodAdapterVer.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull  DatabaseError error) {

                }
            });
        }

        rc.setAdapter(foodAdapterVer);
    }
}