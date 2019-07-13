package com.mobiversal.movieaappalo.ola;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.mobiversal.movieaappalo.ola.database.AppDatabase;
import com.mobiversal.movieaappalo.ola.model.Keyword;

public class PreferencesActivity extends ParentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefrences);
        saveOnClick();
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
    private void saveOnClick() {
        findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
                    public void onClick(View view) {
                        saveKeywords(getKeywords());
            }
        });
    }

//actors  methods start
    private void getActorsOnClick() {
        findViewById(R.id.actors).setOnClickListener(new View.OnClickListener() {
            @Override
                    public void onClick(View view) {
                        //......................
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
                //......................
            }
        });
    }
//genres methods end
}
