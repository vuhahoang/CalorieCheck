package com.example.caloriecheck.CustomApdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caloriecheck.Model.FoodModel;
import com.example.caloriecheck.R;

import java.util.ArrayList;

public class FoodAdapterHor extends RecyclerView.Adapter<FoodAdapterHor.ViewHolder> {
    private ArrayList<FoodModel> foodModels;
    private Context context;

    public FoodAdapterHor(ArrayList<FoodModel> foodModels, Context context) {
        this.foodModels = foodModels;
        this.context = context;
    }

    @NonNull

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_layout,parent,false);
        return new FoodAdapterHor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapterHor.ViewHolder holder, int position) {
        FoodModel foodModel = foodModels.get(position);
        holder.tvname.setText(foodModel.getName());
        holder.tvtime.setText(foodModel.getTime());
        holder.tvlever.setText(foodModel.getLever());
        holder.tvkcal.setText(foodModel.getCalorie()+"");
        holder.img.setImageResource(foodModel.getImg());

    }

    @Override
    public int getItemCount() {
        return foodModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvname,tvtime,tvkcal,tvlever;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvname = itemView.findViewById(R.id.tvnamefoodhor);
            tvtime = itemView.findViewById(R.id.tvtimefoodhor);
            tvkcal = itemView.findViewById(R.id.tvkcalfoodhor);
            tvlever = itemView.findViewById(R.id.tvleverfoodhor);
            img = itemView.findViewById(R.id.imgfoodhor);
        }
    }
}
