package com.mobiversal.movieaappalo.ola.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="keyword")
public class Keyword {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ColumnInfo(name="word")
    private String word;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Keyword(String word) {
        this.word = word;
    }
}
