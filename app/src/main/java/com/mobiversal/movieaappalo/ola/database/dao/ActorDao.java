package com.mobiversal.movieaappalo.ola.database.dao;


        import androidx.room.Dao;
        import androidx.room.Insert;
        import androidx.room.Query;

        import com.mobiversal.movieaappalo.ola.model.Actor;

        import java.util.List;

@Dao
public interface ActorDao {

    @Query("SELECT * FROM actor")
    public List<Actor> getAllActors();

    @Insert
    public void saveActor(Actor actor);

    @Query("DELETE  FROM actor" )
    public void deleteAll();
}
