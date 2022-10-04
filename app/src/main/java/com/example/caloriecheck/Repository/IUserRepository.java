package com.example.caloriecheck.Repository;

import android.widget.EditText;

import com.example.caloriecheck.Model.Input.SignUpInputModel;
import com.example.caloriecheck.Model.User;
import com.example.caloriecheck.Module.UserModule;
import com.google.firebase.database.DatabaseReference;

import dagger.Component;

public interface IUserRepository {
    User signUp(SignUpInputModel inputModel, EditText usernameField);

    DatabaseReference getRecentlyUser(String userName);

    DatabaseReference getUserDb();
}
