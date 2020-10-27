package com.example.carteleradecineapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Movie;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carteleradecineapp.adapters.CinemaAdapter;

import com.example.carteleradecineapp.models.Cinema;

import com.example.carteleradecineapp.singleton.Auth;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {
    ViewPager viewPagerCinema;
    public ArrayList<Cinema> cinemaArrayList=new ArrayList<Cinema>();
    LatLng latLng;
    Auth mAuth;
    String userToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth=new Auth();

        viewPagerCinema=findViewById(R.id.viewPagerCinema);

        Cinema cinema=new Cinema();
        cinema.setNombreCine("Cinemark Angamos");
        cinema.setDireccionCine("Av. Angamos Nº 1803 y/o Av. Tomas Marsano Nº 961- Surquillo");
        cinema.setImgUrlCine("http://www.ernestojerardo.com/wp-content/uploads/2015/12/CINEMARK-OPEN-PLAZA-ANGAMOS-1.jpg");
        cinema.setNumCartelera(1);
        double lat=-12.111794;
        double lng=-77.012494;
        latLng= new LatLng(lat,lng);
        cinema.setLatLng(latLng);
        cinemaArrayList.add(cinema);

        Cinema cinema2=new Cinema();
        cinema2.setNombreCine("Cinemark Jockey Plaza");
        cinema2.setDireccionCine("Av. Javier Prado Este 4200 Monterrico Surco");
        cinema2.setImgUrlCine("https://scontent.flim1-2.fna.fbcdn.net/v/t1.0-9/575369_649926488356082_1266131385_n.jpg?_nc_cat=104&_nc_sid=dd9801&_nc_ohc=UTaYwMkk_VQAX_3zzGg&_nc_ht=scontent.flim1-2.fna&oh=683720301a77141e3e67fd18e54236e0&oe=5F9E94F3");
        cinema2.setNumCartelera(2);
        lat=-12.085368;
        lng=-76.978790;
        latLng= new LatLng(lat,lng);
        cinema2.setLatLng(latLng);
        cinemaArrayList.add(cinema2);

        Cinema cinema3=new Cinema();
        cinema3.setNombreCine("Cinemark Gamarra");
        cinema3.setDireccionCine("Avenida Aviación 950 La Victoria, LIMA 13");
        cinema3.setImgUrlCine("https://www.america-retail.com/static//2018/11/Cinemark.jpg");
        cinema3.setNumCartelera(3);
        lat=-12.070628;
        lng=-77.011882;
        latLng= new LatLng(lat,lng);
        cinema3.setLatLng(latLng);
        cinemaArrayList.add(cinema3);

        viewPagerCinema.setAdapter(new CinemaAdapter(getSupportFragmentManager(),cinemaArrayList));


    }

    @Override
    public void onBackPressed() {
        mAuth.logOut();
        super.onBackPressed();
    }
}