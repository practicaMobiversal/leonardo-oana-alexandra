package com.mobiversal.movieaappalo.ola.ui.movies.movies_view_holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobiversal.movieaappalo.ola.R;
import com.mobiversal.movieaappalo.ola.model.Movie;

public class MovieItemViewHolder extends RecyclerView.ViewHolder {

    private ImageView ivMovie;
    private TextView tvTitle;

    public MovieItemViewHolder(@NonNull View itemView) {
        super(itemView);
        this.ivMovie = itemView.findViewById(R.id.iv_movie);
        this.tvTitle = itemView.findViewById(R.id.tv_title);
    }

    public  void onBind(Movie movie) {
        //this.ivMovie.setText(String.format("#%d",movie.getId()));
        this.tvTitle.setText(movie.getTitle());

    }
}
