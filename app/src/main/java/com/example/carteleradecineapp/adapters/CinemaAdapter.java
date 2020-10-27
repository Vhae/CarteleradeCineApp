package com.example.carteleradecineapp.adapters;

import android.os.Parcel;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.carteleradecineapp.cinema.CinemaFragment;
import com.example.carteleradecineapp.models.Cinema;

import org.parceler.Parcels;

import java.util.ArrayList;

public class CinemaAdapter extends FragmentPagerAdapter {
    ArrayList<Cinema> cinemaArrayList;
    FragmentManager fm;

    public CinemaAdapter(@NonNull FragmentManager fm,ArrayList<Cinema> cinemaArrayList) {
        super(fm);
        this.cinemaArrayList=cinemaArrayList;
        this.fm=fm;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        fragment= new CinemaFragment().newInstance(Parcels.wrap(cinemaArrayList.get(position)));
        return fragment;
    }

    @Override
    public int getCount() {
        return cinemaArrayList.size();
    }
}
