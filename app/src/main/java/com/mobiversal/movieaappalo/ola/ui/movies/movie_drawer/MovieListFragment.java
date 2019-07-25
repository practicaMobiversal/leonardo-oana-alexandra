package com.mobiversal.movieaappalo.ola.ui.movies.movie_drawer;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mobiversal.movieaappalo.ola.PreferencesActivity;
import com.mobiversal.movieaappalo.ola.R;
import com.mobiversal.movieaappalo.ola.database.AppDatabase;
import com.mobiversal.movieaappalo.ola.model.Actor;
import com.mobiversal.movieaappalo.ola.model.Genre;
import com.mobiversal.movieaappalo.ola.model.Movie;
import com.mobiversal.movieaappalo.ola.network.RequestManager;
import com.mobiversal.movieaappalo.ola.network.response.MoviesResponse;
import com.mobiversal.movieaappalo.ola.ui.movies.MovieDetailActivity;
import com.mobiversal.movieaappalo.ola.ui.movies.movies_view_holder.MoviesAdapter;
import com.mobiversal.movieaappalo.ola.ui.movies.movies_view_holder.MoviesLoadThread;
import com.mobiversal.movieaappalo.ola.utils.ItemClickListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieListFragment extends Fragment {

    private RecyclerView rvSearchMovies;
    private static final String TAG = SavedMoviesFragment.class.getSimpleName();
    SearchView svMovies;
    List<Movie> movies;
    MoviesAdapter moviesAdapter;
    List<Actor> chosenActors;
    List<Genre> chosenGenres;
    Button filter;



    public MovieListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false);

    }

    @Override
    public void onStart() {
        super.onStart();
        queryTextListener();
        getPreferencesOnClick();
        AppDatabase.getInstance(getContext()).movieDao().deleteAll();

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.rvSearchMovies = view.findViewById(R.id.rv_search_movies);
        this.svMovies = view.findViewById(R.id.sv_movies);
        this.filter = view.findViewById(R.id.filter_btn);

        movies = new ArrayList<>();
        chosenActors = new ArrayList<>();
        chosenGenres = new ArrayList<>();
        setupRecyclerView();
        getMoviesOnDiscover();
    }

    public void getDetailActivity(Movie movie){
        Intent intent = new Intent(getContext(), MovieDetailActivity.class);
        intent.putExtra("MOVIE_ID", movie.getId());
        startActivity(intent);
    }

    private void setupRecyclerView() {
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(RecyclerView.VERTICAL);

        rvSearchMovies.setLayoutManager(llm);


        moviesAdapter = new MoviesAdapter(movies, new ItemClickListener<Movie>() {
            @Override
            public void onItemClick(Movie item) {
            getDetailActivity(item);
            }
        });
        rvSearchMovies.setAdapter(moviesAdapter);

    }

    public void queryTextListener() {
        svMovies.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText!=null)
                {getMoviesOnSearch();}
                else
                {getMoviesOnDiscover();}

                return true;
            }
        });

    }


    public String getUserQuery() {

        String textQuery = "";
        if (svMovies.getQuery() != null)
            textQuery = svMovies.getQuery().toString();
        return textQuery;


    }

    public String getCast() {

        String cast="";
        chosenActors = AppDatabase.getInstance(getContext()).actorDao().getAllActors();
        for (int i=0;i<chosenActors.size();i++) {

            cast+=String.valueOf(chosenActors.get(i).getId())+"|";
        }
        return cast;
    }

    public String getChosenGenres(){
        String genre="";
        chosenGenres = AppDatabase.getInstance(getContext()).genreDao().getAllGenres();
        for (int i=0;i<chosenGenres.size();i++){

            genre+=String.valueOf(chosenGenres.get(i).getId())+"|";
        }

        return genre;
    }

    public void getMoviesOnDiscover() {


        Call<MoviesResponse> request = RequestManager.getInstance().getDiscoveredMovies(getCast(),getChosenGenres());
        request.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                List<Movie> movies = response.body().getResults();
                if (movies != null) {
                    moviesAdapter.setMovies(movies);
                    moviesAdapter.notifyDataSetChanged();
                }
                for (Movie movie : movies) {
                    Log.d(TAG, movie.getTitle());

                }
                Log.d(TAG, "Get discovered movies success" + response.body().getResults().toString());
            }


            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.d(TAG, "Get discovered movies failure" + t.getMessage());

            }
        });
    }




    public void getMoviesOnSearch() {


        Call<MoviesResponse> request = RequestManager.getInstance().getSearchedMovies(getUserQuery());
        if (getUserQuery().length() < 1)
            return;
        request.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                List<Movie> movies = response.body().getResults();
                if (movies != null) {
                    moviesAdapter.setMovies(movies);
                    moviesAdapter.notifyDataSetChanged();
                }
                for (Movie movie : movies) {
                    Log.d(TAG, movie.getTitle());

                }
                Log.d(TAG, "Get searched movies success" + response.body().getResults().toString());
            }


            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.d(TAG, "Get searched movies failure" + t.getMessage());

            }
        });
    }

    public void getPreferencesOnClick(){
        filter.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), PreferencesActivity.class);
            startActivity(intent);
        });
    }






}

