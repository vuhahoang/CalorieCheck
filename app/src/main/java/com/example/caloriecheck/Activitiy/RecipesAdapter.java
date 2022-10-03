package com.example.caloriecheck.Activitiy;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.caloriecheck.fragment.AllFoodTabFragment;
import com.example.caloriecheck.fragment.LoveFoodTabFragment;
import com.example.caloriecheck.fragment.NewFoodTabFragment;
import com.example.caloriecheck.fragment.PopularFoodTabFragment;

public class RecipesAdapter extends FragmentPagerAdapter {

    private Context context;
    int totalTab;

    public RecipesAdapter(FragmentManager fm,Context context,int totalTab){
        super(fm);
        this.context = context;
        this.totalTab = totalTab;
    }

    @Override
    public int getCount() {
        return 4;
    }

    public Fragment getItem(int position){
        switch (position){
            case 0:
                AllFoodTabFragment allFoodTabFragment = new AllFoodTabFragment();
                return allFoodTabFragment;
            case 1:
                NewFoodTabFragment newFoodTabFragment = new NewFoodTabFragment();
                return newFoodTabFragment;
            case 2:
            LoveFoodTabFragment loveFoodTabFragment = new LoveFoodTabFragment();
            return loveFoodTabFragment;
            case 3:
            PopularFoodTabFragment popularFoodTabFragment = new PopularFoodTabFragment();
            return popularFoodTabFragment;

            default:
                return new AllFoodTabFragment();

        }
    }

    @Nullable

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Tất cả";
            case 1:
                return "Mới";
            case 2:
                return "Yêu thích";
            case 3:
                return "Phổ biến";
            default:
                return "Tất cả";
        }
    }
}
