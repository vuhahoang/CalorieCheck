package com.example.caloriecheck.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caloriecheck.CustomApdapter.FoodAdapterVer;
import com.example.caloriecheck.Model.FoodModel;
import com.example.caloriecheck.R;

import java.util.ArrayList;

public class NewFoodTabFragment extends Fragment {
    ArrayList<FoodModel> foodModels;
    FoodAdapterVer foodAdapterVer;
    RecyclerView rcnew;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.tab_fragment, container, false);
        rcnew = view.findViewById(R.id.rcnewfood);
        foodModels = new ArrayList<>();
        foodModels.add(new FoodModel(R.drawable.pnglunch,"345","Cá hồi salad","dễ","30 phút"));
        foodModels.add(new FoodModel(R.drawable.pnglunch,"345","Cá hồi salad","dễ","30 phút"));
        foodModels.add(new FoodModel(R.drawable.pnglunch,"345","Cá hồi salad","dễ","30 phút"));
        foodModels.add(new FoodModel(R.drawable.pnglunch,"345","Cá hồi salad","dễ","30 phút"));
        foodModels.add(new FoodModel(R.drawable.pnglunch,"345","Cá hồi salad","dễ","30 phút"));
        foodModels.add(new FoodModel(R.drawable.pnglunch,"345","Cá hồi salad","dễ","30 phút"));
        foodModels.add(new FoodModel(R.drawable.pnglunch,"345","Cá hồi salad","dễ","30 phút"));
        foodAdapterVer = new FoodAdapterVer(foodModels,getContext());
        rcnew.setLayoutManager(new LinearLayoutManager(getContext()));
        rcnew.setAdapter(foodAdapterVer);
        return view;
    }
}
