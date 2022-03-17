package com.example.caloriecheck.CustomApdapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.caloriecheck.Model.ContentPager;
import com.example.caloriecheck.fragment.FragmentLearn;

import java.util.ArrayList;

public class FragmentAdapter extends FragmentStateAdapter {
    ArrayList<ContentPager> contentPagers;

    public FragmentAdapter(@NonNull  FragmentActivity fragmentActivity,ArrayList<ContentPager> contentPagers) {
        super(fragmentActivity);
        this.contentPagers = contentPagers;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        ContentPager contentPager = contentPagers.get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object",contentPager);
        FragmentLearn fragmentLearn =new FragmentLearn();
        fragmentLearn.setArguments(bundle);
        return fragmentLearn;
    }

    @Override
    public int getItemCount() {
        return contentPagers.size();
    }
}
