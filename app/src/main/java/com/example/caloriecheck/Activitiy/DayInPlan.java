package com.example.caloriecheck.Activitiy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;

import com.example.caloriecheck.CustomApdapter.DayPlan;
import com.example.caloriecheck.Model.Dayrecipe;
import com.example.caloriecheck.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DayInPlan extends AppCompatActivity {
    RecyclerView rc;
    DayPlan dayPlanAdapter;
    ArrayList<Dayrecipe>  dayrecipes;
    ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_in_plan);
        rc = findViewById(R.id.rcDayinPlan);
        img = findViewById(R.id.imgDayinplan);
        dayrecipes = new ArrayList<>();
        Intent i = getIntent();
        String imgload =  i.getStringExtra("imgtitle");

        Picasso.get().load(imgload).into(img);
        img.setScaleType(ImageView.ScaleType.CENTER_CROP);

        Bundle args = i.getBundleExtra("BUNDLE");
        dayrecipes = (ArrayList<Dayrecipe>) args.getSerializable("Arraylistdayrecipe");

        dayPlanAdapter = new DayPlan(dayrecipes,this);
        rc.setAdapter(dayPlanAdapter);
    }
}