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
public class questionone extends AppCompatActivity {
    Button  it,nhe,vua,sieng,exsieng;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cccnst);
        it =findViewById(R.id.noneex);
        nhe = findViewById(R.id.littleex);
        vua = findViewById(R.id.mediumex);
        sieng = findViewById(R.id.largeex);
        exsieng = findViewById(R.id.extraex);
        sharedpreferences = getSharedPreferences("infomation",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        Intent intent = getIntent();
        int nhucau = intent.getIntExtra("nhucau",0);

        it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putFloat("R", (float) 1.2);
                editor.commit();
                Intent j = new Intent(questionone.this,questiontwo.class);
                startActivity(j);
            }
        });

        nhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putFloat("R", (float) 1.375);
                editor.commit();
                Intent j = new Intent(questionone.this,questiontwo.class);
                startActivity(j);
            }
        });

        vua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putFloat("R", (float) 1.55);
                editor.commit();
                Intent j = new Intent(questionone.this,questiontwo.class);
                startActivity(j);
            }
        });

        sieng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putFloat("R", (float) 1.725);
                editor.commit();
                Intent j = new Intent(questionone.this,questiontwo.class);
                startActivity(j);
            }
        });

        exsieng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putFloat("R", (float) 1.9);
                editor.commit();
                Intent j = new Intent(questionone.this,questiontwo.class);
                startActivity(j);
            }
        });
    }
}