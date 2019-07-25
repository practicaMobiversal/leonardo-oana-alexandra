package com.mobiversal.movieaappalo.ola.network;

import com.mobiversal.movieaappalo.ola.network.response.ActorsResponse;
import com.mobiversal.movieaappalo.ola.network.response.GenresResponse;
import com.mobiversal.movieaappalo.ola.network.response.MoviesResponse;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.mobiversal.movieaappalo.ola.utils.Constants.API_KEY;
import static com.mobiversal.movieaappalo.ola.utils.Constants.BASE_URL;

public class RequestManager {



    private static RequestManager instance;

    private ApiClient apiClient;

    private RequestManager() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().build();

        Retrofit retrofit = new Retrofit.Builder().client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL).build();

        apiClient = retrofit.create(ApiClient.class);
    }

    public static RequestManager getInstance() {
        if (instance == null) {
            instance = new RequestManager();
        }
        return instance;
    }

    public Call<MoviesResponse> getTopRatedMovies() {
        return apiClient.getTopRatedMovies(API_KEY);
    }

    public Call<ActorsResponse> getPopularPeople() {
        return apiClient.getPopularPeople(API_KEY);
    }

    public  Call<GenresResponse> getMovieListGenres() {return apiClient.getMovieListGenres(API_KEY);}

    public Call<MoviesResponse> getSearchedMovies(String userQuery)  {return apiClient.getSearchedMovies(API_KEY, userQuery );}

    public Call<MoviesResponse> getDiscoveredMovies(String cast, String genre){return apiClient.getDiscoveredMovies(API_KEY,cast, genre);}

    public Call<MoviesResponse> getMovieDetails(int movieId) {return apiClient.getMovieDetails(API_KEY,movieId);}
}
