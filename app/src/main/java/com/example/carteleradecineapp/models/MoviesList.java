package com.example.carteleradecineapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MoviesList {
    @SerializedName("page")
    int page;

    @SerializedName("total_results")
    int total_Results;

    @SerializedName("total_pages")
    int total_pages;

    @SerializedName("results")
    ArrayList<MovieInfo> results;

    //----Getter and Setters


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_Results() {
        return total_Results;
    }

    public void setTotal_Results(int total_Results) {
        this.total_Results = total_Results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public ArrayList<MovieInfo> getResults() {
        return results;
    }

    public void setResults(ArrayList<MovieInfo> results) {
        this.results = results;
    }
}
