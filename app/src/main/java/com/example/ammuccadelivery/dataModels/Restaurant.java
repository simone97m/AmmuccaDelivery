package com.example.ammuccadelivery.dataModels;

import android.media.Image;
import android.support.annotation.DrawableRes;

public class Restaurant {
    private float minOrdine = 8;
    private String nome;
    private String via;
    private @DrawableRes int logo;

    public Restaurant(float minOrdine, String nome, String via,@DrawableRes int logo) {
        this.minOrdine = minOrdine;
        this.nome = nome;
        this.via = via;
        this.logo = logo;
    }

    public float getMinOrdine() {
        return minOrdine;
    }

    public void setMinOrdine(float minOrdine) {
        this.minOrdine = minOrdine;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public @DrawableRes int getLogo() {
        return logo;
    }

    public void setLogo(@DrawableRes int logo) {
        this.logo = logo;
    }
}
