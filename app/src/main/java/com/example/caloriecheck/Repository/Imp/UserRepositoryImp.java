package com.example.caloriecheck.Repository.Imp;

import android.widget.EditText;

import androidx.annotation.NonNull;

import com.example.caloriecheck.Model.Input.SignUpInputModel;
import com.example.caloriecheck.Model.User;
import com.example.caloriecheck.Repository.IUserRepository;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import javax.inject.Inject;


public class UserRepositoryImp implements IUserRepository{
    private final FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    @Inject
    public UserRepositoryImp(FirebaseDatabase firebaseDatabase) {
        this.firebaseDatabase = firebaseDatabase;
    }

    @Override
    public User signUp(SignUpInputModel inputModel, EditText usernameField) {
        reference = firebaseDatabase.getReference("User");
        Query checkUser = reference.orderByChild("username").equalTo(inputModel.getUsername());
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    usernameField.setError("Tài khoản đã tồn tại");
                }else {
                    User user = new User(inputModel.getName(),inputModel.getUsername(),inputModel.getEmail(),inputModel.getPassword(), inputModel.getTuoi(), inputModel.getChieucao(), inputModel.getCan(), inputModel.getNhucau(), inputModel.getCanmt(), inputModel.getR(), inputModel.getK(), inputModel.getCanbt());
                    reference.child(inputModel.getUsername()).setValue(user);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return null;
    }

    @Override
    public DatabaseReference getRecentlyUser(String userName) {
        return this.firebaseDatabase.getReference("User").child(userName).child("Recently");
    }

    @Override
    public DatabaseReference getUserDb() {
        return this.firebaseDatabase.getReference("User");
    }
}
