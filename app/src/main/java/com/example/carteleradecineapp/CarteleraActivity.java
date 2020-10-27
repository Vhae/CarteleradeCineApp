package com.example.carteleradecineapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.Toast;

import com.example.carteleradecineapp.adapters.MovieAdapter;
import com.example.carteleradecineapp.api.ApiClient;
import com.example.carteleradecineapp.api.ApiService;
import com.example.carteleradecineapp.models.MovieInfo;
import com.example.carteleradecineapp.models.MoviesList;

import java.util.ArrayList;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarteleraActivity extends AppCompatActivity {

    String page;
    RecyclerView recyclerViewCartelera;
    Random rand = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartelera);
        recyclerViewCartelera=findViewById(R.id.recyclerViewCartelera);
        recyclerViewCartelera.setLayoutManager(new LinearLayoutManager(this));
        int currentPage;
        if(getIntent().hasExtra("page")){
           int actualPage=getIntent().getIntExtra("page",1);
            CallPopularMovies("es-ES",actualPage);
            currentPage=actualPage;
        }

    }

    private void CallPopularMovies(String language, int page) {
        ApiService apiService= ApiClient.getApiClient().create(ApiService.class);
        Call<MoviesList> call=apiService.getPopularMovies(language,page);

        call.enqueue(new Callback<MoviesList>() {
            @Override
            public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
                if(response.isSuccessful()){
                    MoviesList moviesList=response.body();
                    recyclerViewCartelera.setAdapter(new MovieAdapter(moviesList,getBaseContext()));
                }else{
                    Toast.makeText(CarteleraActivity.this, "Code:"+response.code(), Toast.LENGTH_SHORT).show();
                    Log.e("Error:","code:"+response.code());
                }
            }

            @Override
            public void onFailure(Call<MoviesList> call, Throwable t) {
                Log.e("Error:","code:"+t.getMessage());
            }
        });
    }

}