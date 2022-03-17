package com.example.caloriecheck.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caloriecheck.CustomApdapter.FoodAdapterHor;
import com.example.caloriecheck.CustomApdapter.FoodAdapterVer;
import com.example.caloriecheck.Model.FoodModel;
import com.example.caloriecheck.R;

import java.util.ArrayList;

public class AllFoodTabFragment extends Fragment {
    ArrayList<FoodModel> foodModels;
    FoodAdapterHor foodAdapterHor;
    RecyclerView rcphobien,rckiemsoat,rcmonmoi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.all_tab_fragment, container, false);
        rcphobien = view.findViewById(R.id.rcphobieninall);
        rckiemsoat = view.findViewById(R.id.rckiemsoatinall);
        rcmonmoi = view.findViewById(R.id.rcnewfoodinall);
        foodModels = new ArrayList<>();
        foodModels.add(new FoodModel(R.drawable.pnglunch,"345","Cá hồi salad","dễ","30 phút"));
        foodModels.add(new FoodModel(R.drawable.pnglunch,"345","Cá hồi salad","dễ","30 phút"));
        foodModels.add(new FoodModel(R.drawable.pnglunch,"345","Cá hồi salad","dễ","30 phút"));
        foodModels.add(new FoodModel(R.drawable.pnglunch,"345","Cá hồi salad","dễ","30 phút"));
        foodModels.add(new FoodModel(R.drawable.pnglunch,"345","Cá hồi salad","dễ","30 phút"));
        foodModels.add(new FoodModel(R.drawable.pnglunch,"345","Cá hồi salad","dễ","30 phút"));
        foodModels.add(new FoodModel(R.drawable.pnglunch,"345","Cá hồi salad","dễ","30 phút"));
        foodAdapterHor = new FoodAdapterHor(foodModels,getContext());

        rcphobien.setAdapter(foodAdapterHor);
        rckiemsoat.setAdapter(foodAdapterHor);
        rcmonmoi.setAdapter(foodAdapterHor);

        return view;
    }
}
