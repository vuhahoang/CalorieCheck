package com.example.caloriecheck.CustomApdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caloriecheck.FoodInfo;
import com.example.caloriecheck.Model.FoodModel;
import com.example.caloriecheck.R;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class FoodAdapterCalo extends RecyclerView.Adapter<FoodAdapterCalo.ViewHolder> {
    Context context;
    ArrayList<FoodModel> foodModels;
    Intent i;
    SharedPreferences sharedPreferences,sharedPreferencesdata;




    public FoodAdapterCalo(Context context, ArrayList<FoodModel> foodModels,Intent i) {
        this.context = context;
        this.foodModels = foodModels;
        this.i = i;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customapdapterfoodcalo,parent,false);
        return new FoodAdapterCalo.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  FoodAdapterCalo.ViewHolder holder, int position) {
        FoodModel foodModel = foodModels.get(position);
        holder.name.setText(foodModel.getName());
        holder.calorie.setText(foodModel.getCalorie());
        String buaan =  i.getStringExtra("title");
        Gson gson = new Gson();
        String strJsonUser = gson.toJson(foodModel);


        holder.imgadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                String date2 = sdf.format(new Date());
                Log.d("day",date2);
                //Lấy từng bữa
                sharedPreferences = context.getSharedPreferences(buaan+date2,Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String checkexits = sharedPreferences.getString(foodModel.getName(),"");

                if(checkexits.equals("")){
                    editor.putString(foodModel.getName(),strJsonUser);
                    editor.commit();
                }else {
                    FoodModel foodModel2 = gson.fromJson(checkexits,FoodModel.class);
                    FoodModel foodModel3 = new FoodModel(foodModel2.getName(),(Float.parseFloat(foodModel2.getCalorie())+Float.parseFloat(foodModel.getCalorie()))+"",(Float.parseFloat(foodModel2.getProtein())+Float.parseFloat(foodModel.getProtein()))+"",(Float.parseFloat(foodModel2.getCarb())+Float.parseFloat(foodModel.getCarb()))+"",(Float.parseFloat(foodModel2.getFat())+Float.parseFloat(foodModel.getFat()))+"",(Float.parseFloat(foodModel2.getGram())+Float.parseFloat(foodModel.getGram()))+"");
                    String newjson = gson.toJson(foodModel3);
                    editor.putString(foodModel3.getName(),newjson);
                    editor.commit();
                    Log.d("conka",newjson);
                }




                //Tính data tổng
                sharedPreferencesdata = context.getSharedPreferences("data"+date2,Context.MODE_PRIVATE);
                SharedPreferences.Editor editordata = sharedPreferencesdata.edit();
                Float proteinht =  sharedPreferencesdata.getFloat(buaan+"protein",0);
                Float calorieht = sharedPreferencesdata.getFloat(buaan +"calorie",0);
                Float fatht = sharedPreferencesdata.getFloat(buaan+"fat",0);
                Float carbht = sharedPreferencesdata.getFloat(buaan +"carb",0);
                editordata.putFloat(buaan+"protein",Float.parseFloat(foodModel.getProtein()) + proteinht);
                editordata.putFloat(buaan+"calorie",Float.parseFloat(foodModel.getCalorie()) + calorieht);
                editordata.putFloat(buaan+"fat",Float.parseFloat(foodModel.getFat()) + fatht);
                editordata.putFloat(buaan+"carb",Float.parseFloat(foodModel.getCarb()) + carbht);
                editordata.commit();
            }
        });

        holder.layoutfood.setOnClickListener(new View.OnClickListener() {
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
                i.putExtra("gram",foodModel1.getGram());
                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        if(foodModels.size() > 10){
            return 10;
        }else return foodModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,calorie;
        ImageView imgadd;
        LinearLayout layoutfood;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvnamecafc);
            calorie = itemView.findViewById(R.id.tvcalocafc);
            imgadd = itemView.findViewById(R.id.addcafc);
            layoutfood = itemView.findViewById(R.id.layoutfoodinaddfood);

        }
    }
}
