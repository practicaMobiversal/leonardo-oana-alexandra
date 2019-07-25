package com.mobiversal.movieaappalo.ola;

//import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.mobiversal.movieaappalo.ola.database.AppDatabase;
import com.mobiversal.movieaappalo.ola.model.Actor;
import com.mobiversal.movieaappalo.ola.model.Genre;
import com.mobiversal.movieaappalo.ola.model.Movie;
import com.mobiversal.movieaappalo.ola.network.RequestManager;
import com.mobiversal.movieaappalo.ola.network.response.ActorsResponse;
import com.mobiversal.movieaappalo.ola.network.response.MoviesResponse;
import com.mobiversal.movieaappalo.ola.ui.movies.SearchMoviesActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends ParentActivity {


    List<Actor> actors;
    List<Genre> genres;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        splashScreenTransition();
        actors = AppDatabase.getInstance(this).actorDao().getAllActors();
        genres = AppDatabase.getInstance(this).genreDao().getAllGenres();



    }

    private void splashScreenTransition() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(actors!= null && genres !=null)
                {
                    startActivity(new Intent(getApplicationContext(), SearchMoviesActivity.class));
                    finish();
                }
                else {
                    startActivity(new Intent(getApplicationContext(), PreferencesActivity.class));
                    finish();
                }
            }

        }, 5000);
    }





}
