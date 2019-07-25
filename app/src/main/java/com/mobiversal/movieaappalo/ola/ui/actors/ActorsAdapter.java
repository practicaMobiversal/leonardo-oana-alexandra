package com.mobiversal.movieaappalo.ola.ui.actors;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobiversal.movieaappalo.ola.R;
import com.mobiversal.movieaappalo.ola.model.Actor;
import com.mobiversal.movieaappalo.ola.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import static com.mobiversal.movieaappalo.ola.utils.Constants.BASE_IMAGE_URL;
import static com.mobiversal.movieaappalo.ola.utils.Constants.IMAGE_SIZE;

public class ActorsAdapter extends RecyclerView.Adapter<ActorsAdapter.ActorViewHolder> {

    public List<Actor> actors;
    public List<Actor> selectedActors;

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public List<Actor> getSelectedActors() {
        return selectedActors;
    }


    public ActorsAdapter(List<Actor> actors) {

        this.actors = actors;
        selectedActors = new ArrayList<>();

    }


    @NonNull
    @Override
    public ActorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.view_actor_item, parent, false);
        return new ActorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActorViewHolder holder, int position) {
        holder.onBind(actors.get(position));
        getItemCount();


    }

    @Override
    public int getItemCount() {
        return actors.size();
    }

    class ActorViewHolder extends RecyclerView.ViewHolder {


        ImageView actorImage;
        TextView actorName;
        CheckBox actorCheckBox;

        public ActorViewHolder(@NonNull View itemView) {
            super(itemView);

            actorImage = itemView.findViewById(R.id.iv_actor);
            actorName = itemView.findViewById(R.id.tv_name_actor);
            actorCheckBox = itemView.findViewById(R.id.cb_actor);
        }

        public void onBind(Actor actor) {
            ImageLoader.loadImageUrl(actorImage, BASE_IMAGE_URL + IMAGE_SIZE + actor.getImageUrl(), actorImage.getContext());
            actorName.setText(actor.getName());
            actorCheckBox.setOnCheckedChangeListener(null);
            setCheckboxOnThick(actor);
            actorCheckBox.setChecked(actor.isSelected());
        }


        private void setCheckboxOnThick(Actor actor) {

            actorCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {


                actor.setSelected(isChecked);
                if (isChecked) {
                    selectedActors.add(actor);
                    Log.d("Actors ID", actor.getName());
                } else {

                    selectedActors.remove(actor);
                    Log.d("Actors Adapter", "actor is selected" + isChecked);
                    Log.d("Actors removed", actor.getName());
                }

                actor.setSelected(isChecked);
            });
        }

    }


}
