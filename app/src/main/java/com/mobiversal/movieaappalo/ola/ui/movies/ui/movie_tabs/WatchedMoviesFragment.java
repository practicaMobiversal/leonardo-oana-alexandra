package com.mobiversal.movieaappalo.ola.ui.movies.ui.movie_tabs;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobiversal.movieaappalo.ola.R;
import com.mobiversal.movieaappalo.ola.database.AppDatabase;
import com.mobiversal.movieaappalo.ola.model.Movie;
import com.mobiversal.movieaappalo.ola.ui.movies.movies_view_holder.MoviesAdapter;
import com.mobiversal.movieaappalo.ola.ui.movies.movies_view_holder.TabMoviesAdapter;
import com.mobiversal.movieaappalo.ola.utils.RemoveItemListener;

import java.util.ArrayList;
import java.util.List;

public class WatchedMoviesFragment extends Fragment {

    private RecyclerView rvWatchedMovies;
    private static final String TAG = FavouriteMoviesFragment.class.getSimpleName();
    List<Movie> movies;
    TabMoviesAdapter savedMoviesAdapter;
    List<Movie> watchedMovies;
    Button remove;



    public WatchedMoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_watched_movies, container, false);

    }

    @Override
    public void onStart() {
        super.onStart();

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.rvWatchedMovies = view.findViewById(R.id.rv_watched_movies);
        this.remove = view.findViewById(R.id.remove_btn);
        watchedMovies = new ArrayList<>();
        setupRecyclerView();
        getWatchedMoviesFromDatabase();
    }

    private void setupRecyclerView() {
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(RecyclerView.VERTICAL);

        rvWatchedMovies.setLayoutManager(llm);
        savedMoviesAdapter = new TabMoviesAdapter(new ArrayList<>(), new RemoveItemListener() {
            @Override
            public void removeItem(Movie item) {

                removeWatched(item);

            }
        });
        rvWatchedMovies.setAdapter(savedMoviesAdapter);

    }


    public void getWatchedMoviesFromDatabase() {

        movies = AppDatabase.getInstance(getContext()).movieDao().getAllMovies();
        for (Movie movie : movies) {
            if (movie.isWatched()) {
                watchedMovies.add(movie);
                savedMoviesAdapter.setMovies(watchedMovies);
                savedMoviesAdapter.notifyDataSetChanged();
                Log.d(TAG, movie.getTitle());
            }
        }
    }


    public void removeWatched(Movie movie) {
        watchedMovies.remove(movie);
        savedMoviesAdapter.setMovies(watchedMovies);
        savedMoviesAdapter.notifyDataSetChanged();
        if (movie.isFavourite()) {
            movie.setWatched(false);
            Log.d(TAG,"Set watched movie on false" + movie.getTitle());

        }
        if (!movie.isFavourite())
        {
            AppDatabase.getInstance(getContext()).movieDao().deleteThisMovie(movie);
            Log.d(TAG,"Movie deleted from watched room" + movie.getTitle());
        }
    }
}
