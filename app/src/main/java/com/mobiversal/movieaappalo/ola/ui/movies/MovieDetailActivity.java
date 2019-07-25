package com.mobiversal.movieaappalo.ola.ui.movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobiversal.movieaappalo.ola.R;
import com.mobiversal.movieaappalo.ola.model.Movie;
import com.mobiversal.movieaappalo.ola.network.RequestManager;
import com.mobiversal.movieaappalo.ola.network.response.MoviesResponse;
import com.mobiversal.movieaappalo.ola.utils.ImageLoader;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mobiversal.movieaappalo.ola.utils.Constants.BASE_IMAGE_URL;
import static com.mobiversal.movieaappalo.ola.utils.Constants.IMAGE_SIZE;

public class MovieDetailActivity extends AppCompatActivity {

    Toolbar tbMovieName;
    ImageView ivPoster;
    Button btnFav;
    Button btnWhatched;
    TextView tvOverview;
    TextView tvYear;
    TextView tvGenres;
    int movieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        tbMovieName = findViewById(R.id.tb_detail);
        ivPoster = findViewById(R.id.iv_poster);
        btnFav = findViewById(R.id.btn_fav_detail);
        btnWhatched = findViewById(R.id.btn_wat_detail);
        tvOverview = findViewById(R.id.overview);
        tvYear = findViewById(R.id.year);
        tvGenres = findViewById(R.id.genre_list);
        movieId = getIntent().getIntExtra("MOVIE_ID",0);
    }

//    public void getMovieDetails(){
//        Call<MoviesResponse> request =  RequestManager.getInstance().getMovieDetails(movieId);
//        request.enqueue(new Callback<MoviesResponse>() {
//            @Override
//            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
//
//                    ivPoster = ImageLoader.loadImageUrl(ivPoster, BASE_IMAGE_URL + IMAGE_SIZE + movie.getImageUrl(), ivPoster.getContext());
//                }
//
//                    Log.d("Details", movie.getTitle());
//
//                }
//                Log.d("Details", "Get discovered movies success" + response.body().getResults().toString());
//            }
//
//
//            @Override
//            public void onFailure(Call<MoviesResponse> call, Throwable t) {
//                Log.d("Details", "Get discovered movies failure" + t.getMessage());
//
//            }
//        });
//    }


}

