package com.example.caloriecheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

public class questionfour extends AppCompatActivity {
    NumberPicker numberPicker;
    int chieucao;
    Button nextfour;
    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionfour);
        numberPicker = findViewById(R.id.numberPicker4);
        numberPicker.setMinValue(50);
        numberPicker.setMaxValue(300);
        nextfour = findViewById(R.id.nextfour);
        sharedpreferences = getSharedPreferences("infomation",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();




        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                chieucao = newVal;
                nextfour.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editor.putInt("chieucao",chieucao);
                        editor.commit();
                        Intent j = new Intent(questionfour.this,questionfive.class);
                        startActivity(j);
                    }
                });
            }
        });


    }
}