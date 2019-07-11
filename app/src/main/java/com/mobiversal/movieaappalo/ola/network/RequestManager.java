package com.mobiversal.movieaappalo.ola.network;

import com.mobiversal.movieaappalo.ola.network.response.MoviesResponse;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestManager {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String API_KEY = "736d0ff281700746aa0bf9027c2f7024";

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
        if(instance==null) {
            instance = new RequestManager();
        }
        return instance;
    }

    public Call<MoviesResponse> getTopRatedMovies() {
        return apiClient.getTopRatedMovies(API_KEY);
    }
}
