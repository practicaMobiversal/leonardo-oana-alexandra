package com.mobiversal.movieaappalo.ola.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mobiversal.movieaappalo.ola.model.Movie;

import java.util.List;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM movie")
    public List<Movie> getAllMovies();

    @Insert
    public long saveMovie(Movie movie);
}
