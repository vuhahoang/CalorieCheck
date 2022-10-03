package com.example.caloriecheck.Activitiy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import com.example.caloriecheck.R;

public class questiontwo extends AppCompatActivity {
    NumberPicker numberPicker;
    Button nexttwo;
    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questiontwo);
        numberPicker = findViewById(R.id.numberPicker2);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(500);
        nexttwo = findViewById(R.id.nexttwo);
        Intent intent = getIntent();
        double r = intent.getDoubleExtra("R",0);
        int nhucau = intent.getIntExtra("nhucau",0);
        sharedpreferences = getSharedPreferences("infomation",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();




        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                int can = newVal;
                nexttwo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editor.putInt("can",can);
                        editor.putInt("canbd",can);
                        editor.commit();
                        Intent j = new Intent(questiontwo.this,questionthree.class);
                        startActivity(j);
                    }
                });
            }
        });




    }
}