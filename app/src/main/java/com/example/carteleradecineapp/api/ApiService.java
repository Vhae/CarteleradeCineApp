package com.example.carteleradecineapp.api;

import com.example.carteleradecineapp.models.MoviesList;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiService {

    @GET("popular?api_key=c1b6e8c8116cfb5d77b4709d3fd7ffe3")
    Call<MoviesList> getPopularMovies(@Query("language") String language, @Query("page") int page);

}
