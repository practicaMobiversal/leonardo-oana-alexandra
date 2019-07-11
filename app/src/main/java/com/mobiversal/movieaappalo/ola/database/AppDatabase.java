package com.mobiversal.movieaappalo.ola.database;

public class AppDatabase {

private static AppDatabase instance;

public static AppDatabase getInstance(){
    if(instance == null){
        instance = new AppDatabase();
    }
    return instance;
}
}
