package com.example.caloriecheck.Activitiy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.caloriecheck.Model.Ingredients;
import com.example.caloriecheck.Model.RecipeModel;
import com.example.caloriecheck.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddRecipeAdmin extends AppCompatActivity {
    EditText ed,tag,type;
    Button add,fun;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ArrayList<RecipeModel> recipeModels;
    ArrayList<Ingredients> ingredients;
    String url1,url2;
    RequestQueue requestQueue;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe_admin);
        ed = findViewById(R.id.edaddrecipe);
        add = findViewById(R.id.btnaddrecipe);
        fun = findViewById(R.id.addforfun);
        tag = findViewById(R.id.edtaginaddrecipe);
        type = findViewById(R.id.edtypeinaddrecipe);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Recipe");
        recipeModels = new ArrayList<>();
        ingredients = new ArrayList<>();
        id = ed.getText().toString().trim();



         add.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 id = ed.getText().toString().trim();
                 getRecipe();

             }
         });



    }

    private void getRecipe(){
        requestQueue = Volley.newRequestQueue(AddRecipeAdmin.this);
        url1 = "https://api.spoonacular.com/recipes/" + id + "/information?includeNutrition=false&apiKey=9874a23b6ae3452f88bca9546a5ccda6";
        url2 = "https://api.spoonacular.com/recipes/" + id +  "/nutritionWidget.json?apiKey=9874a23b6ae3452f88bca9546a5ccda6";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    int idt = jsonObject.getInt("id");
                    String title = jsonObject.getString("title");
                    String image = jsonObject.getString("image");
                    String instructions = jsonObject.getString("instructions");
                    int rim = jsonObject.getInt("readyInMinutes");
                    JSONArray IngredientsObject = jsonObject.getJSONArray("extendedIngredients");
                    for(int i =0 ; i<IngredientsObject.length();i++){
                        JSONObject jsonObject1 =IngredientsObject.getJSONObject(i);
                        JSONObject jsonObject2 = jsonObject1.getJSONObject("measures").getJSONObject("us");

                        Ingredients ingredient = new Ingredients(jsonObject1.getString("name"),jsonObject2.getString("amount")+jsonObject2.getString("unitLong"),jsonObject1.getString("image"));
                        ingredients.add(ingredient);
                    }
                    StringRequest stringRequest1 = new StringRequest(Request.Method.GET, url2, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response.toString());
                                String calories = jsonObject.getString("calories");
                                String carbs = jsonObject.getString("carbs");
                                String fat = jsonObject.getString("fat");
                                String protein = jsonObject.getString("protein");
                                int calo = Integer.parseInt(removeLastChar(calories));
                                int carb = Integer.parseInt(removeLastChar(carbs));
                                int fatint = Integer.parseInt(removeLastChar(fat));
                                int pro = Integer.parseInt(removeLastChar(protein));
                                RecipeModel recipeModel = new RecipeModel(calo,carb,fatint,idt,pro,rim,0,image,instructions,title,type.getText().toString(),tag.getText().toString(),ingredients);
                                databaseReference.child(idt+"").setValue(recipeModel);



                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });

                    requestQueue.add(stringRequest1);



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(stringRequest);

//        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, url2, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject jsonObject = new JSONObject(response.toString());
//                    String calories = jsonObject.getString("calories");
//                    String carbs = jsonObject.getString("carbs");
//                    String fat = jsonObject.getString("fat");
//                    String protein = jsonObject.getString("protein");
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        })
//        {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> headers = new HashMap<>();
//                headers.put("Content-Type", "application/json");
//                headers.put("apiKey", "9874a23b6ae3452f88bca9546a5ccda6");
//                return headers;
//
//            }
//        };
//        requestQueue.add(stringRequest1);

    }

    private String removeLastChar(String str){
        return str.substring(0,str.length() - 1);
    }
}