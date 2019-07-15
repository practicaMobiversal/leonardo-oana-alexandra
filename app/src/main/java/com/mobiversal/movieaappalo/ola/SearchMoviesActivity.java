package com.mobiversal.movieaappalo.ola;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

public class SearchMoviesActivity extends ParentActivity {

    private RecyclerView rvSearchMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movies);
        this.rvSearchMovies = findViewById(R.id.rv_search_movies);
    }
}
