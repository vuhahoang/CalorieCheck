package com.example.caloriecheck.CustomApdapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caloriecheck.Inforecipe;
import com.example.caloriecheck.Model.FoodModel;
import com.example.caloriecheck.Model.RecipeModel;
import com.example.caloriecheck.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FoodAdapterHor extends RecyclerView.Adapter<FoodAdapterHor.ViewHolder> {
    private ArrayList<RecipeModel> recipeModels;
    private Context context;

    public FoodAdapterHor(ArrayList<RecipeModel> recipeModels, Context context) {
        this.recipeModels = recipeModels;
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
        RecipeModel recipeModel = recipeModels.get(position);
        holder.tvname.setText("Name: " +recipeModel.getTitle());
        holder.tvtime.setText(recipeModel.getRim()+"phút");
        holder.tvlever.setText("Dễ");
        holder.tvkcal.setText(recipeModel.getCalorie()+"kcal");
        Picasso.get().load(recipeModel.getImage()).into(holder.img);
        holder.cvlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Inforecipe.class);
                i.putExtra("keyid",recipeModel.getId());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return recipeModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvname,tvtime,tvkcal,tvlever;
        ImageView img;
        CardView cvlayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvname = itemView.findViewById(R.id.tvnamefoodhor);
            tvtime = itemView.findViewById(R.id.tvtimefoodhor);
            tvkcal = itemView.findViewById(R.id.tvkcalfoodhor);
            tvlever = itemView.findViewById(R.id.tvleverfoodhor);
            img = itemView.findViewById(R.id.imgfoodhor);
            cvlayout = itemView.findViewById(R.id.cardviewlayoutFoodHor);
            tvname.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            tvname.setSelected(true);
        }
    }
}
