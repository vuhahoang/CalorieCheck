package com.example.caloriecheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

public class questionthree extends AppCompatActivity {
    NumberPicker numberPicker;
    int canmoi;
    Button nextthree;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionthree);
        numberPicker = findViewById(R.id.numberPicker3);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(500);
        nextthree = findViewById(R.id.nextthere);
        sharedpreferences = getSharedPreferences("infomation",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                canmoi = newVal;
                nextthree.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editor.putInt("canmoi",canmoi);
                        editor.commit();
                        Intent j = new Intent(questionthree.this,questionfour.class);
                        startActivity(j);
                    }
                });
            }
        });


    }
}