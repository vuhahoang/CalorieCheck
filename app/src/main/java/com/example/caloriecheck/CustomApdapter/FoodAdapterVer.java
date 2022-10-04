package com.example.caloriecheck.CustomApdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caloriecheck.Activitiy.Inforecipe;
import com.example.caloriecheck.Model.RecipeModel;
import com.example.caloriecheck.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FoodAdapterVer extends RecyclerView.Adapter<FoodAdapterVer.ViewHolder> {
    private ArrayList<RecipeModel> recipeModels;
    private Context context;

    public FoodAdapterVer(ArrayList<RecipeModel> recipeModels, Context context) {
        this.recipeModels = recipeModels;
        this.context = context;
    }

    @NonNull

    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_layout_ver,parent,false);
        return new FoodAdapterVer.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapterVer.ViewHolder holder, int position) {
        RecipeModel recipeModel = recipeModels.get(position);
        if(recipeModel.getTitle()==null){
            holder.tvname.setText("Name: Ngày"+position+1);
        }else {
            holder.tvname.setText("Name: " +recipeModel.getTitle());
        }

        holder.tvtime.setText("Time: " + recipeModel.getRim()+" phút");
        holder.tvlever.setText("Level: Dễ");
        holder.tvkcal.setText("Calorie: " + recipeModel.getCalorie()+" kcal");
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
        CardView cvlayout;
        TextView tvname,tvtime,tvkcal,tvlever;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvname = itemView.findViewById(R.id.tvnamefoodver);
            tvtime = itemView.findViewById(R.id.tvtimefoodver);
            tvkcal = itemView.findViewById(R.id.tvkcalfoodver);
            tvlever = itemView.findViewById(R.id.tvleverfoodver);
            img = itemView.findViewById(R.id.imgfoodver);
            cvlayout = itemView.findViewById(R.id.cardViewLayoutFoodVer);

        }
    }
}
