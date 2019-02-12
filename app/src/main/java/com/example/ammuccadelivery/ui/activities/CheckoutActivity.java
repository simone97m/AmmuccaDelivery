package com.example.ammuccadelivery.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.ammuccadelivery.R;
import com.example.ammuccadelivery.dataModels.Ordine;
import com.example.ammuccadelivery.dataModels.Prodotto;
import com.example.ammuccadelivery.dataModels.Restaurant;
import com.example.ammuccadelivery.ui.adapters.CheckoutAdapters;

import java.util.ArrayList;

public class CheckoutActivity extends AppCompatActivity implements View.OnClickListener, CheckoutAdapters.onItemRemovedListener{

    private TextView restaturantTv,restaurantAdress,totalTv,ord_min_rv;
    private RecyclerView productRv;
    private Button payBtn;
    private LinearLayoutManager layoutManager;
    private CheckoutAdapters adapter;




    private Ordine order;
    private float total = 15;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        restaturantTv = findViewById(R.id.restaurant_name);
        restaurantAdress = findViewById(R.id.restaurant_adress);
        totalTv = findViewById(R.id.total_tv);
        productRv = findViewById(R.id.product_rv);
        payBtn = findViewById(R.id.pay_btn);
        ord_min_rv = findViewById(R.id.ord_min_tv);

        // Initialize datamodel object
        order = getOrder();
        total = order.getTotal();

        // setup recyclerview
        layoutManager = new LinearLayoutManager(this);
        productRv.setLayoutManager(layoutManager);
        adapter = new CheckoutAdapters(this,order);
        adapter.setOnItemRemovedListener(this);
        productRv.setAdapter(adapter);

        //set click listener for button
        payBtn.setOnClickListener(this);

        bindData();

    }


    private void bindData(){
        restaturantTv.setText(order.getRestaurant().getNome());
        restaurantAdress.setText(order.getRestaurant().getVia());
        totalTv.setText(String.valueOf(order.getTotal()));
        ord_min_rv.setText(String.valueOf(order.getRestaurant().getMinOrdine()));



    }


    //TODO hardcoded

    private Ordine getOrder(){

        Ordine mockOrder =  new Ordine();
        mockOrder.setProducts(getProducts());
        mockOrder.setRestaurant(getRestaurant());
        mockOrder.setTotal(total);

        return mockOrder;
    }


    private Restaurant getRestaurant() {
        return new Restaurant(10, "McDonald's", "Tiburtina",R.drawable.mcdonalds_2018);
    }

    //TODO hardcoded
    private ArrayList<Prodotto> getProducts() {
        ArrayList<Prodotto> products = new ArrayList<>();
        products.add(new Prodotto("McMenu", 5));
        products.add(new Prodotto("McMenu", 5));
        products.add(new Prodotto("McMenu", 5));

        return products;

    }

    @Override
    public void onClick(View view) {
        //TODO manageClick
    }

    @Override
    public void onItemRemoved(float subtotal) {
        updateTotal(subtotal);
    }

    private void updateTotal(float subtotal) {
        if(order.getTotal() == 0) return;
        order.setTotal(order.getTotal()-subtotal);
        totalTv.setText(String.valueOf(order.getTotal()));
    }
}
