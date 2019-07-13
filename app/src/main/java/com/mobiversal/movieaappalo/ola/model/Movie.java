package com.mobiversal.movieaappalo.ola.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie")
public class Movie {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;

    @ColumnInfo(name = "title")
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title=title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
