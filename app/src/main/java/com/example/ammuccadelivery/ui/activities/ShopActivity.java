package com.example.ammuccadelivery.ui.activities;


import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ammuccadelivery.R;
import com.example.ammuccadelivery.dataModels.Prodotto;
import com.example.ammuccadelivery.dataModels.Restaurant;
import com.example.ammuccadelivery.ui.adapters.ProductAdapters;


import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity implements ProductAdapters.OnQuanityChangedListener{

    // UI components
    private TextView shopNameTv, shopAddress, totalTxtView;
    private Button checkout;
    private ProgressBar progressBar;
    private ImageView restaurantIv;

    // RecyclerView components
    private RecyclerView productRv;
    private LinearLayoutManager layoutManager;
    private ProductAdapters adapter;


    // data model
    private Restaurant restaurant;


    private float total = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        shopNameTv = findViewById(R.id.shop_name_tv);
        shopAddress = findViewById(R.id.shop_desc_tv);
        totalTxtView = findViewById(R.id.total_tv);
        restaurantIv = findViewById(R.id.shop_iv);

        checkout = findViewById(R.id.checkout);
        progressBar = findViewById(R.id.progress);
        productRv = findViewById(R.id.product_rv);

        restaurant = getRestaurant();
        //restaurant.setImageUrl("https://rovato5stelle.files.wordpress.com/2013/11/mcdonald.jpg");
        //Glide.with(restaurantIv).load(R.drawable.kfc);
        restaurant.setProdotto(getProdotto());
        shopNameTv.setText(restaurant.getNome());
        shopAddress.setText(restaurant.getVia());
        Glide.with(this).load(restaurant.getLogo()).into(restaurantIv);
        progressBar.setMax((int)restaurant.getMinOrdine() * 100);

        layoutManager = new LinearLayoutManager(this);
        adapter = new ProductAdapters(this, restaurant.getProdotto());
        adapter.setOnQuanityChangedListener(this);

        productRv.setLayoutManager(layoutManager);
        productRv.setAdapter(adapter);


    }

    //TODO hardcoded
    private Restaurant getRestaurant() {
        return new Restaurant(10F,"McDonald's","via Tiburtina",R.drawable.mcdonalds_2018);
    }

    //TODO hardcoded
    private ArrayList<Prodotto> getProdotto() {
        ArrayList<Prodotto> products = new ArrayList<>();
        products.add(new Prodotto("McMenu", 7));
        products.add(new Prodotto("McMenu", 7));
        products.add(new Prodotto("McMenu", 7));
        products.add(new Prodotto("McMenu", 7));
        products.add(new Prodotto("McMenu", 7));
        return products;

    }

    private void updateTotal(float item){
        total= total + item;
        totalTxtView.setText("Total: ".concat( String.valueOf(total)));
    }

    private void updateProgress(int progress){
        progressBar.setProgress(progress);
    }

    @Override
    public void onChange(float price) {
        updateTotal(price);
        updateProgress((int)total*100);
    }
}