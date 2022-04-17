package com.example.caloriecheck.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.caloriecheck.AddRecipeAdmin;
import com.example.caloriecheck.R;
import com.example.caloriecheck.RecipesAdapter;
import com.google.android.material.tabs.TabLayout;


public class CookFragment extends Fragment {
    ViewPager viewPager;
   TabLayout tabLayout;
   TextView admin;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cook, container, false);
        viewPager = view.findViewById(R.id.view_pager);
        tabLayout = view.findViewById(R.id.tab_layout);
        admin = view.findViewById(R.id.adminrecipe);
        tabLayout.addTab(tabLayout.newTab().setText("Tất cả"));
        tabLayout.addTab(tabLayout.newTab().setText("Mới"));
        tabLayout.addTab(tabLayout.newTab().setText("Yêu thích"));
        tabLayout.addTab(tabLayout.newTab().setText("Phổ biến"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final RecipesAdapter adapter = new RecipesAdapter(getChildFragmentManager(),getContext(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), AddRecipeAdmin.class);
                startActivity(i);
            }
        });



        return view;
    }




}