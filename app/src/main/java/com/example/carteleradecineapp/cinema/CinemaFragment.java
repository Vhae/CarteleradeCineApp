package com.example.carteleradecineapp.cinema;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carteleradecineapp.CarteleraActivity;
import com.example.carteleradecineapp.CinemaLocationActivity;
import com.example.carteleradecineapp.R;
import com.example.carteleradecineapp.models.Cinema;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CinemaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CinemaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    ImageView imgCinema;
    TextView txtCineName,txtDireccion;
    Button btnCartelera,btnUbicacion;
    Cinema cinema;
    int page;
    double lat,lng;
    LatLng latLng;
    // TODO: Rename and change types of parameters

    public CinemaFragment() {
        // Required empty public constructor
    }


    public static CinemaFragment newInstance(Parcelable parcel) {
        CinemaFragment fragment = new CinemaFragment();
        Bundle args = new Bundle();
        args.putParcelable("parcel",parcel);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootview=inflater.inflate(R.layout.fragment_cinema, container, false);

        btnUbicacion=rootview.findViewById(R.id.btnUbicacion);
        btnCartelera=rootview.findViewById(R.id.btnCartelera);
        txtCineName=rootview.findViewById(R.id.txtCineName);
        txtDireccion=rootview.findViewById(R.id.txtDireccion);
        imgCinema=rootview.findViewById(R.id.imgCinema);
        btnCartelera.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent carteleraIntent=new Intent(getContext(), CarteleraActivity.class);
                carteleraIntent.putExtra("page",page);
                startActivity(carteleraIntent);
            }
        });
        btnUbicacion.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent cinemaLocationIntent=new Intent(getContext(), CinemaLocationActivity.class);
                cinemaLocationIntent.putExtra("lat",lat);
                cinemaLocationIntent.putExtra("lng",lng);
                startActivity(cinemaLocationIntent);

            }
        });

        if (getArguments() != null) {
            cinema= Parcels.unwrap(getArguments().getParcelable("parcel"));
            Picasso.with(getContext()).load(cinema.getImgUrlCine()).into(imgCinema);
            txtCineName.setText(cinema.getNombreCine());
            txtDireccion.setText(cinema.getDireccionCine());
            page=cinema.getNumCartelera();
            LatLng latLng=cinema.getLatLng();
            lat=latLng.latitude;
            lng=latLng.longitude;
        }
        return rootview;

    }


}