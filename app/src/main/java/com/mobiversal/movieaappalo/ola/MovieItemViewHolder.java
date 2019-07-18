package com.mobiversal.movieaappalo.ola;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobiversal.movieaappalo.ola.model.Movie;

public class MovieItemViewHolder extends RecyclerView.ViewHolder {

    private TextView tvId;
    private TextView tvTitle;

    public MovieItemViewHolder(@NonNull View itemView) {
        super(itemView);
        this.tvId = itemView.findViewById(R.id.iv_movie);
        this.tvTitle = itemView.findViewById(R.id.tv_title);
    }

    public  void onBind(Movie movie) {
        this.tvId.setText(String.format("#%d",movie.getId()));
        this.tvTitle.setText(movie.getTitle());

    }
}
