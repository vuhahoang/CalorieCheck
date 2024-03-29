package com.example.caloriecheck.CustomApdapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caloriecheck.Activitiy.DayInPlan;
import com.example.caloriecheck.Model.Plan;
import com.example.caloriecheck.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.ViewHolder> {
    ArrayList<Plan> plans ;
    Context context;

    public PlanAdapter(ArrayList<Plan> plans, Context context) {
        this.plans = plans;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_plan,parent,false);
        return new PlanAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  PlanAdapter.ViewHolder holder, int position) {
         Plan plan = plans.get(position);
         holder.name.setText(plan.getName());
         holder.condition.setText(plan.getCondition());
        Picasso.get().load(plan.getImg()).into( holder.img);
        holder.cvplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DayInPlan.class);
                Bundle args = new Bundle();
                args.putSerializable("Arraylistdayrecipe",plan.getDayrecipes());
                i.putExtra("BUNDLE",args);
                i.putExtra("imgtitle",plan.getImg());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return plans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,condition;
        CardView cvplan;
       ImageView img;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvNamePlan);
            condition = itemView.findViewById(R.id.tvConditionPlan);
            cvplan = itemView.findViewById(R.id.cardviewPlan);
            img = itemView.findViewById(R.id.imgplanbackground);
        }
    }
}
