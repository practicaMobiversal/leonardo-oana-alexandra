package com.mobiversal.movieaappalo.ola.ui.movies.movie_drawer;


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
import android.widget.EditText;

import com.mobiversal.movieaappalo.ola.R;
import com.mobiversal.movieaappalo.ola.model.Movie;
import com.mobiversal.movieaappalo.ola.network.RequestManager;
import com.mobiversal.movieaappalo.ola.network.response.MoviesResponse;
import com.mobiversal.movieaappalo.ola.ui.movies.movies_view_holder.MoviesAdapter;
import com.mobiversal.movieaappalo.ola.ui.movies.movies_view_holder.MoviesLoadThread;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchOtherMoviesFragment extends Fragment {

    private static final String TAG = SearchOtherMoviesFragment.class.getSimpleName();
    private RecyclerView rvSearchMovies;
    List<Movie> movies = new ArrayList<>();
    MoviesAdapter moviesAdapter;
    SearchView svMovies;


    public SearchOtherMoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.rvSearchMovies = view.findViewById(R.id.rv_search_other_movies);
        this.svMovies= view.findViewById(R.id.sv_movies);
        setupRecyclerView();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_other_movies, container, false);
    }


    private void setupRecyclerView() {
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(RecyclerView.VERTICAL);

        rvSearchMovies.setLayoutManager(llm);
        moviesAdapter  = new MoviesAdapter(movies);
        rvSearchMovies.setAdapter(moviesAdapter);

    }

    @Override
    public void onStart() {
        super.onStart();
        queryTextListener();
    }

        public String getUserQuery() {

        String textQuery="";
        if (svMovies.getQuery() != null)
            textQuery=svMovies.getQuery().toString();
        return textQuery;


    }


    public void queryTextListener() {
        svMovies.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getMoviesOnSearch();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
// do something when text changes
                return false;
            }
        });

    }



    public void getMoviesOnSearch() {

//        svMovies.findViewById(R.id.sv_movies);
//        String textQuery = svMovies.getQuery().toString();

            Call<MoviesResponse> request = RequestManager.getInstance().getSearchedMovies(getUserQuery());
            if (getUserQuery().length()<1)
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



}
