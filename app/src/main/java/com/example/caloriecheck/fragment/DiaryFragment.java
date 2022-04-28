package com.example.caloriecheck.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.caloriecheck.Add_Food_Activity;
import com.example.caloriecheck.CustomApdapter.NoteAdapter;
import com.example.caloriecheck.FoodSave;
import com.example.caloriecheck.Login;
import com.example.caloriecheck.Model.Diary;
import com.example.caloriecheck.Model.FoodModel;
import com.example.caloriecheck.Notes;
import com.example.caloriecheck.Pedometer;
import com.example.caloriecheck.R;
import com.example.caloriecheck.learn1;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class DiaryFragment extends Fragment {
    SharedPreferences sharedPreferences,sharedPreferencesdata;
    TextView tvMyCalorie,tvMyProtein,tvMyCarb,tvMyFat,tvcanht,tvcanmt,tvtieuthu,tvthaira,tvcalobs,tvcalobtr,tvcalobt,tvcalobp,pedometer,tvcaloout;
    int protein,fat,carb;
    FloatingActionButton add_bs, add_btr , add_bt , add_bp,btn_tang,btn_giam;
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    String date2 = sdf.format(new Date());
    int calorie;
    ProgressBar progressBar,pBpro,pbCarb,pbFat;
    Button addnotes;
    RecyclerView rc;
    ArrayList<Diary> diaries;
    NoteAdapter noteAdapter;
    CardView l1,l2,l3,l4,l5;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_diary, container, false);
        tvMyCalorie = view.findViewById(R.id.tvmycalorie);
        tvMyProtein = view.findViewById(R.id.tvMyProtein);
        tvMyCarb = view.findViewById(R.id.tvMyCarb);
        tvMyFat = view.findViewById(R.id.tvMyFat);
        add_bs = view.findViewById(R.id.btn_add_buasang);
        add_btr = view.findViewById(R.id.btn_add_buatrua);
        add_bt = view.findViewById(R.id.btn_add_buatoi);
        add_bp = view.findViewById(R.id.btn_add_buaphu);
        btn_tang = view.findViewById(R.id.btntangcan);
        btn_giam = view.findViewById(R.id.btngiamcan);
        tvcanht = view.findViewById(R.id.tvcanhientai);
        tvcanmt = view.findViewById(R.id.tvcanmuctieu);
        tvtieuthu = view.findViewById(R.id.datieuthu);
        tvthaira = view.findViewById(R.id.dathaira);
        tvcalobs = view.findViewById(R.id.tvMycalorieinbs);
        tvcalobtr = view.findViewById(R.id.tvMycalorieintr);
        tvcalobt = view.findViewById(R.id.tvMycalorieinbt);
        tvcalobp = view.findViewById(R.id.tvMycalorieinbp);
        progressBar = view.findViewById(R.id.progressBar);
        tvcaloout = view.findViewById(R.id.dathaira);
        pBpro = view.findViewById(R.id.progressBar2);
        pbCarb = view.findViewById(R.id.progressBar3);
        pbFat = view.findViewById(R.id.progressBar4);
        addnotes = view.findViewById(R.id.cardViewadddiary);
        pedometer = view.findViewById(R.id.btnpedometer);
        rc = view.findViewById(R.id.recyclerView);
        l1 = view.findViewById(R.id.learn1);
        l2 = view.findViewById(R.id.learn2);
        l3 = view.findViewById(R.id.learn3);
        l4 = view.findViewById(R.id.learn4);
        l5 = view.findViewById(R.id.learn5);

        diaries = new ArrayList<>();
        getNotes();
        noteAdapter = new NoteAdapter(diaries,getContext());
        rc.setAdapter(noteAdapter);
        SharedPreferences preferences = getContext().getSharedPreferences("User", Context.MODE_PRIVATE);
        Boolean checklogin = preferences.getBoolean("checklogin",false);




        sharedPreferences = getContext().getSharedPreferences("infomation",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int nhucau = sharedPreferences.getInt("nhucau",0);
        double r = sharedPreferences.getFloat("R",0);
        int can = sharedPreferences.getInt("can",0);
        int chieucao = sharedPreferences.getInt("chieucao",0);
        int k = sharedPreferences.getInt("K",0);
        int tuoi = sharedPreferences.getInt("tuoi",0);
        int canmt = sharedPreferences.getInt("canmoi",0);
        calorie = (int) (((9.99*can)+(6.25*chieucao)-(4.92*tuoi)+k)*r+nhucau);
        editor.putInt("calorie",calorie);
        editor.commit();

        marco(calorie,nhucau);
        tvMyProtein.setText(protein + "g còn lại");
        tvMyCarb.setText(carb+"g còn lại");
        tvMyFat.setText(fat+"g còn lại");
        tvcanht.setText(can + "");
        tvcanmt.setText("Mục tiêu" +canmt + " kg");
        sharedPreferencesdata = getActivity().getSharedPreferences("data"+date2,Context.MODE_PRIVATE);

        addnotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checklogin){
                    SimpleDateFormat sdf = new SimpleDateFormat("dd");
                    SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
                    String day = sdf.format(new Date());
                    String month = sdf1.format(new Date());
                    Boolean check = false;
                    int value = 0;
                    for ( int i = 0; i < diaries.size() ; i++){
                        Diary diary = diaries.get(i);

                        if(diary.getDay().equals(day)  && diary.getMonth().equals(month) ){

                            check = true;
                            value = i;
                        }
                    }

                    Log.d("check",check + "");

                    if(check){
                        Diary diary = diaries.get(value);
                        Intent i = new Intent(getContext(), Notes.class);
                        i.putExtra("day",diary.getDay());
                        i.putExtra("month",diary.getMonth());
                        i.putExtra("dow",diary.getDow());
                        i.putExtra("content",diary.getContent());
                        i.putExtra("check",true);
                        startActivity(i);
                    }else {
                        Intent i = new Intent(getContext(), Notes.class);
                        startActivity(i);
                    }
                }else {
                    AlertDialog.Builder builder =new AlertDialog.Builder(getContext());
                    builder.setMessage("Bạn cần đăng nhập để tiếp tục?")
                            .setCancelable(false)
                            .setPositiveButton("Đăng nhập", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent i = new Intent(getContext(), Login.class);
                                    startActivity(i);
                                }
                            })
                            .setNegativeButton("Để sau",null);
                    builder.show();
                }


            }
        });

        pedometer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), Pedometer.class);
                startActivity(i);
            }
        });

        add_bs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Float check = sharedPreferencesdata.getFloat("Bữa sángcalorie",0);
                if(check != 0.0){
                    Intent j = new Intent(getContext(), FoodSave.class);
                    j.putExtra("title","Bữa sáng");
                    startActivity(j);
                }else {
                    Intent i = new Intent(getContext(), Add_Food_Activity.class);
                    i.putExtra("title","Bữa sáng");
                    startActivity(i);
                }
            }
        });

        add_btr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Float check = sharedPreferencesdata.getFloat("Bữa trưacalorie",0);
                if(check != 0.0){
                    Intent j = new Intent(getContext(), FoodSave.class);
                    j.putExtra("title","Bữa trưa");
                    startActivity(j);
                }else {
                    Intent i = new Intent(getContext(), Add_Food_Activity.class);
                    i.putExtra("title","Bữa trưa");
                    startActivity(i);
                }
            }
        });

        add_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Float check = sharedPreferencesdata.getFloat("Bữa tốicalorie",0);

                if(check != 0.0){
                    Intent j = new Intent(getContext(), FoodSave.class);
                    j.putExtra("title","Bữa tối");
                    startActivity(j);
                }else {
                    Intent i = new Intent(getContext(), Add_Food_Activity.class);
                    i.putExtra("title","Bữa tối");
                    startActivity(i);
                }
            }
        });

        add_bp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Float check = sharedPreferencesdata.getFloat("Bữa phụcalorie",0);
                if(check != 0.0){
                    Intent j = new Intent(getContext(), FoodSave.class);
                    j.putExtra("title","Bữa phụ");
                    startActivity(j);
                }else {
                    Intent i = new Intent(getContext(), Add_Food_Activity.class);
                    i.putExtra("title","Bữa phụ");
                    startActivity(i);
                }
            }
        });

        btn_tang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("infomation", Context.MODE_PRIVATE);
                int socanht = sharedPreferences.getInt("can",0)+1;
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("can",socanht);
                editor.commit();
                tvcanht.setText(socanht + " Kg");
            }
        });

        btn_giam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("infomation", Context.MODE_PRIVATE);
                int socanht = sharedPreferences.getInt("can",0)-1;
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("can",socanht);
                editor.commit();
                tvcanht.setText("" + socanht);
            }
        });

        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),learn1.class);
                i.putExtra("keylearn",1);
                startActivity(i);
            }
        });

        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),learn1.class);
                i.putExtra("keylearn",2);
                startActivity(i);
            }
        });

        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),learn1.class);
                i.putExtra("keylearn",3);
                startActivity(i);
            }
        });

        l4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),learn1.class);
                i.putExtra("keylearn",4);
                startActivity(i);
            }
        });

        l5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),learn1.class);
                i.putExtra("keylearn",5);
                startActivity(i);
            }
        });



        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getlist();

    }

    private void marco(int calorie, int nhucau){
        if(nhucau == -500){
             protein = (int) ((calorie*0.4)/4);
             carb = (int) ((calorie*0.2)/4);
             fat = (int) ((calorie*0.4)/9);
        }else if(nhucau == 0){
             protein = (int) ((calorie*0.3)/4);
             carb = (int) ((calorie*0.35)/4);
             fat = (int) ((calorie*0.35)/9);
        }else {
             protein = (int) ((calorie*0.3)/4);
             carb = (int) ((calorie*0.5)/4);
             fat = (int) ((calorie*0.2)/9);
        }
    }

    private void getlist(){
        Float calobs = sharedPreferencesdata.getFloat("Bữa sángcalorie",0);
        Float calobtr = sharedPreferencesdata.getFloat("Bữa trưacalorie",0);
        Float calobt = sharedPreferencesdata.getFloat("Bữa tốicalorie",0);
        Float calobp = sharedPreferencesdata.getFloat("Bữa phụcalorie",0);
        Float probs = sharedPreferencesdata.getFloat("Bữa sángprotein",0);
        Float probtr = sharedPreferencesdata.getFloat("Bữa trưaprotein",0);
        Float probt = sharedPreferencesdata.getFloat("Bữa tốiprotein",0);
        Float probp = sharedPreferencesdata.getFloat("Bữa phụprotein",0);
        Float carbbs = sharedPreferencesdata.getFloat("Bữa sángcarb",0);
        Float carbbtr = sharedPreferencesdata.getFloat("Bữa trưacarb",0);
        Float carbbt = sharedPreferencesdata.getFloat("Bữa tốicarb",0);
        Float carbbp = sharedPreferencesdata.getFloat("Bữa phụcarb",0);
        Float fatbs = sharedPreferencesdata.getFloat("Bữa sángfat",0);
        Float fatbtr = sharedPreferencesdata.getFloat("Bữa trưafat",0);
        Float fatbt = sharedPreferencesdata.getFloat("Bữa tốifat",0);
        Float fatbp = sharedPreferencesdata.getFloat("Bữa phụfat",0);
        int sobuoc = sharedPreferencesdata.getInt("pedometer",0);
        Log.d("btr",sobuoc+"");


        tvcalobs.setText(calobs + "kc");
        tvcalobtr.setText(calobtr + "kc");
        tvcalobt.setText(calobt + "kc");
        tvcalobp.setText(calobp + "kc");
        tvtieuthu.setText(calobs + calobtr + calobt + calobp + "kcal");
        tvMyCalorie.setText(round((float) ((calorie - (calobs + calobtr + calobt + calobp)) + (sobuoc*0.04)),0)+"kc");
        tvMyProtein.setText(round((protein - (probs + probtr + probt + probp)),0) + "g còn lại");
        tvMyCarb.setText(round((carb - (carbbs + carbbtr + carbbt + carbbp)),0)+"g còn lại");
        tvMyFat.setText(round((fat - (fatbs + fatbtr + fatbt + fatbp)),0)+ "g còn lại");
        tvcaloout.setText((sobuoc*0.04)+"kcal");
        int phantram = (int) (((calobs + calobtr + calobt + calobp)/calorie) * 100);
        int phantramPro = (int) (((probs + probtr + probt + probp) / protein) * 100);
        int phantramCarb = (int) (((carbbs + carbbtr + carbbt + carbbp) / carb) * 100);
        int phantramFat = (int) (((fatbs + fatbtr + fatbt + fatbp) / fat) * 100);


        progressBar.setProgress(phantram);
        progressBar.setMax(100);
        pBpro.setProgress(phantramPro);
        pBpro.setMax(100);
        pbCarb.setProgress(phantramCarb);
        pbCarb.setMax(100);
        pbFat.setProgress(phantramFat);
        pbFat.setMax(100);
    }

    private void getNotes(){
        SharedPreferences sharedPreferences1 = getActivity().getSharedPreferences("User",Context.MODE_PRIVATE);
        String nameUser = sharedPreferences1.getString("username","error");
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("User").child(nameUser).child("Notes");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Diary diary = dataSnapshot.getValue(Diary.class);
                    diaries.add(diary);
                }
                noteAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
}