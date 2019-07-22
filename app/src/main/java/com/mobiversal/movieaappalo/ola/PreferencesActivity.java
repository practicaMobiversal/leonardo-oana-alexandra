package com.mobiversal.movieaappalo.ola;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mobiversal.movieaappalo.ola.database.AppDatabase;
import com.mobiversal.movieaappalo.ola.model.Actor;
import com.mobiversal.movieaappalo.ola.model.Genre;
import com.mobiversal.movieaappalo.ola.model.Keyword;
import com.mobiversal.movieaappalo.ola.ui.movies.SavedMoviesActivity;
import com.mobiversal.movieaappalo.ola.ui.movies.SearchMoviesActivity;
import com.mobiversal.movieaappalo.ola.ui.actors.SelectActorsActivity;
import com.mobiversal.movieaappalo.ola.ui.genres.SelectGenresActivity;

import java.util.List;

public class PreferencesActivity extends ParentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefrences);
        //saveOnClick();
        openSearchMoviesActivityOnClick();
        getActorsOnClick();
        getGenresOnClick();
        placeholder();
        AppDatabase.getInstance(PreferencesActivity.this).actorDao().deleteAll();
        AppDatabase.getInstance(PreferencesActivity.this).genreDao().deleteAll();

    }

    @Override
    protected void onResume() {
        super.onResume();
        showSelectedActors();
        showSelectedGenres();

    }

    public void showSelectedActors() {

        List<Actor> selectedActors = AppDatabase.getInstance(PreferencesActivity.this).actorDao().getAllActors();
        String actorNames = "Actors:";
        TextView actorTv = findViewById(R.id.actors);
            for (Actor actor: selectedActors)
                actorNames += actor.getName()+" "+"," ;
            actorTv.setText(actorNames);

    }

    public void showSelectedGenres() {

        List<Genre> selectedGenres = AppDatabase.getInstance(PreferencesActivity.this).genreDao().getAllGenres();
        String genreNames = "Genres:";
        TextView genreTv = findViewById(R.id.genres);
            for (Genre genre: selectedGenres)
                genreNames += genre.getGenre()+"," ;
            genreTv.setText(genreNames);

    }

    //Kewords methods start
    private String getKeywords() {
        EditText keywords = findViewById(R.id.keywords);
        String textKeywords = keywords.getEditableText().toString();
        return textKeywords;
    }

    private void saveKeywords(String name) {
        Keyword keywordEmpty = new Keyword(name);
        AppDatabase.getInstance(PreferencesActivity.this).keywordDao().saveKeyword(keywordEmpty);
        Log.d(PreferencesActivity.class.getSimpleName(), name);
    }


    //actors  methods start
    private void getActorsOnClick() {
        findViewById(R.id.actors).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PreferencesActivity.this, SelectActorsActivity.class);
                  startActivity(intent);
            }
        });
    }

// .............
// actors methods end

    // genres methods start
    private void getGenresOnClick() {
        findViewById(R.id.genres).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PreferencesActivity.this, SelectGenresActivity.class);
                startActivity(intent);
            }
        });
    }
//genres methods end

    private void openSearchMoviesActivityOnClick() {

        saveKeywords(getKeywords());
        findViewById(R.id.save_pref_btn).setOnClickListener(view -> {
            Intent intent = new Intent(PreferencesActivity.this, SearchMoviesActivity.class);
            startActivity(intent);
        });
    }

    private void placeholder() {

        findViewById(R.id.placeholder).setOnClickListener(view -> {
            Intent intent = new Intent(PreferencesActivity.this, SavedMoviesActivity.class);
            startActivity(intent);
        });
    }
}
