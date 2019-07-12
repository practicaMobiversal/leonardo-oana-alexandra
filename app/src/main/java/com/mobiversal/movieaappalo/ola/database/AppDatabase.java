package com.mobiversal.movieaappalo.ola.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mobiversal.movieaappalo.ola.database.dao.MovieDao;
import com.mobiversal.movieaappalo.ola.model.Movie;

@Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();
    private static AppDatabase instance;


    public static AppDatabase getInstance(Context context){
        if(instance == null){
           return Room.databaseBuilder(context, AppDatabase.class,"app_databse")
            .allowMainThreadQueries()
            .build();
        }
        return instance;
    }
}
