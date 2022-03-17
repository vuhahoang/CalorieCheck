package com.example.caloriecheck.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.caloriecheck.Model.ContentPager;
import com.example.caloriecheck.R;


public class FragmentLearn extends Fragment {

    TextView title,c1,c2;
    ConstraintLayout layout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_learn, container, false);
        title = view.findViewById(R.id.titlelearn);
        c1 = view.findViewById(R.id.content1);
        c2 = view.findViewById(R.id.content2);
        layout = view.findViewById(R.id.layout_abc);

        Bundle bundle = getArguments();
        ContentPager contentpager = (ContentPager) bundle.get("object");

        title.setText(contentpager.getTitle());
        c1.setText(contentpager.getContent1());
        c2.setText(contentpager.getContent2());
        layout.setBackgroundColor(Color.parseColor(contentpager.getColor()));
        c1.setTextColor(Color.parseColor(contentpager.getColortext()));
        c2.setTextColor(Color.parseColor(contentpager.getColortext()));

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}