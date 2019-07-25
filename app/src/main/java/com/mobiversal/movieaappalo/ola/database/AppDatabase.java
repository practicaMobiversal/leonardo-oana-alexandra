package com.mobiversal.movieaappalo.ola.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mobiversal.movieaappalo.ola.database.dao.ActorDao;
import com.mobiversal.movieaappalo.ola.database.dao.GenreDao;
import com.mobiversal.movieaappalo.ola.database.dao.KeywordDao;
import com.mobiversal.movieaappalo.ola.database.dao.MovieDao;
import com.mobiversal.movieaappalo.ola.model.Actor;
import com.mobiversal.movieaappalo.ola.model.Genre;
import com.mobiversal.movieaappalo.ola.model.Keyword;
import com.mobiversal.movieaappalo.ola.model.Movie;

@Database(entities = {Movie.class, Keyword.class, Actor.class, Genre.class}, version = 4, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();
    public abstract KeywordDao keywordDao();
    public abstract ActorDao actorDao();
    public abstract GenreDao genreDao();
    private static AppDatabase instance;


    public static AppDatabase getInstance(Context context){
        if(instance == null){
           return Room.databaseBuilder(context, AppDatabase.class,"app_database")
            .allowMainThreadQueries().fallbackToDestructiveMigration()
            .build();
        }
        return instance;
    }
}
