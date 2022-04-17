package com.example.caloriecheck.CustomApdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caloriecheck.Model.Ingredients;
import com.example.caloriecheck.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {

    ArrayList<Ingredients> ingredients;
    Context context;

    public IngredientsAdapter(ArrayList<Ingredients> ingredients, Context context) {
        this.ingredients = ingredients;
        this.context = context;
    }

    @NonNull
    @Override
    public IngredientsAdapter.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_ingredients,parent,false);
        return new IngredientsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  IngredientsAdapter.ViewHolder holder, int position) {
         Ingredients ing = ingredients.get(position);
         holder.tvname.setText(ing.getName());
         holder.tvcount.setText(ing.getMeasures());
        Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/"+ing.getImage()).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvname,tvcount;
        ImageView img;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            tvname = itemView.findViewById(R.id.tvnameingredients);
            tvcount = itemView.findViewById(R.id.tvcountingredients);
            img = itemView.findViewById(R.id.imgingredients);
        }
    }
}
