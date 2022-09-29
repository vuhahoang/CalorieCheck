package com.example.caloriecheck.Activitiy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.caloriecheck.R;

public class Hello extends AppCompatActivity {
    Button BtnTang ,BtnGiam,BtnGiu;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        BtnGiam = findViewById(R.id.btnGiamCan);
        BtnTang = findViewById(R.id.btnTangCB);
        BtnGiu = findViewById(R.id.btnDuyTri);
        sharedPreferences = getSharedPreferences("infomation",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        BtnGiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("nhucau",-500);
                editor.commit();
                Intent i = new Intent(Hello.this,questionone.class);
                startActivity(i);
            }
        });

        BtnTang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("nhucau",500);
                editor.commit();
                Intent i = new Intent(Hello.this,questionone.class);
                startActivity(i);

            }
        });

        BtnGiu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("nhucau",0);
                editor.commit();
                Intent i = new Intent(Hello.this,questionone.class);
                startActivity(i);

            }
        });


    }
}