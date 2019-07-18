package com.mobiversal.movieaappalo.ola.ui.genres;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobiversal.movieaappalo.ola.R;
import com.mobiversal.movieaappalo.ola.model.Genre;

import java.util.List;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreViewHolder> {

    List<Genre> genres;

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public GenreAdapter(List<Genre> genres) {
        this.genres = genres;
    }


    @NonNull
    @Override
    public GenreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.view_genre_item, parent, false);
        return new GenreViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull GenreViewHolder holder, int position) {
        holder.onBind(genres.get(position));
        getItemCount();

    }

    @Override
    public int getItemCount() {
        return genres.size();
    }

    class GenreViewHolder extends RecyclerView.ViewHolder {

        TextView genre;
        CheckBox genreCheckBox;


        public GenreViewHolder(@NonNull View itemView) {
            super(itemView);
            genre = itemView.findViewById(R.id.tv_genre);
            genreCheckBox = itemView.findViewById(R.id.cb_genre);

        }


        public void onBind(Genre genreItem) {
            genre.setText(genreItem.getGenre());
            setCheckboxOnThick();


        }

        private void setCheckboxOnThick () {

            genreCheckBox.setChecked(true);
            genreCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                // update your model (or other business logic) based on isChecked
            });
        }
    }
}
