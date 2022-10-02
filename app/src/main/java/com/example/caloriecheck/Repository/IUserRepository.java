package com.example.caloriecheck.Repository;

import android.widget.EditText;

import com.example.caloriecheck.Model.Input.SignUpInputModel;
import com.example.caloriecheck.Model.User;

public interface IUserRepository {
    public User signUp(SignUpInputModel inputModel, EditText usernameField);
}
