package com.example.caloriecheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.shape.InterpolateOnScrollPositionChangeHelper;

public class Updateprofile extends AppCompatActivity {
    TextView title,tangcan,giucan,giamcan,vdi,vdn,vdvua,vds,vdv;
    LinearLayout layoutgoal,layoutweight,layoutgoalweight,layoutactivity,layoutcalorie,bot,cfloseweight,cfbuildmuscle,cfmaintainweight,cfvdi,cfvdn,cfvdvua,cfvds,cfvdv;
    EditText edweight,edgoalweight,edcalorie;
    SharedPreferences sharedPreferences;
    Button save;
    String stitle;
    String muctieu,hoatdong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateprofile);
        title = findViewById(R.id.tvtitleupdate);
        layoutgoal = findViewById(R.id.layoutforgoal);
        layoutweight = findViewById(R.id.layoutforweiht);
        layoutgoalweight = findViewById(R.id.layoutforgoalweiht);
        layoutactivity = findViewById(R.id.layoutforacitivity);
        layoutcalorie = findViewById(R.id.layoutforcalorie);
        bot = findViewById(R.id.botupdateprofile);
        cfloseweight = findViewById(R.id.clickforloseweight);
        cfbuildmuscle = findViewById(R.id.clickforbuildmuscle);
        cfmaintainweight = findViewById(R.id.clickformaintainwweight);
        cfvdi = findViewById(R.id.clickforvdi);
        cfvdn = findViewById(R.id.clickforvdn);
        cfvdvua = findViewById(R.id.clickforvdvua);
        cfvds = findViewById(R.id.clickforvdsieng);
        cfvdv = findViewById(R.id.clickforvdv);
        edweight = findViewById(R.id.edweight);
        edgoalweight = findViewById(R.id.edgoalweight);
        edcalorie = findViewById(R.id.edcalorieinupdateprofile);
        tangcan = findViewById(R.id.tvtangcan);
        giucan = findViewById(R.id.tvgiucan);
        giamcan = findViewById(R.id.tvgiamcan);
        vdi = findViewById(R.id.tvvandongit);
        vdn = findViewById(R.id.tvvandongnhe);
        vdvua = findViewById(R.id.tvvandongvua);
        vds = findViewById(R.id.tvvandongsieng);
        vdv = findViewById(R.id.tvvandongvien);
        save = findViewById(R.id.saveinupdateprofile);
        sharedPreferences = getSharedPreferences("infomation",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Intent i = getIntent();
        stitle = i.getStringExtra("title");
        title.setText(stitle);

        if (stitle.equals("Mục tiêu")){
            muctieu = i.getStringExtra("goal");
            codegoal();
        }else if(stitle.equals("Cân nặng hiện tại")){
            codeweight();
        }else if(stitle.equals("Cân nặng mong muốn")){
            codegoalweight();
        }else if(stitle.equals("Tần suất hoạt động")){
            hoatdong = i.getStringExtra("activity");
            codeactivity();
        }else if(stitle.equals("Calorie trong ngày")){
            codecalorie();
        }

        cfloseweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("nhucau",-500);
                editor.commit();
                Toast.makeText(Updateprofile.this,"Đã lưu",Toast.LENGTH_LONG).show();
                onBackPressed();
            }
        });

        cfmaintainweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("nhucau",0);
                editor.commit();
                Toast.makeText(Updateprofile.this,"Đã lưu",Toast.LENGTH_LONG).show();
                onBackPressed();
            }
        });

        cfbuildmuscle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("nhucau",500);
                editor.commit();
                Toast.makeText(Updateprofile.this,"Đã lưu",Toast.LENGTH_LONG).show();
               onBackPressed();
            }
        });

        cfvdi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putFloat("R", (float) 1.2);
                editor.commit();
                Toast.makeText(Updateprofile.this,"Đã lưu",Toast.LENGTH_LONG).show();
                onBackPressed();
            }
        });

        cfvdn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putFloat("R", (float) 1.375);
                editor.commit();
                Toast.makeText(Updateprofile.this,"Đã lưu",Toast.LENGTH_LONG).show();
               onBackPressed();
            }
        });

        cfvdvua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putFloat("R", (float) 1.55);
                editor.commit();
                Toast.makeText(Updateprofile.this,"Đã lưu",Toast.LENGTH_LONG).show();
             onBackPressed();
            }
        });

        cfvds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putFloat("R", (float) 1.725);
                editor.commit();
                Toast.makeText(Updateprofile.this,"Đã lưu",Toast.LENGTH_LONG).show();
                onBackPressed();
            }
        });

        cfvdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putFloat("R", (float) 1.9);
                editor.commit();
                Toast.makeText(Updateprofile.this,"Đã lưu",Toast.LENGTH_LONG).show();
                onBackPressed();
            }
        });




    }

    private void codegoal(){
        layoutgoal.setVisibility(View.VISIBLE);

        if(muctieu.equals("Giảm cân")){
            giamcan.setTextColor(Color.parseColor("#3093FF"));
        }else if(muctieu.equals("Giữ cân")){
             giucan.setTextColor(Color.parseColor("#3093FF"));
        }else if(muctieu.equals("Tăng cân")){
            tangcan.setTextColor(Color.parseColor("#3093FF"));
        }
    }

    private void codeweight(){
        layoutweight.setVisibility(View.VISIBLE);
        bot.setVisibility(View.VISIBLE);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String canht = edweight.getText().toString().trim();
                if(canht.isEmpty()){
                    Toast.makeText(Updateprofile.this,"Không được bỏ trống",Toast.LENGTH_LONG).show();
                }else{
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("can",Integer.parseInt(canht));
                    editor.commit();
                    onBackPressed();
                    Toast.makeText(Updateprofile.this,"Đã lưu",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void codegoalweight(){
        layoutgoalweight.setVisibility(View.VISIBLE);
        bot.setVisibility(View.VISIBLE);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String canmm = edgoalweight.getText().toString().trim();
                if(canmm.isEmpty()){
                    Toast.makeText(Updateprofile.this,"Không được bỏ trống",Toast.LENGTH_LONG).show();
                }else{
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("canmoi",Integer.parseInt(canmm));
                    editor.commit();
                    onBackPressed();
                    Toast.makeText(Updateprofile.this,"Đã lưu",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void codeactivity(){
        layoutactivity.setVisibility(View.VISIBLE);
        if(hoatdong.equals("Vận động ít")){
            vdi.setTextColor(Color.parseColor("#3093FF"));
        }else if(hoatdong.equals("Vận động nhẹ")){
            vdn.setTextColor(Color.parseColor("#3093FF"));
        }else if(hoatdong.equals("Vận động vừa")){
            vdvua.setTextColor(Color.parseColor("#3093FF"));
        }else if(hoatdong.equals("Vận động siêng")){
            vds.setTextColor(Color.parseColor("#3093FF"));
        }else if(hoatdong.equals("Vận động viên")){
            vdv.setTextColor(Color.parseColor("#3093FF"));
        }
    }


    private void codecalorie(){
        layoutcalorie.setVisibility(View.VISIBLE);
        bot.setVisibility(View.VISIBLE);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String calorie = edcalorie.getText().toString().trim();
                if(calorie.isEmpty()){
                    Toast.makeText(Updateprofile.this,"Không được bỏ trống",Toast.LENGTH_LONG).show();
                }else{
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("calorie",Integer.parseInt(calorie));
                    editor.commit();
                    onBackPressed();
                    Toast.makeText(Updateprofile.this,"Đã lưu",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}