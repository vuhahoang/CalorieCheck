package com.example.caloriecheck.Activitiy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.caloriecheck.Model.Input.Builder.SignUpInputModelBuilder;
import com.example.caloriecheck.R;
import com.example.caloriecheck.Repository.IUserRepository;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class Signup extends AppCompatActivity {

    EditText edName,edUserName,edPassword,edEmail;
    Button btnSignUp;
    @Inject
    IUserRepository userRepository;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        edName = findViewById(R.id.ednamesu);
        edUserName = findViewById(R.id.edusersu);
        edPassword = findViewById(R.id.edpasswordsu);
        edEmail = findViewById(R.id.edemailsu);
        btnSignUp = findViewById(R.id.signupbtn);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateName() | !validateUsername() | !validateEmail() | !validatePassword() ){
                    return;
                }else {
                    String name = edName.getText().toString().trim();
                    String username = edUserName.getText().toString().trim();
                    String email = edEmail.getText().toString().trim();
                    String password = edPassword.getText().toString().trim();
                    sharedPreferences = getSharedPreferences("infomation",MODE_PRIVATE);
                    int nhucau = sharedPreferences.getInt("nhucau",0);
                    double r = sharedPreferences.getFloat("R",0);
                    int can = sharedPreferences.getInt("can",0);
                    int chieucao = sharedPreferences.getInt("chieucao",0);
                    int k = sharedPreferences.getInt("K",0);
                    int tuoi = sharedPreferences.getInt("tuoi",0);
                    int canmt = sharedPreferences.getInt("canmoi",0);
                    int canbt = sharedPreferences.getInt("canbd",0);
                    userRepository.signUp(new SignUpInputModelBuilder().setName(name)
                            .setUsername(username)
                            .setEmail(email)
                            .setPassword(password)
                            .setNhucau(nhucau)
                            .setR(r)
                            .setTuoi(tuoi)
                            .setCan(can)
                            .setChieucao(chieucao)
                            .setK(k)
                            .setCanmt(canmt)
                            .setCanbt(canbt).createSignUpInputModel(),edUserName);
                    sharedPreferences = getSharedPreferences("User",MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("name",name);
                                editor.putString("username",username);
                                editor.putBoolean("checklogin",true);

                                editor.commit();
                                Intent i = new Intent(Signup.this,Home.class);
                                startActivity(i);
                }
            }
        });
    }

    private Boolean validateName() {
        String val = edName.getText().toString().trim();

        if (val.isEmpty()) {
            edName.setError("Tên không được trống");
            return false;
        }
        else {
            edName.setError(null);

            return true;
        }
    }

    private Boolean validateUsername() {
        String val = edUserName.getText().toString().trim();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if (val.isEmpty()) {
            edUserName.setError("Tài khoản không được trống");
            return false;
        } else if (val.length() >= 15) {
            edUserName.setError("Tài khoản quá dài");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            edUserName.setError("Không được chứa khoảng cách");
            return false;
        } else {
            edUserName.setError(null);

            return true;
        }
    }

    private Boolean validateEmail() {
        String val = edEmail.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            edEmail.setError("Email không được bỏ trống");
            return false;
        } else if (!val.matches(emailPattern)) {
            edEmail.setError("Email không hợp lệ");
            return false;
        } else {
            edEmail.setError(null);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = edPassword.getText().toString().trim();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter

                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            edPassword.setError("Mật khẩu không được trống");
            return false;
        } else if (!val.matches(passwordVal)) {
            edPassword.setError("Mật khẩu quá yếu");
            return false;
        } else {
            edPassword.setError(null);
            return true;
        }
    }
}