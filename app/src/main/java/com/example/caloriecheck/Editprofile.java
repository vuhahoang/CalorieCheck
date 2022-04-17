package com.example.caloriecheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Editprofile extends AppCompatActivity {
    TextView goal,weight,goalweight,activity,calorie;
    LinearLayout llgoal,llweight,llgoalweight,llactivity,llcalorie;
    SharedPreferences sharedPreferences;
    String muctieu;
    String hoatdong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);
        goal = findViewById(R.id.tvgoal);
        weight = findViewById(R.id.tvweight);
        goalweight = findViewById(R.id.tvgoalweight);
        activity = findViewById(R.id.tvactivity);
        calorie = findViewById(R.id.tvcalorieinttcn);
        llgoal = findViewById(R.id.layoutgoal);
        llweight = findViewById(R.id.layoutweight);
        llgoalweight = findViewById(R.id.layoutgoalweight);
        llactivity = findViewById(R.id.layoutactivity);
        llcalorie = findViewById(R.id.layoutcalorie);
//        sharedPreferences = getSharedPreferences("infomation",MODE_PRIVATE);
//        int mt = sharedPreferences.getInt("nhucau",0);
//        float r = sharedPreferences.getFloat("R",0);
//        Log.d("Ronaldo",r+"");
//        if(mt == -500){
//            muctieu="Giảm cân";
//        }else if(mt == 0){
//            muctieu="Giữ cân";
//        }else muctieu="Tăng cân";
//
//        if(Float.compare(r, (float) 1.2)==0){
//            hoatdong="Vận động ít";
//        }else if(Float.compare(r, (float) 1.375)==0){
//            hoatdong="Vận động nhẹ";
//        }else if( Float.compare(r, (float) 1.55)==0){
//            hoatdong="Vận động vừa";
//        }else if( Float.compare(r, (float) 1.725)==0){
//            hoatdong="Vận động siêng";
//        }else if(Float.compare(r, (float) 1.9)==0){
//            hoatdong="Vận động viên";
//        }


//        goal.setText(muctieu);
//        weight.setText(sharedPreferences.getInt("can",0)+"");
//        goalweight.setText(sharedPreferences.getInt("canmoi",0)+"");
//        activity.setText(hoatdong);
//        calorie.setText(sharedPreferences.getInt("calorie",0)+"");

        llgoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Editprofile.this,Updateprofile.class);
                i.putExtra("goal",muctieu);
                i.putExtra("title","Mục tiêu");
                startActivity(i);
            }
        });

        llweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Editprofile.this,Updateprofile.class);
                i.putExtra("title","Cân nặng hiện tại");
                i.putExtra("weight",sharedPreferences.getInt("can",0));
                startActivity(i);
            }
        });

        llgoalweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Editprofile.this,Updateprofile.class);
                i.putExtra("title","Cân nặng mong muốn");
                i.putExtra("goalweight",sharedPreferences.getInt("canmoi",0));
                startActivity(i);
            }
        });

        llactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Editprofile.this,Updateprofile.class);
                i.putExtra("activity",hoatdong);
                i.putExtra("title","Tần suất hoạt động");
                startActivity(i);
            }
        });

        llcalorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Editprofile.this,Updateprofile.class);
                i.putExtra("calorie",sharedPreferences.getInt("calorie",0));
                i.putExtra("title","Calorie trong ngày");
                startActivity(i);
            }
        });





    }

    @Override
    protected void onStart() {
        sharedPreferences = getSharedPreferences("infomation",MODE_PRIVATE);
        int mt = sharedPreferences.getInt("nhucau",0);
        float r = sharedPreferences.getFloat("R",0);

        if(mt == -500){
            muctieu="Giảm cân";
        }else if(mt == 0){
            muctieu="Giữ cân";
        }else muctieu="Tăng cân";

        if(Float.compare(r, (float) 1.2)==0){
            hoatdong="Vận động ít";
        }else if(Float.compare(r, (float) 1.375)==0){
            hoatdong="Vận động nhẹ";
        }else if( Float.compare(r, (float) 1.55)==0){
            hoatdong="Vận động vừa";
        }else if( Float.compare(r, (float) 1.725)==0){
            hoatdong="Vận động siêng";
        }else if(Float.compare(r, (float) 1.9)==0){
            hoatdong="Vận động viên";
        }
        super.onStart();
        goal.setText(muctieu);
        weight.setText(sharedPreferences.getInt("can",0)+"");
        goalweight.setText(sharedPreferences.getInt("canmoi",0)+"");
        activity.setText(hoatdong);
        calorie.setText(sharedPreferences.getInt("calorie",0)+"");
    }
}