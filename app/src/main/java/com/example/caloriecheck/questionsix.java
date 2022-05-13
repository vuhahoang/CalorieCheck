package com.example.caloriecheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import java.text.SimpleDateFormat;
import java.util.Date;

public class questionsix extends AppCompatActivity {

    NumberPicker date,month,year;
    Button nextsix;
    SharedPreferences sharedpreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionsix);
        date = findViewById(R.id.numberPickerDate);
        month = findViewById(R.id.numberPickerMonth);
        year = findViewById(R.id.numberPickerYear);
        nextsix = findViewById(R.id.addfoodinfoodinfo);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String getyear = sdf.format(new Date());
        sharedpreferences = getSharedPreferences("infomation",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();



        date.setMinValue(1);
        date.setMaxValue(31);

        year.setMinValue(1980);
        year.setMaxValue(2021);

        month.setMinValue(1);
        month.setMaxValue(12);
        String[] str = {"Tháng 1","Tháng 2","Tháng 3","Tháng 4","Tháng 5","Tháng 6","Tháng 7","Tháng 8","Tháng 9","Tháng 10","Tháng 11","Tháng 12"};
        month.setDisplayedValues(str);




        year.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                int tuoi = Integer.parseInt(getyear) - newVal;
                nextsix.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editor.putInt("tuoi",tuoi);
                        editor.putBoolean("checkfull",true);
                        editor.commit();
                        Intent i = new Intent(questionsix.this,Home.class);
                        startActivity(i);
                    }
                });
            }
        });







    }
}