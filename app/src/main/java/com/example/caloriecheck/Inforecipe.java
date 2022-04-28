package com.example.caloriecheck;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caloriecheck.CustomApdapter.IngredientsAdapter;
import com.example.caloriecheck.Model.FoodModel;
import com.example.caloriecheck.Model.Ingredients;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Inforecipe extends AppCompatActivity {
  TextView tvname,tvkcal,tvtime,tvlever,tvintroduct;
  RecyclerView rcingredients;
  IngredientsAdapter ingredientsAdapter;
  ArrayList<Ingredients> ingredients;
  FirebaseDatabase firebaseDatabase;
  DatabaseReference databaseReference,reference;
  ImageView imgtitle;
  Button btnadd;
  SharedPreferences sharedPreferences,sharedPreferencesdata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inforecipe);
        tvname = findViewById(R.id.tvnamerecipe);
        tvkcal = findViewById(R.id.tvkcalrecipeinfo);
        tvtime = findViewById(R.id.tvtimerecipeinfo);
        tvlever = findViewById(R.id.tvleverrecipeinfo);
        imgtitle = findViewById(R.id.imgtitleinforecipe);
        tvintroduct = findViewById(R.id.tvintroductinforecipe);
        rcingredients = findViewById(R.id.rcingredients);
        btnadd = findViewById(R.id.btnaddrecipetodiary);
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

        reference = firebaseDatabase.getReference("Recipe").child(id+"");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                String img = snapshot.child("image").getValue(String.class);
                String name = snapshot.child("title").getValue(String.class);
                String intro = snapshot.child("introduce").getValue(String.class);
                int calorie = snapshot.child("calorie").getValue(Integer.class);
                int time = snapshot.child("rim").getValue(Integer.class);
                tvkcal.setText(calorie + " kcal");
                tvname.setText(name);
                tvtime.setText(time+" phút");
                tvintroduct.setText(intro);
                Picasso.get().load(img).into(imgtitle);
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull  DataSnapshot snapshot) {
                        String buaan;
                        int calorie = snapshot.child("calorie").getValue(Integer.class);
                        int carb = snapshot.child("carb").getValue(Integer.class);
                        int protein = snapshot.child("protein").getValue(Integer.class);
                        int fat = snapshot.child("fat").getValue(Integer.class);
                        String name = snapshot.child("title").getValue(String.class);
                        FoodModel foodModel = new FoodModel(name,calorie+"",protein+"",carb+"",fat+"","1 Khẩu phần");
                        Gson gson = new Gson();
                        String strJsonUser = gson.toJson(foodModel);
                        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                        SimpleDateFormat time = new SimpleDateFormat("hhaaa");
                        String date2 = sdf.format(new Date());
                        String datetime = time.format(new Date());

                        if(datetime.equals("00AM") || datetime.equals("01AM") || datetime.equals("02AM") || datetime.equals("03AM") || datetime.equals("04AM") || datetime.equals("05AM") || datetime.equals("06AM") || datetime.equals("07AM") || datetime.equals("08AM") || datetime.equals("09AM")){
                            buaan = "Bữa sáng";
                        }else if(datetime.equals("10AM") || datetime.equals("11AM") || datetime.equals("00PM") || datetime.equals("01PM")){
                            buaan = "Bữa trưa";
                        }else if(datetime.equals("02PM") || datetime.equals("03PM") || datetime.equals("04PM") || datetime.equals("05PM")){
                            buaan = "Bữa phụ";
                        }else {
                            buaan = "Bữa tối";
                        }



                        sharedPreferences = getSharedPreferences(buaan+date2, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        String checkexits = sharedPreferences.getString(foodModel.getName(),"");

                        if(checkexits.equals("")){
                            editor.putString(foodModel.getName(),strJsonUser);
                            editor.commit();
                        }else {
                            Toast.makeText(Inforecipe.this,"Đã thêm thức ăn này rồi",Toast.LENGTH_SHORT).show();
                        }

                        sharedPreferencesdata = getSharedPreferences("data"+date2,Context.MODE_PRIVATE);
                        SharedPreferences.Editor editordata = sharedPreferencesdata.edit();
                        Float proteinht =  sharedPreferencesdata.getFloat(buaan+"protein",0);
                        Float calorieht = sharedPreferencesdata.getFloat(buaan +"calorie",0);
                        Float fatht = sharedPreferencesdata.getFloat(buaan+"fat",0);
                        Float carbht = sharedPreferencesdata.getFloat(buaan +"carb",0);
                        editordata.putFloat(buaan+"protein",Float.parseFloat(foodModel.getProtein()) + proteinht);
                        editordata.putFloat(buaan+"calorie",Float.parseFloat(foodModel.getCalorie()) + calorieht);
                        editordata.putFloat(buaan+"fat",Float.parseFloat(foodModel.getFat()) + fatht);
                        editordata.putFloat(buaan+"carb",Float.parseFloat(foodModel.getCarb()) + carbht);
                        editordata.commit();
                        Toast.makeText(Inforecipe.this,"Đã thêm " + foodModel.getGram() + " " + foodModel.getName(), Toast.LENGTH_SHORT).show();



                    }

                    @Override
                    public void onCancelled(@NonNull  DatabaseError error) {

                    }
                });
            }
        });



    }
}