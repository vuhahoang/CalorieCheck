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

import com.example.caloriecheck.Model.Dayrecipe;
import com.example.caloriecheck.R;
import com.example.caloriecheck.RecipeinDay;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DayPlan extends RecyclerView.Adapter<DayPlan.ViewHolder> {

    ArrayList<Dayrecipe> dayrecipes;
    Context context;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference ;

    public DayPlan(ArrayList<Dayrecipe> dayrecipes, Context context) {
        this.dayrecipes = dayrecipes;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dayrecipelayout,parent,false);
        return new DayPlan.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  DayPlan.ViewHolder holder, int position) {
         Dayrecipe dayrecipe = dayrecipes.get(position);
         holder.tvName.setText(dayrecipe.getDay());
         int number = dayrecipe.getRecipeModels().size();
         holder.tvCount.setText(number + " Công thức");
        Picasso.get().load(dayrecipe.getImg()).into( holder.img);

         for(int i = 0;i < dayrecipe.getRecipeModels().size();i++){

             String k = dayrecipe.getRecipeModels().get(i);


             databaseReference = firebaseDatabase.getReference("Recipe").child(k);
             databaseReference.addValueEventListener(new ValueEventListener() {
                 @Override
                 public void onDataChange(@NonNull  DataSnapshot snapshot) {

                      int calo = snapshot.child("calorie").getValue(Integer.class);

                     holder.tvCalorie.setText(calo+" kcal");
                 }

                 @Override
                 public void onCancelled(@NonNull  DatabaseError error) {

                 }
             });




         }

         holder.cv.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent i = new Intent(context, RecipeinDay.class);
                 i.putStringArrayListExtra("idrecipe",dayrecipe.getRecipeModels());
                 i.putExtra("tvtitle",dayrecipe.getDay());
                 i.putExtra("imgtitle",dayrecipe.getImg());
                 context.startActivity(i);
             }
         });


    }

    @Override
    public int getItemCount() {
        return dayrecipes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvCount,tvCalorie;
        ImageView img;
        CardView cv;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvDayinPlanLayout);
            tvCount = itemView.findViewById(R.id.tvcountrecipeDayinPlanlayout);
            tvCalorie = itemView.findViewById(R.id.tvcalorieDayinPlanlayout);
            img = itemView.findViewById(R.id.imgDayinplanlayout);
            cv = itemView.findViewById(R.id.cvdayrecipelayout);
        }
    }
}
