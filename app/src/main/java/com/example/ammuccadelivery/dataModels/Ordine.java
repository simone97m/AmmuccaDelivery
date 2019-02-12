package com.example.ammuccadelivery.dataModels;

import java.util.ArrayList;

public class Ordine {

    private Restaurant restaurant;
    private ArrayList<Prodotto> products;
    private float total;

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public ArrayList<Prodotto> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Prodotto> products) {
        this.products = products;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
