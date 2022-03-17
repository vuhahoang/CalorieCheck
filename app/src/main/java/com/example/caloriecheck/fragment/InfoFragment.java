package com.example.caloriecheck.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.caloriecheck.Login;
import com.example.caloriecheck.R;


public class InfoFragment extends Fragment {
    SharedPreferences sharedPreferences;
    TextView siandsu,tvSaveprofile;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         View view = inflater.inflate(R.layout.fragment_info, container, false);
         siandsu = view.findViewById(R.id.tvsignuporlogin);
         tvSaveprofile = view.findViewById(R.id.tvsaveprofile);
       
        sharedPreferences = getContext().getSharedPreferences("User", Context.MODE_PRIVATE);
        Boolean checklogin = sharedPreferences.getBoolean("checklogin",false);

        if (checklogin){
            siandsu.setVisibility(View.GONE);
            tvSaveprofile.setText("Xin chào " + sharedPreferences.getString("name",""));
        }


        siandsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), Login.class);
                startActivity(i);
            }
        });

        
        return view;
    }
}