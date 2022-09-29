package com.example.caloriecheck.Activitiy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caloriecheck.Model.FoodModel;
import com.example.caloriecheck.R;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FoodInfo extends AppCompatActivity {
    TextView tvName,tvCarb,tvPro,tvFat,tvCalo,tvCarb1,tvPro1,tvFat1,tvCalo1,title;
    EditText edgram;
    Button btnadd;
    SharedPreferences sharedPreferences,sharedPreferencesdata;
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    String buaan,name,calorie,fat,carb,protein,gram;
    FoodModel foodModel;
    Boolean check = false;
    ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_info);
        tvName = findViewById(R.id.tvnamefoodinfo);
        tvCarb = findViewById(R.id.tvcarbfoodinfo);
        tvPro = findViewById(R.id.tvproteinfoodinfo);
        tvFat = findViewById(R.id.tvfatfoodinfo);
        tvCalo = findViewById(R.id.tvcaloriefoodinfo);
        tvCarb1 = findViewById(R.id.tvcarbfoodinfo1);
        tvPro1 = findViewById(R.id.tvproteinfoodinfo1);
        tvFat1 = findViewById(R.id.tvfatfoodinfo1);
        tvCalo1 = findViewById(R.id.tvcaloriefoodinfo1);
        title = findViewById(R.id.tvtitlefoodinfo);
        edgram = findViewById(R.id.edgramfoodinfo);
        btnadd = findViewById(R.id.addfoodinfoodinfo);
        back = findViewById(R.id.imgbackfoodinfo);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getdata();
        if(check){
            btnadd.setText("Sửa");
            edgram.setText(gram);
        }

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edgram.getText().toString().isEmpty()){
                    Toast.makeText(FoodInfo.this,"Vui lòng nhập nhá trị gram",Toast.LENGTH_SHORT).show();
                }else {
                    if (!check){
                        Float sogram = Float.parseFloat(edgram.getText().toString());
                        Float tiso = sogram/100;
                        foodModel = new FoodModel(name,Float.parseFloat(calorie)*tiso+"",Float.parseFloat(protein)*tiso+"",Float.parseFloat(carb)*tiso+"",Float.parseFloat(fat)*tiso+"",sogram+"");
                        Gson gson = new Gson();
                        String strJsonUser = gson.toJson(foodModel);
                        String date2 = sdf.format(new Date());
                        sharedPreferences = getSharedPreferences(buaan+date2,MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        String checkexits = sharedPreferences.getString(foodModel.getName(),"");

                        if(checkexits.equals("")){
                            editor.putString(foodModel.getName(),strJsonUser);
                            editor.commit();
                        }else {
                            FoodModel foodModel2 = gson.fromJson(checkexits,FoodModel.class);
                            FoodModel foodModel3 = new FoodModel(foodModel2.getName(),(Float.parseFloat(foodModel2.getCalorie())+Float.parseFloat(foodModel.getCalorie()))+"",(Float.parseFloat(foodModel2.getProtein())+Float.parseFloat(foodModel.getProtein()))+"",(Float.parseFloat(foodModel2.getCarb())+Float.parseFloat(foodModel.getCarb()))+"",(Float.parseFloat(foodModel2.getFat())+Float.parseFloat(foodModel.getFat()))+"",(Float.parseFloat(foodModel2.getGram())+Float.parseFloat(foodModel.getGram()))+"");
                            String newjson = gson.toJson(foodModel3);
                            editor.putString(foodModel3.getName(),newjson);
                            editor.commit();
                            Log.d("conka",newjson);
                        }
                        sharedPreferencesdata = getSharedPreferences("data"+date2, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editordata = sharedPreferencesdata.edit();
                        Float proteinht =  sharedPreferencesdata.getFloat(buaan+"protein",0);
                        Float calorieht = sharedPreferencesdata.getFloat(buaan +"calorie",0);
                        Float fatht = sharedPreferencesdata.getFloat(buaan+"fat",0);
                        Float carbht = sharedPreferencesdata.getFloat(buaan +"carb",0);
                        editordata.putFloat(buaan+"protein",Float.parseFloat(foodModel.getProtein()) + proteinht);
                        editordata.putFloat(buaan+"calorie",Float.parseFloat(foodModel.getCalorie()) + calorieht);
                        editordata.putFloat(buaan+"fat",Float.parseFloat(foodModel.getFat()) + fatht);
                        editordata.putFloat(buaan+"carb",Float.parseFloat(foodModel.getCarb()) + carbht);
                        editordata.commit();
                    }else {
                        Float sogramsau = Float.parseFloat(edgram.getText().toString());
                        Float sogramtrc = Float.parseFloat(gram);
                        Float sogramchenhlech = sogramsau - sogramtrc;
                        Float tiso = sogramchenhlech/sogramtrc;
                        foodModel = new FoodModel(name,Float.parseFloat(calorie)*tiso+"",Float.parseFloat(protein)*tiso+"",Float.parseFloat(carb)*tiso+"",Float.parseFloat(fat)*tiso+"",Float.parseFloat(gram)*tiso+"");
                        String date2 = sdf.format(new Date());
                        Gson gson = new Gson();
                        sharedPreferences = getSharedPreferences(buaan+date2,MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        String checkexits = sharedPreferences.getString(foodModel.getName(),"");
                        FoodModel foodModel2 = gson.fromJson(checkexits,FoodModel.class);
                        FoodModel foodModel3 = new FoodModel(foodModel2.getName(),(Float.parseFloat(foodModel2.getCalorie())+Float.parseFloat(foodModel.getCalorie()))+"",(Float.parseFloat(foodModel2.getProtein())+Float.parseFloat(foodModel.getProtein()))+"",(Float.parseFloat(foodModel2.getCarb())+Float.parseFloat(foodModel.getCarb()))+"",(Float.parseFloat(foodModel2.getFat())+Float.parseFloat(foodModel.getFat()))+"",(Float.parseFloat(foodModel2.getGram())+Float.parseFloat(foodModel.getGram()))+"");
                        String newjson = gson.toJson(foodModel3);
                        editor.putString(foodModel3.getName(),newjson);
                        editor.commit();

                        sharedPreferencesdata = getSharedPreferences("data"+date2, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editordata = sharedPreferencesdata.edit();
                        Float proteinht =  sharedPreferencesdata.getFloat(buaan+"protein",0);
                        Float calorieht = sharedPreferencesdata.getFloat(buaan +"calorie",0);
                        Float fatht = sharedPreferencesdata.getFloat(buaan+"fat",0);
                        Float carbht = sharedPreferencesdata.getFloat(buaan +"carb",0);
                        editordata.putFloat(buaan+"protein",Float.parseFloat(foodModel.getProtein()) + proteinht);
                        editordata.putFloat(buaan+"calorie",Float.parseFloat(foodModel.getCalorie()) + calorieht);
                        editordata.putFloat(buaan+"fat",Float.parseFloat(foodModel.getFat()) + fatht);
                        editordata.putFloat(buaan+"carb",Float.parseFloat(foodModel.getCarb()) + carbht);
                        editordata.commit();
                    }
                }



            }
        });




    }

    private void getdata(){
        Intent i = getIntent();
        buaan = i.getStringExtra("title");
        calorie = i.getStringExtra("calorieFood");
        name = i.getStringExtra("nameFood");
        fat = i.getStringExtra("fatFood");
        carb = i.getStringExtra("carbFood");
        protein = i.getStringExtra("proteinFood");
        gram = i.getStringExtra("gram");
        tvName.setText(i.getStringExtra("nameFood"));
        tvCarb.setText(i.getStringExtra("carbFood"));
        tvCarb1.setText(i.getStringExtra("carbFood"));
        tvPro.setText(i.getStringExtra("proteinFood"));
        tvPro1.setText(i.getStringExtra("proteinFood"));
        tvFat.setText(i.getStringExtra("fatFood"));
        tvFat1.setText(i.getStringExtra("fatFood"));
        tvCalo.setText(i.getStringExtra("calorieFood"));
        tvCalo1.setText(i.getStringExtra("calorieFood"));
        title.setText(i.getStringExtra("title"));
        check = i.getBooleanExtra("update",false);
    }
}