package com.mobiversal.movieaappalo.ola.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.mobiversal.movieaappalo.ola.model.Movie;

import java.util.List;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM movie")
    public List<Movie> getAllMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long saveMovie(Movie movie);

    @Query("DELETE  FROM movie" )
    public void deleteAll();

    @Delete
    public void deleteThisMovie(Movie movie);

    @Update
    public void updateMovies(Movie movie);
}
