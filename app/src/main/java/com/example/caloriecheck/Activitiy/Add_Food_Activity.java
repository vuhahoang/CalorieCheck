package com.example.caloriecheck.Activitiy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.caloriecheck.CustomApdapter.FoodAdapterCalo;
import com.example.caloriecheck.Model.FoodModel;
import com.example.caloriecheck.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class Add_Food_Activity extends AppCompatActivity {
    TextView name,calo,protein,carb,fat;
    Button btnTim;
    EditText edfood;
    ProgressBar load;
    ConstraintLayout ci,cc;
    RecyclerView rc,rc1;
    ArrayList<FoodModel> foodModels;
    FoodAdapterCalo foodAdapterCalo,foodAdapterCalo1;
    LinearLayout customfood;
    TextView title,tvthucpham,tvthucpham1;
    ArrayList<FoodModel> foodModels1;
    SharedPreferences sharedPreferences;
    LinearLayout llback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        btnTim = findViewById(R.id.btntimkiemthucan);
        edfood = findViewById(R.id.edsearchfood);
        load = findViewById(R.id.pgcalo);
        ci = findViewById(R.id.ctlayoutinfo);
        cc = findViewById(R.id.ctlayoutrc);
        rc = findViewById(R.id.rccalo);
        rc1 = findViewById(R.id.rccalosearch);
        title = findViewById(R.id.tvtitle);
        llback = findViewById(R.id.lllayoutbackinaddfood);
        tvthucpham = findViewById(R.id.tvthucpham);
        tvthucpham1 = findViewById(R.id.tvthucpham1);
        customfood = findViewById(R.id.botaddfood);
        foodModels = new ArrayList<>();
        foodModels1 = new ArrayList<>();
        Intent i = getIntent();
        String tit = i.getStringExtra("title");
        title.setText(i.getStringExtra("title"));
        if(cc.isEnabled()){
            getList();
        }
        foodAdapterCalo = new FoodAdapterCalo(this,foodModels,i);
        foodAdapterCalo1 = new FoodAdapterCalo(this,foodModels1,i);
        rc.setAdapter(foodAdapterCalo);





        edfood.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                   Log.d("vu1",s.toString());


                   if (start == 0){
                       cc.setVisibility(View.VISIBLE);
                       ci.setVisibility(View.GONE);
                       if(cc.isEnabled()){
                           Log.d("cc","cc.isEnabled");
                       }

                   }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });





        btnTim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load.setVisibility(View.VISIBLE);
                cc.setVisibility(View.GONE);
                ci.setVisibility(View.VISIBLE);
                String foodsearch =  edfood.getText().toString();
                if(foodsearch.isEmpty()){
                    Toast.makeText(Add_Food_Activity.this,"Không được bỏ trống ô tìm kiếm",Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
                    DatabaseReference reference = rootNode.getReference("FoodCustoms");
                    Query check = reference.orderByChild("name").equalTo(foodsearch);
                    check.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                Log.d("yes", "yes");
                                String name = snapshot.child(foodsearch).child("name").getValue(String.class);
                                String calorie = snapshot.child(foodsearch).child("calorie").getValue(String.class);
                                String carb = snapshot.child(foodsearch).child("carb").getValue(String.class);
                                String protein = snapshot.child(foodsearch).child("protein").getValue(String.class);
                                String fat = snapshot.child(foodsearch).child("fat").getValue(String.class);
                                FoodModel foodModelci = new FoodModel(name, calorie, protein, carb, fat, "100");
                                foodModels1.add(foodModelci);
                                foodAdapterCalo.notifyDataSetChanged();
                                rc1.setAdapter(foodAdapterCalo1);
                                load.setVisibility(View.GONE);


                            } else {
                                translateText(foodsearch);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }





                }





        });

        customfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Add_Food_Activity.this,CustomFood.class);
                i.putExtra("title",tit);
                startActivity(i);
            }
        });

        llback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    private void getList(){
        sharedPreferences = getSharedPreferences("User",MODE_PRIVATE);
        String nameUser = sharedPreferences.getString("username","error");
        Boolean check = sharedPreferences.getBoolean("checklogin",false);
        if (check){
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("User").child(nameUser).child("Recently");
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull  DataSnapshot snapshot) {
                    if(!snapshot.exists()){
                        tvthucpham1.setText("Phổ biến");
                        FirebaseDatabase databasefood = FirebaseDatabase.getInstance();
                        DatabaseReference myfood = databasefood.getReference("Foods");
                        myfood.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                                    FoodModel foodModel = dataSnapshot.getValue(FoodModel.class);
                                    foodModels.add(foodModel);


                                }

                                foodAdapterCalo.notifyDataSetChanged();
                            }

                            @Override
                            public void onCancelled(@NonNull  DatabaseError error) {

                            }
                        });

                    }else {

                        tvthucpham1.setText("Gần đây");

                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            FoodModel foodModel = dataSnapshot.getValue(FoodModel.class);
                            foodModels.add(foodModel);
                        }

                        foodAdapterCalo.notifyDataSetChanged();
                    }

                }

                @Override
                public void onCancelled(@NonNull  DatabaseError error) {

                }
            });
        }else {
            tvthucpham1.setText("Phổ biến");
            FirebaseDatabase databasefood = FirebaseDatabase.getInstance();
            DatabaseReference myfood = databasefood.getReference("Foods");
            myfood.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull  DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        FoodModel foodModel = dataSnapshot.getValue(FoodModel.class);
                        foodModels.add(foodModel);


                    }

                    foodAdapterCalo.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull  DatabaseError error) {

                }
            });
        }

    }



    private void translateText(String source){
        TranslatorOptions options = new TranslatorOptions.Builder()
                .setSourceLanguage(TranslateLanguage.VIETNAMESE)
                .setTargetLanguage(TranslateLanguage.ENGLISH)
                .build();

        final Translator englishVietnam = Translation.getClient(options);

        DownloadConditions conditions = new DownloadConditions.Builder()
                .requireWifi()
                .build();

        englishVietnam.downloadModelIfNeeded(conditions)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        englishVietnam.translate(source).addOnSuccessListener(new OnSuccessListener<String>() {
                            @Override
                            public void onSuccess(String s) {
                                Log.d("NameEn",s);
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference myRef = database.getReference("User");








                                RequestQueue requestQueue = Volley.newRequestQueue(Add_Food_Activity.this);
                                String url = "https://api.calorieninjas.com/v1/nutrition?query= 100gr " + s;

                                StringRequest stringRequest= new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            load.setVisibility(View.GONE);
                                            JSONObject jsonObject = new JSONObject(response.toString());

                                            Log.d("test",jsonObject.getString("items"));
                                            if(jsonObject.getString("items").equals("[]")){
                                                Toast.makeText(Add_Food_Activity.this,"Không có thức ăn này vui lòng tìm lại",Toast.LENGTH_LONG).show();
                                            }
                                            else {
                                                JSONArray flagObject = jsonObject.getJSONArray("items");
                                                JSONObject jsonObject1 = flagObject.getJSONObject(0);
                                                FoodModel foodModelci = new FoodModel(source,jsonObject1.getString("calories"),jsonObject1.getString("protein_g"),jsonObject1.getString("carbohydrates_total_g"),jsonObject1.getString("fat_total_g"),"100");
                                                sharedPreferences = getSharedPreferences("User",MODE_PRIVATE);
                                                String nameUser = sharedPreferences.getString("username","error");
                                                myRef.child(nameUser).child("Recently").child(source).setValue(foodModelci);
                                                foodModels1.add(foodModelci);
                                                foodAdapterCalo.notifyDataSetChanged();
                                                rc1.setAdapter(foodAdapterCalo1);


                                            }





                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }


                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                })
                                {
                                    @Override
                                    public Map<String, String> getHeaders() throws AuthFailureError {
                                        Map<String, String> headers = new HashMap<>();
                                        headers.put("Content-Type", "application/json");
                                        headers.put("x-api-key", "dynkcHzHFa2I/rf+DWH9Wg==GiSrfQ0y0YWLQ7hc");
                                        return headers;

                                    }
                                };

                                requestQueue.add(stringRequest);





                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull  Exception e) {
                              Toast.makeText(Add_Food_Activity.this,"error",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Add_Food_Activity.this,"error",Toast.LENGTH_SHORT).show();
                    }
                });

    }




}