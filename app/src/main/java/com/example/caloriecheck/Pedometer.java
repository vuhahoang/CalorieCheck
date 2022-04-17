package com.example.caloriecheck;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class Pedometer extends AppCompatActivity {
    EditText edPedometer;
    TextView tvPedometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedometer);

        edPedometer = findViewById(R.id.Edpedometer);
        tvPedometer = findViewById(R.id.tvpedometer);

        edPedometer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().equals("")){
                    tvPedometer.setText("");
                }else {
                    int step = Integer.parseInt(s.toString());
                    double calorie = 0.04 * step;
                    tvPedometer.setText(calorie + "kcal");
                }

            }
        });


    }
}