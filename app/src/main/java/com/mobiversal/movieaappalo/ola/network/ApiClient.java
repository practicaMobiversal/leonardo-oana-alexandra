package com.mobiversal.movieaappalo.ola.network;

import com.mobiversal.movieaappalo.ola.network.response.ActorsResponse;
import com.mobiversal.movieaappalo.ola.network.response.GenresResponse;
import com.mobiversal.movieaappalo.ola.network.response.MoviesResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.Call;

public interface ApiClient {

    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("person/popular")
    Call<ActorsResponse> getPopularPeople(@Query("api_key") String apiKey);

    @GET("genre/movie/list")
    Call<GenresResponse> getMovieListGenres(@Query("api_key") String apiKey);

    @GET("search/movie")
    Call<MoviesResponse> getSearchedMovies(@Query("api_key") String apiKey, @Query("query") String query);

    @GET("discover/movie")
    Call<MoviesResponse> getDiscoveredMovies(@Query("api_key") String apiKey, @Query("with_cast") String cast, @Query("with_genres") String genre);

    @GET("movie/{movie_id}&append_to_response=videos")
    Call<MoviesResponse> getMovieDetails(@Query("api_key") String apiKey, @Query("movie_id") int movieId);
}
