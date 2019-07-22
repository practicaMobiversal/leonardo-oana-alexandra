package com.mobiversal.movieaappalo.ola.ui.movies.movie_drawer;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobiversal.movieaappalo.ola.R;
import com.mobiversal.movieaappalo.ola.model.Movie;
import com.mobiversal.movieaappalo.ola.network.RequestManager;
import com.mobiversal.movieaappalo.ola.network.response.MoviesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchOtherMoviesFragment extends Fragment {


    public SearchOtherMoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_other_movies, container, false);
    }



//    public void getMoviesOnSearch() {
//        Call<MoviesResponse> request = RequestManager.getInstance().getTopRatedMovies();
//        request.enqueue(new Callback<MoviesResponse>() {
//            @Override
//            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
//
//                List<Movie> movies = response.body().getResults();
//                if(movies != null){
//                    adapter.setActors(actors);
//                    adapter.notifyDataSetChanged();
//                }
//                for (Actor actor : actors) {
//                    Log.d(TAG, actor.getName());
//
//                }
//                Log.d(TAG, "Get actors success" + response.body().getResults().toString());
//            }
//
//
//            @Override
//            public void onFailure(Call<ActorsResponse> call, Throwable t) {
//                Log.d(TAG, "Get genres failure" + t.getMessage());
//
//            }
//        });
//
//    }

}
