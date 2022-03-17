package com.example.caloriecheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caloriecheck.Model.Diary;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Notes extends AppCompatActivity {
    TextView tvday;
    EditText edcontent;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    SharedPreferences sharedPreferences;
    String day,month,year,today;
    Button btnadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        tvday = findViewById(R.id.dayinnotes);
        edcontent = findViewById(R.id.edcontentnotes);
        btnadd = findViewById(R.id.addnote);
        Intent i= getIntent();
        Boolean check =  i.getBooleanExtra("check",false);
        if (check){
            tvday.setText(i.getStringExtra("dow")  + ", Ngày " + i.getStringExtra("day") + " Tháng "+ i.getStringExtra("month") );
            edcontent.setText(i.getStringExtra("content"));
        }else {
            getDay();
        }


        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edcontent.getText().toString().equals("")){
                    Toast.makeText(Notes.this,"Nội dung không được để chống",Toast.LENGTH_SHORT).show();

                }else {
                    getData();
                    Toast.makeText(Notes.this,"Đã thêm",Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
            }
        });
    }

    private String getDay(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
        SimpleDateFormat sdf2 = new SimpleDateFormat("EEEE");
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy");
        day = sdf.format(new Date());
        month = sdf1.format(new Date());
        year = sdf3.format(new Date());
        today = sdf2.format(new Date());

        if(today.equals("Monday")){
            today = "Thứ Hai";
        }else if(today.equals("Tuesday")){
            today = "Thứ Ba";
        }else if(today.equals("Wednesday")){
            today = "Thứ Tư";
        }else if(today.equals("Thursday")){
            today = "Thứ Năm";
        }else if(today.equals("Friday")){
            today = "Thứ Sáu";
        }else if(today.equals("Saturday")){
            today = "Thứ Bảy";
        }else if(today.equals("Sunday")){
            today = "Chủ Nhật";
        }


        tvday.setText(today + ", Ngày " + day + " Tháng "+month );
        return today;
    }

    private void getData(){
        sharedPreferences = getSharedPreferences("User",MODE_PRIVATE);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("User");
        String username = sharedPreferences.getString("username","");
        Diary diary = new Diary(day,month,year,today,edcontent.getText().toString());
        databaseReference.child(username).child("Notes").child(day +month).setValue(diary);
    }
}