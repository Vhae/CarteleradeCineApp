package com.example.carteleradecineapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class CinemaLocationActivity extends AppCompatActivity implements OnMapReadyCallback {
    double lat,lng;
    LatLng latLng;
    private GoogleMap nMap;
    private SupportMapFragment nMapFragment;
    Marker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_location);

        nMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (nMapFragment != null) {
            nMapFragment.getMapAsync(this);
        }

        if(getIntent().hasExtra("lat") && getIntent().hasExtra("lng")){
            lat=getIntent().getDoubleExtra("lat",-12.111794);
            lng=getIntent().getDoubleExtra("lng",-77.012494);
            latLng=new LatLng(lat,lng);
            Log.e("error","3 "+lat);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        float zoomLevel=20f;
        nMap = googleMap;
        Log.e("error","4 "+latLng.latitude);
        nMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        nMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel));

        marker = googleMap.addMarker(new MarkerOptions().position(latLng));
    }

}