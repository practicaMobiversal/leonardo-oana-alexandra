package com.mobiversal.movieaappalo.ola;

//import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.mobiversal.movieaappalo.ola.database.AppDatabase;
import com.mobiversal.movieaappalo.ola.model.Movie;
import com.mobiversal.movieaappalo.ola.network.RequestManager;
import com.mobiversal.movieaappalo.ola.network.response.MoviesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends ParentActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        splashScreenTransition();
        //newActivityOnClick();
        //getSupportFragmentManager().beginTransaction().add(R.id.fragmentLayout, new SavedMoviesFragment(),"fragment");
//        getMoviesFromInternet();
        getMoviesFromDatabase();


    }

    private void splashScreenTransition() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), PrefrencesActivity.class));
                finish();
            }

        }, 5000);
    }

    private void newActivityOnClick(){

        //findViewById(R.id.btnSavedMovies).setOnClickListener(new View.OnClickListener() {
            // @Override
            // public void onClick(View view) {
            //   openSavedMoviesActivity();
            // }
        //});
    }

    private void openSavedMoviesActivity () {

        Intent savedMoviesIntent=new Intent(this, PrefrencesActivity.class);
        startActivity(savedMoviesIntent);


    }

    private void getMoviesFromInternet() {

        Call<MoviesResponse> request = RequestManager.getInstance().getTopRatedMovies();
        request.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                List<Movie> movies = response.body().getResults();
                 for(Movie movie: movies)
                 {
                     Log.d(TAG, movie.getTitle());
                     AppDatabase.getInstance(MainActivity.this)
                             .movieDao()
                             .saveMovie(movie);
                 }


            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.d(TAG, "Get movies failure" + t.getMessage());

            }
        });
    }

    public void getMoviesFromDatabase() {

        List<Movie> movies = AppDatabase.getInstance(this).movieDao().getAllMovies();
        for(Movie movie: movies) {
            Log.d(TAG, movie.getTitle());
        }

   }
}
