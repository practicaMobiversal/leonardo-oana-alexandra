package com.mobiversal.movieaappalo.ola.network.response;

import com.google.gson.annotations.SerializedName;
import com.mobiversal.movieaappalo.ola.model.Genre;

import java.util.List;

public class GenresResponse {

    @SerializedName("genres")
    private List<Genre> results;


    public List<Genre> getResults() {
        return results;
    }

    public void setResults(List<Genre> results) {
        this.results = results;
    }
}
