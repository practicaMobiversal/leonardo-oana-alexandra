package com.mobiversal.movieaappalo.ola.ui.movies;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.mobiversal.movieaappalo.ola.R;
import com.mobiversal.movieaappalo.ola.ui.movies.ui.movie_tabs.SavedMoviesAdapter;

public class SavedMoviesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_movies);
        SavedMoviesAdapter savedMoviesAdapter = new SavedMoviesAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(savedMoviesAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

    }
}