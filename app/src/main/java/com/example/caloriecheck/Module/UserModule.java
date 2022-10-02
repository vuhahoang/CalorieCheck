package com.example.caloriecheck.Module;

import com.example.caloriecheck.Repository.IUserRepository;
import com.example.caloriecheck.Repository.Imp.UserRepositoryImp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class UserModule {
//    @Provides
//    @Singleton
//    FirebaseDatabase provideDB(){
//        return FirebaseDatabase.getInstance();
//    }
//    @Provides
//    DatabaseReference provideDBRef(FirebaseDatabase rootNode){
//        return rootNode.getReference("User");
//    }
    @Provides
    IUserRepository provideUserRepo(FirebaseDatabase firebaseDatabase){
        return new UserRepositoryImp(firebaseDatabase);
    }
}
