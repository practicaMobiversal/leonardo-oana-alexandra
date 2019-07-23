package com.mobiversal.movieaappalo.ola.ui.movies;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;
import com.mobiversal.movieaappalo.ola.R;
import com.mobiversal.movieaappalo.ola.ui.movies.movie_drawer.MovieListFragment;
import com.mobiversal.movieaappalo.ola.ui.movies.movie_drawer.SavedMoviesFragment;

public class SearchMoviesActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movies);
        showFirstFragment();
        initToolbar();
        initDrawer();
    }

    private void initToolbar() {
        drawerLayout = findViewById(R.id.dl_drawer);
        toolbar = findViewById(R.id.tb_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setNavigationOnClickListener(view -> drawerLayout.openDrawer(GravityCompat.START));
    }

    private void initDrawer() {
        navigationView = findViewById(R.id.nv_navigation);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            handleNavigation(menuItem.getItemId());
            closeDrawer();
            return true;

        });

    }


    private void closeDrawer() {
        drawerLayout.closeDrawers();
    }

    private void showFirstFragment() {
        switchFragment(new MovieListFragment());
    }

    private void showSecondFragment() {
        switchFragment(new SavedMoviesFragment());
        toolbar.setTitle("Saved Movies");
    }

    private void switchFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .replace(R.id.fl_container, fragment)
                .commit();
    }


    private void handleNavigation(int id) {

        switch (id) {
            case R.id.first:
                showFirstFragment();
                toolbar.setTitle("Search Movies");
                break;
            case R.id.second:
                showSecondFragment();
                break;
        }
    }
}