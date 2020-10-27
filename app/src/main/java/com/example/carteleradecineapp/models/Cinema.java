package com.example.carteleradecineapp.models;


import com.google.android.gms.maps.model.LatLng;

import org.parceler.Parcel;

@Parcel
public class Cinema {
    String nombreCine;
    String direccionCine;
    String imgUrlCine;
    LatLng latLng;
    int numCartelera;

//-----Getters and Setters

    public String getNombreCine() {
        return nombreCine;
    }

    public void setNombreCine(String nombreCine) {
        this.nombreCine = nombreCine;
    }

    public String getDireccionCine() {
        return direccionCine;
    }

    public void setDireccionCine(String direccionCine) {
        this.direccionCine = direccionCine;
    }

    public String getImgUrlCine() {
        return imgUrlCine;
    }

    public void setImgUrlCine(String imgUrlCine) {
        this.imgUrlCine = imgUrlCine;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public int getNumCartelera() {
        return numCartelera;
    }

    public void setNumCartelera(int numCartelera) {
        this.numCartelera = numCartelera;
    }
}
