package com.mobiversal.movieaappalo.ola.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mobiversal.movieaappalo.ola.model.Genre;

import java.util.List;

@Dao
public interface GenreDao {

    @Query("SELECT * FROM genre")
    public List<Genre> getAllGenres();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void saveGenre(Genre genre);

    @Query("DELETE  FROM genre" )
    public void deleteAll();
}
