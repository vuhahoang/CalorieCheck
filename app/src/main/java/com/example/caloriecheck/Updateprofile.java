package com.example.caloriecheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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

        if (stitle.equals("M???c ti??u")){
            muctieu = i.getStringExtra("goal");
            Log.d("testso", muctieu+"ss");
            codegoal();
        }else if(stitle.equals("C??n n???ng hi???n t???i")){
            int cantv = i.getIntExtra("weight",0);
            edweight.setText(cantv+"");
            Log.d("testso", cantv+"ss");
            codeweight();
        }else if(stitle.equals("C??n n???ng mong mu???n")){
            int canmttv = i.getIntExtra("goalweight",0);
            edgoalweight.setText(canmttv+"");
            codegoalweight();
        }else if(stitle.equals("T???n su???t ho???t ?????ng")){
            hoatdong = i.getStringExtra("activity");
            codeactivity();
        }else if(stitle.equals("Calorie trong ng??y")){
            int calotv = i.getIntExtra("calorie",0);
            edcalorie.setText(calotv+"");
            codecalorie();
        }

        cfloseweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("nhucau",-500);
                editor.commit();
                Toast.makeText(Updateprofile.this,"???? l??u",Toast.LENGTH_LONG).show();
                onBackPressed();
            }
        });

        cfmaintainweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("nhucau",0);
                editor.commit();
                Toast.makeText(Updateprofile.this,"???? l??u",Toast.LENGTH_LONG).show();
                onBackPressed();
            }
        });

        cfbuildmuscle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("nhucau",500);
                editor.commit();
                Toast.makeText(Updateprofile.this,"???? l??u",Toast.LENGTH_LONG).show();
               onBackPressed();
            }
        });

        cfvdi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putFloat("R", (float) 1.2);
                editor.commit();
                Toast.makeText(Updateprofile.this,"???? l??u",Toast.LENGTH_LONG).show();
                onBackPressed();
            }
        });

        cfvdn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putFloat("R", (float) 1.375);
                editor.commit();
                Toast.makeText(Updateprofile.this,"???? l??u",Toast.LENGTH_LONG).show();
               onBackPressed();
            }
        });

        cfvdvua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putFloat("R", (float) 1.55);
                editor.commit();
                Toast.makeText(Updateprofile.this,"???? l??u",Toast.LENGTH_LONG).show();
             onBackPressed();
            }
        });

        cfvds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putFloat("R", (float) 1.725);
                editor.commit();
                Toast.makeText(Updateprofile.this,"???? l??u",Toast.LENGTH_LONG).show();
                onBackPressed();
            }
        });

        cfvdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putFloat("R", (float) 1.9);
                editor.commit();
                Toast.makeText(Updateprofile.this,"???? l??u",Toast.LENGTH_LONG).show();
                onBackPressed();
            }
        });




    }

    private void codegoal(){
        layoutgoal.setVisibility(View.VISIBLE);

        if(muctieu.equals("Gi???m c??n")){
            giamcan.setTextColor(Color.parseColor("#3093FF"));
        }else if(muctieu.equals("Gi??? c??n")){
             giucan.setTextColor(Color.parseColor("#3093FF"));
        }else if(muctieu.equals("T??ng c??n")){
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
                    Toast.makeText(Updateprofile.this,"Kh??ng ???????c b??? tr???ng",Toast.LENGTH_LONG).show();
                }else{
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("can",Integer.parseInt(canht));
                    editor.commit();
                    onBackPressed();
                    Toast.makeText(Updateprofile.this,"???? l??u",Toast.LENGTH_LONG).show();
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
                    Toast.makeText(Updateprofile.this,"Kh??ng ???????c b??? tr???ng",Toast.LENGTH_LONG).show();
                }else{
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("canmoi",Integer.parseInt(canmm));
                    editor.commit();
                    onBackPressed();
                    Toast.makeText(Updateprofile.this,"???? l??u",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void codeactivity(){
        layoutactivity.setVisibility(View.VISIBLE);
        if(hoatdong.equals("V???n ?????ng ??t")){
            vdi.setTextColor(Color.parseColor("#3093FF"));
        }else if(hoatdong.equals("V???n ?????ng nh???")){
            vdn.setTextColor(Color.parseColor("#3093FF"));
        }else if(hoatdong.equals("V???n ?????ng v???a")){
            vdvua.setTextColor(Color.parseColor("#3093FF"));
        }else if(hoatdong.equals("V???n ?????ng si??ng")){
            vds.setTextColor(Color.parseColor("#3093FF"));
        }else if(hoatdong.equals("V???n ?????ng vi??n")){
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
                    Toast.makeText(Updateprofile.this,"Kh??ng ???????c b??? tr???ng",Toast.LENGTH_LONG).show();
                }else{
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("calorie",Integer.parseInt(calorie));
                    editor.commit();
                    onBackPressed();
                    Toast.makeText(Updateprofile.this,"???? l??u",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}