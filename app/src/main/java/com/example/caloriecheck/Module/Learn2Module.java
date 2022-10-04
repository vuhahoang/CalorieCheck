//package com.example.caloriecheck.Module;
//
//import com.example.caloriecheck.Repository.ILearn2Repository;
//import com.example.caloriecheck.Repository.Imp.Learn2RepositoryImp;
//import com.google.firebase.database.FirebaseDatabase;
//
//import dagger.Module;
//import dagger.Provides;
//import dagger.hilt.InstallIn;
//import dagger.hilt.components.SingletonComponent;
//
//@Module
//@InstallIn(SingletonComponent.class)
//public class Learn2Module {
//    @Provides
//    ILearn2Repository provideLearn2Repo(FirebaseDatabase firebaseDatabase){
//        return new Learn2RepositoryImp(firebaseDatabase);
//    }
//}
