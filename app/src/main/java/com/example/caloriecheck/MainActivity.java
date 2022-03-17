package com.example.caloriecheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static int SPlASH_SCREEN = 5000;
    SharedPreferences sharedpreferences;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedpreferences = getSharedPreferences("infomation", MODE_PRIVATE);
        boolean check = sharedpreferences.getBoolean("checkfull",false);

        if(check == false){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(MainActivity.this,Hello.class);
                    startActivity(i);

                }
            },SPlASH_SCREEN);
        }else if(check == true){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(MainActivity.this,Home.class);
                    startActivity(i);

                }
            },SPlASH_SCREEN);
        }







    }
}