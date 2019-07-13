package com.mobiversal.movieaappalo.ola.database.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mobiversal.movieaappalo.ola.model.Keyword;

import java.util.List;

@Dao
public interface KeywordDao {

    @Query("SELECT * FROM keyword")
    public List<Keyword> getAllKeywords();

    @Insert
    public void saveKeyword(Keyword keyword);
}

