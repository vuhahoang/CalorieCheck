package com.example.caloriecheck.Activitiy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caloriecheck.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class Pedometer extends AppCompatActivity {
    EditText edPedometer;
    TextView tvPedometer;
    ImageView imgback;
    Button btnadd;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedometer);

        edPedometer = findViewById(R.id.Edpedometer);
        tvPedometer = findViewById(R.id.tvpedometer);
        imgback = findViewById(R.id.backpedometer);
        btnadd = findViewById(R.id.addpedometer);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String date2 = sdf.format(new Date());
        sharedPreferences = getSharedPreferences("data" + date2,MODE_PRIVATE);
        edPedometer.setText(sharedPreferences.getInt("pedometer",0) + "");

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

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = edPedometer.getText().toString();


                SharedPreferences.Editor editordata = sharedPreferences.edit();
                if(number.isEmpty()){
                    Toast.makeText(Pedometer.this,"Nhập dữ liệu",Toast.LENGTH_SHORT).show();
                }else {
                    editordata.putInt("pedometer", Integer.parseInt(edPedometer.getText().toString()));
                    editordata.commit();
                    onBackPressed();
                }



            }
        });

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }
}