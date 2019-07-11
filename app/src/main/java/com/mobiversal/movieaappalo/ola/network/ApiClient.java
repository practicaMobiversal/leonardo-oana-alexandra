package com.mobiversal.movieaappalo.ola.network;

import com.mobiversal.movieaappalo.ola.network.response.MoviesResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.Call;

public interface ApiClient {

    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);
}
