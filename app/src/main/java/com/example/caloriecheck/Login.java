package com.example.caloriecheck;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    Button signup,signin;
    EditText edUser;
    EditText edPass;
    SharedPreferences sharedPreferences,sharedPreferences1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signup = findViewById(R.id.signupwithCalorieCheck);
        edUser = findViewById(R.id.edusersi);
        edPass = findViewById(R.id.edpasswordsi);
        signin = findViewById(R.id.login);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this,Signup.class);
                startActivity(i);
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateUsername() | !validatePassword()){
                    return;
                }else isUser();
            }
        });
    }

    private Boolean validateUsername() {
        String val = edUser.getText().toString().trim();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if (val.isEmpty()) {
            edUser.setError("Tài khoản không được chống");
            return false;
        } else if (val.length() >= 15) {
           edUser.setError("Tài khoản quá dài");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            edUser.setError("Không được chứa khoảng cách");
            return false;
        } else {
            edUser.setError(null);

            return true;
        }
    }

    private Boolean validatePassword() {
        String val = edPass.getText().toString().trim();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter

                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            edPass.setError("Mật khẩu không được chống");
            return false;
        } else if (!val.matches(passwordVal)) {
            edPass.setError("Mật khẩu quá yếu");
            return false;
        } else {
            edPass.setError(null);
            return true;
        }
    }

    private void isUser(){
        final String Username = edUser.getText().toString().trim();
        final  String Password = edPass.getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
        Query query = reference.orderByChild("username").equalTo(Username);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                if(snapshot.exists()){
                    edUser.setError(null);
                    String passfromDB = snapshot.child(Username).child("password").getValue(String.class);
                    int agefromDB = snapshot.child(Username).child("age").getValue(int.class);
                    int heightfromDB = snapshot.child(Username).child("height").getValue(int.class);
                    int kfromDB = snapshot.child(Username).child("k").getValue(int.class);
                    float rfromDB =  snapshot.child(Username).child("r").getValue(float.class);
                    int targetDB = snapshot.child(Username).child("target").getValue(int.class);
                    int weightDB = snapshot.child(Username).child("weight").getValue(int.class);
                    int weightTargetDB = snapshot.child(Username).child("weightTarget").getValue(int.class);
                    String nameDB = snapshot.child(Username).child("name").getValue(String.class);



                    if(passfromDB.equals(Password)){
                        sharedPreferences = getSharedPreferences("User",MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("name",nameDB);
                        editor.putString("username",Username);
                        editor.putBoolean("checklogin",true);
                        editor.commit();
                        sharedPreferences1 = getSharedPreferences("infomation",MODE_PRIVATE);
                        SharedPreferences.Editor editor1 = sharedPreferences1.edit();
                        editor1.putInt("can",weightDB);
                        editor1.putInt("chieucao",heightfromDB);
                        editor1.putFloat("R",rfromDB);
                        editor1.putInt("nhucau",targetDB);
                        editor1.putInt("tuoi",agefromDB);
                        editor1.putInt("canmoi",weightTargetDB);
                        editor1.putInt("K",kfromDB);
                        editor1.putBoolean("checkfull",true);
                        editor1.commit();
                        Intent i = new Intent(Login.this,Home.class);
                        startActivity(i);
                    }else {
                        edPass.setError("Mật khẩu sai");
                    }


                }else {
                    edUser.setError("Tài khoản không tồn tại");
                }
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });
    }
}