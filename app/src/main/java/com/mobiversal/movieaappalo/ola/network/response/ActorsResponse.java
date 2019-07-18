package com.mobiversal.movieaappalo.ola.network.response;

import com.google.gson.annotations.SerializedName;
import com.mobiversal.movieaappalo.ola.model.Actor;

import java.util.List;

public class ActorsResponse {

    @SerializedName("page")
    private int page;

    @SerializedName("total_results")
    private int totalResults;

    @SerializedName("total_pages")
    private int totalPages;

    private List<Actor> results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<Actor> getResults() {
        return results;
    }

    public void setResults(List<Actor> results) {
        this.results = results;
    }


}
