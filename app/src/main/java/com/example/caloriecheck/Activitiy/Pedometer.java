package com.example.caloriecheck.Activitiy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caloriecheck.R;
import com.example.caloriecheck.services.StepCounter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Pedometer extends AppCompatActivity {
    EditText edPedometer;
    TextView tvPedometer;
    ImageView imgback;
    Button btnadd;
    SharedPreferences sharedPreferences;
    TextView tvStep;
    TextView tvCaloOut;

    private static Pedometer mInstance;

    public static Pedometer getInstance() {
        return mInstance;
    }

    public static synchronized void setmInstance(Pedometer mInstance) {
        Pedometer.mInstance = mInstance;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedometer);
        setmInstance(this);

        tvCaloOut = findViewById(R.id.tvCaloOut);
        tvPedometer = findViewById(R.id.tvpedometer);
        imgback = findViewById(R.id.backpedometer);
        btnadd = findViewById(R.id.addpedometer);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String date2 = sdf.format(new Date());
        sharedPreferences = getSharedPreferences("data" + date2,MODE_PRIVATE);
        tvStep = findViewById(R.id.tv_stepsTaken);

        setTextPredo();




        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });




            // Mark Service as Started
            Preferences.setServiceRun(this, false);

            // Start Step Counting service
            Intent serviceIntent = new Intent(this, StepCounter.class);
            startService(serviceIntent);

        Log.e("dungkm",isServiceRunning() + " biến gì đó");



    }

    public void setTextPredo() {
        tvStep.setText(Preferences.getStepCount(this));
        tvCaloOut.setText("Calorie đã đốt: "  +(int)(Integer.parseInt(Preferences.getStepCount(this)) * 0.04) + " kcal");
        SharedPreferences.Editor editordata = sharedPreferences.edit();
        editordata.putInt("pedometer", Integer.parseInt(Preferences.getStepCount(this)));
        editordata.commit();
    }

    private boolean isServiceRunning() {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if ("com.example.caloriecheck".equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}