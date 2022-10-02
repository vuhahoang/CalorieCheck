package com.example.caloriecheck.Activitiy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.caloriecheck.CustomApdapter.FoodAdapterCaloFS;
import com.example.caloriecheck.Model.FoodModel;
import com.example.caloriecheck.R;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FoodSave extends AppCompatActivity {
    RecyclerView rc;
    TextView tvName,tvCarb,tvPro,tvFat,tvCalo,tvCarb1,tvPro1,tvFat1,tvCalo1,title;
    ArrayList<FoodModel> foodModels;
    FoodAdapterCaloFS foodAdapterCalo;
    SharedPreferences sharedPreferences,sharedPreferencesdata;
    Button btnadd;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_save);
        tvCarb = findViewById(R.id.tvcarbfoodsave);
        tvPro = findViewById(R.id.tvproteinfoodsave);
        tvFat = findViewById(R.id.tvfatfoodsave);
        tvCalo = findViewById(R.id.tvcaloriefoodsave);
        tvCarb1 = findViewById(R.id.tvcarbfoodsave1);
        tvPro1 = findViewById(R.id.tvproteinfoodsave1);
        tvFat1 = findViewById(R.id.tvfatfoodsave1);
        tvCalo1 = findViewById(R.id.tvcaloriefoodsave1);
        title = findViewById(R.id.tvtitlefoodsave);
        rc = findViewById(R.id.rccalofoodsave);
        btnadd = findViewById(R.id.addfoodinfoodsave);
        back = findViewById(R.id.imgbackfoodsave);
        Intent i = getIntent();
        foodModels = new ArrayList<>();

        String buaan = i.getStringExtra("title");
        title.setText(i.getStringExtra("title"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String date2 = sdf.format(new Date());
        sharedPreferences = getSharedPreferences(buaan+date2,MODE_PRIVATE);
        sharedPreferencesdata = getSharedPreferences("data" + date2,MODE_PRIVATE);
        Float calo = sharedPreferencesdata.getFloat(buaan + "calorie",0);
        Float pro = sharedPreferencesdata.getFloat(buaan + "protein",0);
        Float carb = sharedPreferencesdata.getFloat(buaan + "carb",0);
        Float fat = sharedPreferencesdata.getFloat(buaan + "fat",0);
        tvCarb.setText(carb+"");
        tvCarb1.setText(carb+"");
        tvCalo.setText(calo+"");
        tvCalo1.setText(calo+"");
        tvPro.setText(pro + "");
        tvPro1.setText(pro+"");
        tvFat.setText(fat+"");
        tvFat1.setText(fat+"");
        Map<String,?> keys = sharedPreferences.getAll();
        for(Map.Entry<String,?> entry : keys.entrySet()){
            Log.d("map values",entry.getKey() + ": " + entry.getValue().toString());
            Gson gson = new Gson();
            FoodModel foodModel = gson.fromJson(entry.getValue().toString(),FoodModel.class);
            foodModels.add(foodModel);
        }
        foodAdapterCalo = new FoodAdapterCaloFS(this,foodModels,i);
        rc.setAdapter(foodAdapterCalo);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FoodSave.this,Add_Food_Activity.class);
                i.putExtra("title",buaan);
                startActivity(i);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}