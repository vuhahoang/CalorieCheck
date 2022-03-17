package com.example.caloriecheck.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.caloriecheck.CustomApdapter.FoodAdapterVer;
import com.example.caloriecheck.Model.FoodModel;
import com.example.caloriecheck.R;

import java.util.ArrayList;


public class PopularFoodTabFragment extends Fragment {
    ArrayList<FoodModel> foodModels;
    FoodAdapterVer foodAdapterVer;
    RecyclerView rcphobien;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_popular_food_tab, container, false);
        rcphobien = view.findViewById(R.id.rcphobien);
        foodModels = new ArrayList<>();
        foodModels.add(new FoodModel(R.drawable.pnglunch,"345","Cá hồi salad","dễ","30 phút"));
        foodModels.add(new FoodModel(R.drawable.pnglunch,"345","Cá hồi salad","dễ","30 phút"));
        foodModels.add(new FoodModel(R.drawable.pnglunch,"345","Cá hồi salad","dễ","30 phút"));
        foodModels.add(new FoodModel(R.drawable.pnglunch,"345","Cá hồi salad","dễ","30 phút"));
        foodModels.add(new FoodModel(R.drawable.pnglunch,"345","Cá hồi salad","dễ","30 phút"));
        foodModels.add(new FoodModel(R.drawable.pnglunch,"345","Cá hồi salad","dễ","30 phút"));
        foodModels.add(new FoodModel(R.drawable.pnglunch,"345","Cá hồi salad","dễ","30 phút"));
        foodAdapterVer = new FoodAdapterVer(foodModels,getContext());
        rcphobien.setLayoutManager(new LinearLayoutManager(getContext()));
        rcphobien.setAdapter(foodAdapterVer);
        return view;
    }
}