package com.example.caloriecheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caloriecheck.Model.FoodModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CustomFood extends AppCompatActivity {
    EditText EdName,EdCalorie,EdCarbs,EdProtein,EdFat;
    Button add;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    FoodModel foodModel;
    TextView title;
    ImageView imgback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_food);
        EdName = findViewById(R.id.Ednamecustomfood);
        EdCalorie = findViewById(R.id.edCalorieCustomfood);
        EdCarbs = findViewById(R.id.EdCarbsCustomFoods);
        EdProtein = findViewById(R.id.EdproteinCustomfood);
        EdFat = findViewById(R.id.EdFatCustomfood);
        add = findViewById(R.id.addCustomfood);
        title = findViewById(R.id.tvtitlecustomfood);
        imgback = findViewById(R.id.imgbackincustomfood);
        Intent i = getIntent();
        title.setText(i.getStringExtra("title"));

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!EdName.getText().toString().isEmpty() | !EdCalorie.getText().toString().isEmpty() | !EdCarbs.getText().toString().isEmpty() | !EdProtein.getText().toString().isEmpty() | !EdFat.getText().toString().isEmpty() ){
                    rootNode = FirebaseDatabase.getInstance();
                    reference = rootNode.getReference("FoodCustoms");
                    foodModel = new FoodModel(EdName.getText().toString().trim(),EdCalorie.getText().toString().trim(),EdCarbs.getText().toString().trim(),EdProtein.getText().toString().trim(),EdFat.getText().toString().trim(),"1");
                    reference.child(EdName.getText().toString().trim()).setValue(foodModel);
                    Toast.makeText(CustomFood.this,"Đã thêm",Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }else {
                    Toast.makeText(CustomFood.this,"Không được để chống ô nào",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}