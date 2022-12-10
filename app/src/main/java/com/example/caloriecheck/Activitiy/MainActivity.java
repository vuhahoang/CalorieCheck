package com.example.caloriecheck.Activitiy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.caloriecheck.R;
import com.example.caloriecheck.services.StepCounter;

public class MainActivity extends AppCompatActivity {
    private static int SPlASH_SCREEN = 5000;
    SharedPreferences sharedpreferences;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedpreferences = getSharedPreferences("infomation", MODE_PRIVATE);
        boolean check = sharedpreferences.getBoolean("checkfull",false);
        Preferences.setServiceRun(this, false);
        Intent serviceIntent = new Intent(this, StepCounter.class);
        startService(serviceIntent);

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