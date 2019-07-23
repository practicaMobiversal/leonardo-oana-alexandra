package com.mobiversal.movieaappalo.ola.ui.movies.movies_view_holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobiversal.movieaappalo.ola.R;
import com.mobiversal.movieaappalo.ola.model.Movie;
import com.mobiversal.movieaappalo.ola.utils.ImageLoader;

import static com.mobiversal.movieaappalo.ola.utils.Constants.BASE_IMAGE_URL;
import static com.mobiversal.movieaappalo.ola.utils.Constants.IMAGE_SIZE;

public class MovieItemViewHolder extends RecyclerView.ViewHolder {

    private ImageView ivMovie;
    private TextView tvTitle;

    public MovieItemViewHolder(@NonNull View itemView) {
        super(itemView);
        this.ivMovie = itemView.findViewById(R.id.iv_movie);
        this.tvTitle = itemView.findViewById(R.id.tv_title);
    }

    public  void onBind(Movie movie) {
        ImageLoader.loadImageUrl(ivMovie, BASE_IMAGE_URL + IMAGE_SIZE + movie.getImageUrl(), ivMovie.getContext());
        this.tvTitle.setText(movie.getTitle());

    }
}
