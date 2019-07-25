package com.mobiversal.movieaappalo.ola.ui.actors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mobiversal.movieaappalo.ola.R;
import com.mobiversal.movieaappalo.ola.database.AppDatabase;
import com.mobiversal.movieaappalo.ola.model.Actor;
import com.mobiversal.movieaappalo.ola.network.RequestManager;
import com.mobiversal.movieaappalo.ola.network.response.ActorsResponse;
import com.mobiversal.movieaappalo.ola.ui.genres.SelectGenresActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectActorsActivity extends AppCompatActivity {



    private RecyclerView rvActors;
    private static final String TAG = SelectActorsActivity.class.getSimpleName();
    List<Actor> actors;
    ActorsAdapter  adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_actors);
        rvActors = findViewById(R.id.rv_select_actors);
        setupRecyclerView();
        getActorsFromInternet();
        getActorsOnClick();

    }

    private void setupRecyclerView() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rvActors.setLayoutManager(layoutManager);


        adapter = new ActorsAdapter(new ArrayList<>());
        rvActors.setAdapter(adapter);
    }



    private void getActorsFromInternet() {

        Call<ActorsResponse> request = RequestManager.getInstance().getPopularPeople();
        request.enqueue(new Callback<ActorsResponse>() {
            @Override
            public void onResponse(Call<ActorsResponse> call, Response<ActorsResponse> response) {

                List<Actor> actors = response.body().getResults();
                if(actors != null){
                    adapter.setActors(actors);
                    adapter.notifyDataSetChanged();
                }
                for (Actor actor : actors) {
                    Log.d(TAG, actor.getName());

                }
                Log.d(TAG, "Get actors success" + response.body().getResults().toString());
            }


            @Override
            public void onFailure(Call<ActorsResponse> call, Throwable t) {
                Log.d(TAG, "Get genres failure" + t.getMessage());

            }
        });
    }
    public void getActorsOnClick() {
        findViewById(R.id.save_actors_btn).setOnClickListener(view -> {

            //AppDatabase.getInstance(SelectActorsActivity.this).actorDao().deleteAll();
            for (Actor actor: adapter.getSelectedActors() ) {
                AppDatabase.getInstance(SelectActorsActivity.this).actorDao().saveActor(actor);
                Log.d(TAG, actor.getName());
            }
            onBackPressed();
        });


    }


}
