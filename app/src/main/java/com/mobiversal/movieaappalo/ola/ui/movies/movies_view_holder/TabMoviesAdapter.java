package com.mobiversal.movieaappalo.ola.ui.movies.movies_view_holder;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.mobiversal.movieaappalo.ola.R;
import com.mobiversal.movieaappalo.ola.database.AppDatabase;
import com.mobiversal.movieaappalo.ola.model.Movie;
import com.mobiversal.movieaappalo.ola.ui.movies.ui.movie_tabs.FavouriteMoviesFragment;
import com.mobiversal.movieaappalo.ola.ui.movies.ui.movie_tabs.WatchedMoviesFragment;
import com.mobiversal.movieaappalo.ola.utils.ImageLoader;
import com.mobiversal.movieaappalo.ola.utils.RemoveItemListener;

import java.util.ArrayList;
import java.util.List;

import static com.mobiversal.movieaappalo.ola.utils.Constants.BASE_IMAGE_URL;
import static com.mobiversal.movieaappalo.ola.utils.Constants.IMAGE_SIZE;

public class TabMoviesAdapter extends RecyclerView.Adapter<TabMoviesAdapter.SavedMovieItemViewHolder> {


    private List<Movie> movies;

    private Context context;

    private RemoveItemListener removeItemListener;


    public TabMoviesAdapter(List<Movie> movies, RemoveItemListener removeItemListener) {

        this.movies = movies;
        this.removeItemListener = removeItemListener;

    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public SavedMovieItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.view_saved_movie_item, parent, false);
        return new SavedMovieItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SavedMovieItemViewHolder holder, int position) {
        Movie movie = this.movies.get(position);
        context = holder.itemView.getContext();
        holder.onBind(movie);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class SavedMovieItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivMovie;
        private TextView tvTitle;
        Button remove;
        List<Movie> dbMovies;

        public SavedMovieItemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.ivMovie = itemView.findViewById(R.id.iv_savedMovie);
            this.tvTitle = itemView.findViewById(R.id.tv_savedMv_title);
            this.remove = itemView.findViewById(R.id.remove_btn);
            dbMovies = new ArrayList<>();
        }


        public void onBind(Movie movie) {
            ImageLoader.loadImageUrl(ivMovie, BASE_IMAGE_URL + IMAGE_SIZE + movie.getImageUrl(), ivMovie.getContext());
            this.tvTitle.setText(movie.getTitle());
            removeMovieOnClick(movie);


        }

        public void removeMovieOnClick(Movie movie) {
            remove.setOnClickListener(view -> {

                removeItemListener.removeItem(movie);

            });
        }
    }

}
