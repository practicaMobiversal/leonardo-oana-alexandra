package com.mobiversal.movieaappalo.ola.ui.movies.movies_view_holder;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobiversal.movieaappalo.ola.R;
import com.mobiversal.movieaappalo.ola.database.AppDatabase;
import com.mobiversal.movieaappalo.ola.model.Movie;
import com.mobiversal.movieaappalo.ola.ui.movies.MovieDetailActivity;
import com.mobiversal.movieaappalo.ola.utils.ImageLoader;
import com.mobiversal.movieaappalo.ola.utils.ItemClickListener;

import java.util.ArrayList;
import java.util.List;

import static com.mobiversal.movieaappalo.ola.utils.Constants.BASE_IMAGE_URL;
import static com.mobiversal.movieaappalo.ola.utils.Constants.IMAGE_SIZE;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieItemViewHolder> {


    private List<Movie> movies;

    private ItemClickListener<Movie> itemClickListener;

    private Context context;


    public MoviesAdapter(List<Movie> movies, ItemClickListener<Movie> itemClickListener) {
        this.movies = movies;
        this.itemClickListener = itemClickListener;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.view_movie_item, parent, false);
        return new MovieItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieItemViewHolder holder, int position) {
        Movie movie = this.movies.get(position);
        context = holder.itemView.getContext();
        holder.onBind(movie);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MovieItemViewHolder extends RecyclerView.ViewHolder  {

        private ImageView ivMovie;
        private TextView tvTitle;
        Button favourite;
        Button watched;
        Button remove;
        List<Movie> dbMovies;


        public MovieItemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.ivMovie = itemView.findViewById(R.id.iv_movie);
            this.tvTitle = itemView.findViewById(R.id.tv_title);
            this.favourite = itemView.findViewById(R.id.favourites);
            this.watched = itemView.findViewById(R.id.watched);
            this.remove = itemView.findViewById(R.id.remove_btn);
            dbMovies = new ArrayList<>();
            //itemView.setOnClickListener(this);
        }


        public void onBind(Movie movie) {
            ImageLoader.loadImageUrl(ivMovie, BASE_IMAGE_URL + IMAGE_SIZE + movie.getImageUrl(), ivMovie.getContext());
            this.tvTitle.setText(movie.getTitle());
            setFavouriteOnClick(movie);
            setWatchedOnClick(movie);
            itemListener(movie);


        }

        public void setFavouriteOnClick(Movie movie) {
            favourite.setOnClickListener(view -> {
                dbMovies = AppDatabase.getInstance(context).movieDao().getAllMovies();

                if (!dbMovies.contains(movie)){
                    movie.setFavourite(true);
                    AppDatabase.getInstance(context).movieDao().saveMovie(movie);}
                else
                    movie.setFavourite(true);
                Log.d("Room Added to watched", movie.getTitle());

            });

        }

        public void setWatchedOnClick(Movie movie) {
            watched.setOnClickListener(view -> {
                dbMovies = AppDatabase.getInstance(context).movieDao().getAllMovies();
                if (!dbMovies.contains(movie)) {
                    movie.setWatched(true);
                    AppDatabase.getInstance(context).movieDao().saveMovie(movie);}
                else
                    movie.setWatched(true);
                    Log.d("Room Added to watched", movie.getTitle());

            });

        }

        public void itemListener(Movie movie){

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onItemClick(movie);
                }
            });
        }


    }

}
