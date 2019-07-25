package com.mobiversal.movieaappalo.ola.ui.movies.ui.movie_tabs;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobiversal.movieaappalo.ola.R;
import com.mobiversal.movieaappalo.ola.database.AppDatabase;
import com.mobiversal.movieaappalo.ola.model.Movie;
import com.mobiversal.movieaappalo.ola.network.RequestManager;
import com.mobiversal.movieaappalo.ola.network.response.MoviesResponse;
import com.mobiversal.movieaappalo.ola.ui.movies.movies_view_holder.MoviesAdapter;
import com.mobiversal.movieaappalo.ola.ui.movies.movies_view_holder.TabMoviesAdapter;
import com.mobiversal.movieaappalo.ola.utils.RemoveItemListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FavouriteMoviesFragment extends Fragment {

    private RecyclerView rvFavouriteMovies;
    private static final String TAG = FavouriteMoviesFragment.class.getSimpleName();
    List<Movie> movies;
    TabMoviesAdapter savedMoviesAdapter;
    List<Movie> favMovies;
    Button remove;



    public FavouriteMoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourite_movies, container, false);

    }

    @Override
    public void onStart() {
        super.onStart();

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.rvFavouriteMovies = view.findViewById(R.id.rv_favourite_movies);
        favMovies = new ArrayList<>();
        setupRecyclerView();
        getFavouriteMoviesFromDatabase();
    }

    private void setupRecyclerView() {
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(RecyclerView.VERTICAL);

        rvFavouriteMovies.setLayoutManager(llm);
        savedMoviesAdapter = new TabMoviesAdapter(new ArrayList<>(), true, new RemoveItemListener() {
            @Override
            public void removeItem(Movie item) {
                removeFavourite(item);
            }
        });
        rvFavouriteMovies.setAdapter(savedMoviesAdapter);

    }


    public void getFavouriteMoviesFromDatabase() {


        movies = AppDatabase.getInstance(getContext()).movieDao().getAllMovies();
        for (Movie movie : movies) {
            if (movie.isFavourite()) {
                favMovies.add(movie);
                savedMoviesAdapter.setMovies(favMovies);
                savedMoviesAdapter.notifyDataSetChanged();
                Log.d(TAG, movie.getTitle());
            }
        }
    }

    public void removeFavourite(Movie movie) {
        favMovies.remove(movie);
        savedMoviesAdapter.setMovies(favMovies);
        savedMoviesAdapter.notifyDataSetChanged();
    }


}
