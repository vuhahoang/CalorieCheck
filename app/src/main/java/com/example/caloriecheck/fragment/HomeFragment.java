package com.example.caloriecheck.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.caloriecheck.CustomApdapter.PlanAdapter;
import com.example.caloriecheck.Model.Plan;
import com.example.caloriecheck.R;
//import com.example.caloriecheck.Activitiy.learn2;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    RecyclerView rc;
    ArrayList<Plan> plans;
    PlanAdapter planAdapter;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    TextView addplan;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rc = view.findViewById(R.id.rcplan);
        addplan = view.findViewById(R.id.tvaddPlanadmin);
        plans = new ArrayList<>();
        planAdapter = new PlanAdapter(plans,getContext());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Plan");
        rc.setAdapter(planAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {


                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Plan plan = dataSnapshot.getValue(Plan.class);
                    plans.add(plan);
                    planAdapter.notifyDataSetChanged();




                }


            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });

        addplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(getContext(),learn2.class);
//                startActivity(i);
            }
        });




        return view;
    }
}