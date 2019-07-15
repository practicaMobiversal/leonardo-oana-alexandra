package com.mobiversal.movieaappalo.ola;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobiversal.movieaappalo.ola.database.AppDatabase;
import com.mobiversal.movieaappalo.ola.model.Movie;

import java.util.List;

public class SearchMoviesActivity extends ParentActivity {

    private RecyclerView rvSearchMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movies);
        this.rvSearchMovies = findViewById(R.id.rv_search_movies);
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(RecyclerView.VERTICAL);

        rvSearchMovies.setLayoutManager(llm);
        List<Movie> movies = AppDatabase.getInstance(this).movieDao().getAllMovies();

        MoviesAdapter moviesAdapter = new MoviesAdapter(movies);
        rvSearchMovies.setAdapter(moviesAdapter);
    }
}
