package com.example.caloriecheck.Activitiy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.caloriecheck.R;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class questionfive extends AppCompatActivity {
    Button btnNam, btnNu;
    private final int nam = 5;
    private final int nu = -161;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionfive);

        btnNam =findViewById(R.id.btnnam);
        btnNu = findViewById(R.id.btnnu);
        sharedpreferences = getSharedPreferences("infomation",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();



        btnNam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("K",nam);
                editor.commit();
                Intent j = new Intent(questionfive.this,questionsix.class);
                startActivity(j);
            }
        });
        btnNu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("K",nu);
                editor.commit();
                Intent j = new Intent(questionfive.this,questionsix.class);
                startActivity(j);
            }
        });
    }
}