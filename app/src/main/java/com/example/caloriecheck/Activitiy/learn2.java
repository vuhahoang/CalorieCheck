package com.example.caloriecheck.Activitiy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import com.example.caloriecheck.Model.Dayrecipe;
import com.example.caloriecheck.Model.Plan;
import com.example.caloriecheck.Model.RecipeModel;
import com.example.caloriecheck.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class learn2 extends AppCompatActivity {

    Button btn;
    ArrayList<Dayrecipe> dayrecipes;
    ArrayList<Plan> plans;
    FirebaseDatabase firebaseRecipe;
    DatabaseReference databaseRecipe;
    ArrayList<String> list1,list2,list3,list4,list5,list6,list7,list8,list9,list10,list11,list12,list13,list14,list15,list16,list17,list18,list19,list20,list21,list22,list23,list24,list25,list26,list27,list28,list29,list30;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn2);
        btn = findViewById(R.id.btnaddplan);
        dayrecipes = new ArrayList<>();
        plans = new ArrayList<>();
        list1 = new ArrayList<>();
        list1.add("716429");
        list1.add("40565");
        list1.add("654935");
        list1.add("665303");
        list2 = new ArrayList<>();
        list2.add("35893");
        list2.add("636178");
        list2.add("634496");
        list2.add("639644");
        list3 = new ArrayList<>();
        list3.add("76424");
        list3.add("35893");
        list3.add("654935");
        list3.add("607524");
        list4 = new ArrayList<>();
        list4.add("792072");
        list4.add("867882");
        list4.add("634496");
        list5 = new ArrayList<>();
        list5.add("111571");
        list5.add("418561");
        list5.add("486540");
        list6 = new ArrayList<>();
        list6.add("556926");
        list6.add("40565");
        list6.add("658967");
        list6.add("665303");
        list7 = new ArrayList<>();
        list7.add("206280");
        list7.add("206755");
        list7.add("654935");
        list8 = new ArrayList<>();
        list8.add("716429");
        list8.add("40565");
        list8.add("654935");
        list9 = new ArrayList<>();
        list9.add("35893");
        list9.add("40565");
        list9.add("36199");
        list10 = new ArrayList<>();
        list10.add("867882");
        list10.add("497714");
        list10.add("637624");
        list10.add("632091");
        list11 = new ArrayList<>();
        list11.add("206755");
        list11.add("636601");
        list11.add("35893");
        list12 = new ArrayList<>();
        list12.add("658967");
        list12.add("318375");
        list12.add("654935");
        list13 = new ArrayList<>();
        list13.add("636601");
        list13.add("40565");
        list13.add("636178");
        list13.add("665303");
        list14 = new ArrayList<>();
        list14.add("867882");
        list14.add("497714");
        list14.add("637624");
        list14.add("632091");
        list15 = new ArrayList<>();
        list15.add("206280");
        list15.add("206755");
        list15.add("654935");
        list16 = new ArrayList<>();
        list16.add("556926");
        list16.add("40565");
        list16.add("658967");
        list16.add("665303");
        list17 = new ArrayList<>();
        list17.add("35893");
        list17.add("636178");
        list17.add("634496");
        list17.add("639644");
        list18 = new ArrayList<>();
        list18.add("716429");
        list18.add("40565");
        list18.add("654935");
        list18.add("665303");
        list19 = new ArrayList<>();
        list19.add("35893");
        list19.add("40565");
        list19.add("36199");
        list20 = new ArrayList<>();
        list20.add("35893");
        list20.add("636178");
        list20.add("634496");
        list20.add("639644");
        list21 = new ArrayList<>();
        list21.add("206280");
        list21.add("206755");
        list21.add("654935");
        list22 = new ArrayList<>();
        list22.add("556926");
        list22.add("40565");
        list22.add("658967");
        list22.add("665303");
        list23 = new ArrayList<>();
        list23.add("792072");
        list23.add("867882");
        list23.add("634496");
        list24 = new ArrayList<>();
        list24.add("556926");
        list24.add("40565");
        list24.add("658967");
        list24.add("665303");
        list25 = new ArrayList<>();
        list25.add("111571");
        list25.add("418561");
        list25.add("486540");
        list26 = new ArrayList<>();
        list26.add("658967");
        list26.add("318375");
        list26.add("654935");
        list27 = new ArrayList<>();
        list27.add("792072");
        list27.add("867882");
        list27.add("634496");
        list28 = new ArrayList<>();
        list28.add("206280");
        list28.add("206755");
        list28.add("654935");
        list29 = new ArrayList<>();
        list29.add("556926");
        list29.add("40565");
        list29.add("658967");
        list29.add("665303");
        list30 = new ArrayList<>();
        list30.add("76424");
        list30.add("35893");
        list30.add("654935");
        list30.add("607524");


        firebaseRecipe = FirebaseDatabase.getInstance();
        databaseRecipe = firebaseRecipe.getReference("Plan");

        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/716429-556x370.jpg","Ngày 1",list1));
        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/35893-556x370.jpg","Ngày 2",list2));
        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/76424-556x370.jpeg","Ngày 3",list3));
        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/792072-556x370.jpg","Ngày 4",list4));
        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/111571-556x370.jpg","Ngày 5",list5));
        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/556926-556x370.jpg","Ngày 6",list6));
        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/206280-556x370.jpg","Ngày 7",list7));
        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/716429-556x370.jpg","Ngày 8",list8));
        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/35893-556x370.jpg","Ngày 9",list9));
        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/867882-556x370.jpg","Ngày 10",list10));
        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/206755-556x370.jpg","Ngày 11",list11));
        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/658967-556x370.jpg","Ngày 12",list12));
        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/636601-556x370.jpg","Ngày 13",list13));
        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/867882-556x370.jpg","Ngày 14",list14));
        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/206280-556x370.jpg","Ngày 15",list15));
        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/556926-556x370.jpg","Ngày 16",list16));
        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/35893-556x370.jpg","Ngày 17",list17));
        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/716429-556x370.jpg","Ngày 18",list18));
        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/35893-556x370.jpg","Ngày 19",list19));
        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/35893-556x370.jpg","Ngày 20",list20));
        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/206280-556x370.jpg","Ngày 21",list21));
        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/556926-556x370.jpg","Ngày 22",list22));
        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/792072-556x370.jpg","Ngày 23",list23));
        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/556926-556x370.jpg","Ngày 24",list24));
        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/111571-556x370.jpg","Ngày 25",list25));
        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/658967-556x370.jpg","Ngày 26",list26));
        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/792072-556x370.jpg","Ngày 27",list27));
        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/206280-556x370.jpg","Ngày 28",list28));
        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/556926-556x370.jpg","Ngày 29",list29));
        dayrecipes.add(new Dayrecipe("https://spoonacular.com/recipeImages/76424-556x370.jpg","Ngày 30",list30));

        plans.add(new Plan("Giảm cân trong 30 ngày","Không hạn chế ăn uống","https://www.englishclub.com/images/vocabulary/food/health/healthy.jpg",dayrecipes));
        plans.add(new Plan("28 Ngày thử thách thon gọn","Không hạn chế ăn uống","https://dizzybusyandhungry.com/wp-content/uploads/2018/12/healthier-spaghetti-2.jpg",dayrecipes));


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseRecipe.setValue(plans);
            }
        });









    }
}