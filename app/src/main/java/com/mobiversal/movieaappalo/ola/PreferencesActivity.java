package com.mobiversal.movieaappalo.ola;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.mobiversal.movieaappalo.ola.database.AppDatabase;
import com.mobiversal.movieaappalo.ola.model.Keyword;
import com.mobiversal.movieaappalo.ola.ui.movies.SavedMoviesActivity;
import com.mobiversal.movieaappalo.ola.ui.movies.SearchMoviesActivity;
import com.mobiversal.movieaappalo.ola.ui.actors.SelectActorsActivity;
import com.mobiversal.movieaappalo.ola.ui.genres.SelectGenresActivity;

public class PreferencesActivity extends ParentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefrences);
        //saveOnClick();
        openDrawerActivityOnClick();
        getActorsOnClick();
        getGenresOnClick();
        placeholder();
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

//Keywords methods end

    //save button method
//    private void saveOnClick() {
//        findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                saveKeywords(getKeywords());
//
//                //TODO: Move this outside of save function
//
//                Intent intent = new Intent(PreferencesActivity.this, SearchMoviesActivity.class);
//                startActivity(intent);
//            }
//        });
//    }

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

    private void openDrawerActivityOnClick() {

        findViewById(R.id.save_pref_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PreferencesActivity.this, SearchMoviesActivity.class);
                startActivity(intent);
            }
        });
    }

    private void placeholder() {

        findViewById(R.id.placeholder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PreferencesActivity.this, SavedMoviesActivity.class);
                startActivity(intent);
            }
        });
    }
}
