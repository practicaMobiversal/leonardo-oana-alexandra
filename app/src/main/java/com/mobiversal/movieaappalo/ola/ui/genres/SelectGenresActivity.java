package com.mobiversal.movieaappalo.ola.ui.genres;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mobiversal.movieaappalo.ola.R;
import com.mobiversal.movieaappalo.ola.database.AppDatabase;
import com.mobiversal.movieaappalo.ola.model.Genre;
import com.mobiversal.movieaappalo.ola.network.RequestManager;
import com.mobiversal.movieaappalo.ola.network.response.GenresResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectGenresActivity extends AppCompatActivity {

    private RecyclerView rvGenres;
    private static final String TAG = SelectGenresActivity.class.getSimpleName();
    List<Genre> genres;
    GenreAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_genres);
        rvGenres = findViewById(R.id.rv_select_genres);
        setupRecyclerView();
        getGenresFromInternet();
        getIdOnClick();
    }

    private void setupRecyclerView() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rvGenres.setLayoutManager(layoutManager);

        genres = AppDatabase.getInstance(this).genreDao().getAllGenres();

        adapter = new GenreAdapter(new ArrayList<>());
        rvGenres.setAdapter(adapter);
    }

    private void getGenresFromInternet() {

        Call<GenresResponse> request = RequestManager.getInstance().getMovieListGenres();
        request.enqueue(new Callback<GenresResponse>() {
            @Override
            public void onResponse(Call<GenresResponse> call, Response<GenresResponse> response) {

                List<Genre> genres = response.body().getResults();
                if(genres != null){
                    adapter.setGenres(genres);
                    adapter.notifyDataSetChanged();
                }
                for (Genre genre : genres) {
                    Log.d(TAG, genre.getGenre());

                }
                Log.d(TAG, "Get genres success" + response.body().getResults().toString());
            }


            @Override
            public void onFailure(Call<GenresResponse> call, Throwable t) {
                Log.d(TAG, "Get genres failure" + t.getMessage());

            }
        });
    }

    public void getIdOnClick() {
        findViewById(R.id.save_genres_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.getGenresId();
                Log.d("Added genre ids","Added list");
            }
        } );
    }

}
