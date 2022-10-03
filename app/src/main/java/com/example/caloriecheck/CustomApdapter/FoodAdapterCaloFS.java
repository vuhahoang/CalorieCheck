package com.example.caloriecheck.CustomApdapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.caloriecheck.Activitiy.FoodInfo;
import com.example.caloriecheck.Model.FoodModel;
import com.example.caloriecheck.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FoodAdapterCaloFS extends RecyclerView.Adapter<FoodAdapterCaloFS.ViewHolder> {
    Context context;
    ArrayList<FoodModel> foodModels;
    Intent i;
    SharedPreferences sharedPreferences,sharedPreferencesdata;


    public FoodAdapterCaloFS(Context context, ArrayList<FoodModel> foodModels, Intent i) {
        this.context = context;
        this.foodModels = foodModels;
        this.i = i;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customadapterfoodsave,parent,false);
        return new FoodAdapterCaloFS.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  FoodAdapterCaloFS.ViewHolder holder, int position) {
        FoodModel foodModel = foodModels.get(position);
        holder.tvName.setText(foodModel.getName());
        holder.tvGram.setText(foodModel.getGram());
        holder.tvCalo.setText(foodModel.getCalorie());
        String buaan =  i.getStringExtra("title");

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foodModels.remove(position);
                notifyDataSetChanged();
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                String date2 = sdf.format(new Date());
                sharedPreferences = context.getSharedPreferences(buaan+date2,Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(foodModel.getName());
                editor.commit();

                sharedPreferencesdata = context.getSharedPreferences("data"+date2,Context.MODE_PRIVATE);
                SharedPreferences.Editor editordata = sharedPreferencesdata.edit();
                Float proteinht =  sharedPreferencesdata.getFloat(buaan+"protein",0);
                Float calorieht = sharedPreferencesdata.getFloat(buaan +"calorie",0);
                Float fatht = sharedPreferencesdata.getFloat(buaan+"fat",0);
                Float carbht = sharedPreferencesdata.getFloat(buaan +"carb",0);
                editordata.putFloat(buaan+"protein",proteinht - Float.parseFloat(foodModel.getProtein()));
                editordata.putFloat(buaan+"calorie",calorieht - Float.parseFloat(foodModel.getCalorie()));
                editordata.putFloat(buaan+"fat",fatht - Float.parseFloat(foodModel.getFat()));
                editordata.putFloat(buaan+"carb",carbht - Float.parseFloat(foodModel.getCarb()));
                editordata.commit();


            }
        });

        holder.layout_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, FoodInfo.class);
                FoodModel foodModel1 = foodModels.get(position);
                i.putExtra("nameFood",foodModel1.getName());
                i.putExtra("calorieFood",foodModel1.getCalorie());
                i.putExtra("proteinFood",foodModel1.getProtein());
                i.putExtra("carbFood",foodModel1.getCarb());
                i.putExtra("fatFood",foodModel1.getFat());
                i.putExtra("title",buaan);
                i.putExtra("update",true);
                i.putExtra("gram",foodModel1.getGram());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return foodModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
//        SwipeRevealLayout swipeRevealLayout;
        TextView tvName,tvGram,tvCalo;
        LinearLayout update;
        ImageView delete;
        LinearLayout layout_food;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
//
            tvName = itemView.findViewById(R.id.tvnamecafcsave);
            tvGram = itemView.findViewById(R.id.tvgramcafcsave);
            tvCalo = itemView.findViewById(R.id.tvcalocafcsave);
            update = itemView.findViewById(R.id.layoutfoodinfoodsave);
            delete = itemView.findViewById(R.id.deletecafc);
            layout_food = itemView.findViewById(R.id.layoutfoodinfoodsave);
        }
    }
}
