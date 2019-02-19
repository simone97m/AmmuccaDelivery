package com.example.ammuccadelivery.ui.activities;


import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.example.ammuccadelivery.R;
import com.example.ammuccadelivery.dataModels.Prodotto;
import com.example.ammuccadelivery.dataModels.Restaurant;
import com.example.ammuccadelivery.services.RestController;
import com.example.ammuccadelivery.ui.adapters.ProductAdapters;


import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity implements ProductAdapters.OnQuanityChangedListener, View.OnClickListener/*,Response.Listener<String>,Response.ErrorListener*/{


    private static final String TAG = ShopActivity.class.getSimpleName();

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

    private RestController restController;
    ArrayList<Restaurant> arrayList;






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
        checkout.setOnClickListener(this);

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

        restController = new RestController(this);
        //restController.getRequest(Restaurant.ENDPOIN,this,this);


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
        if(total >= restaurant.getMinOrdine()){
            checkout.setEnabled(true);
        }else{
            checkout.setEnabled(false);
        }
    }

    private void updateProgress(int progress){
        progressBar.setProgress(progress);
    }

    @Override
    public void onChange(float price) {
        updateTotal(price);
        updateProgress((int)total*100);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.checkout){
            Intent intent = new Intent(this, CheckoutActivity.class);
            startActivity(intent);
        }
    }

    /*@Override
    public void onErrorResponse(VolleyError error) {
        Log.e(TAG,error.getMessage());
        Toast.makeText(this,error.getMessage(),Toast.LENGTH_LONG).show();

    }

    @Override
    public void onResponse(String response) {
        try {
           JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i<jsonArray.length(); i++){
                arrayList.add(new Restaurant(jsonArray.getJSONObject(i)));
            }
            adapter.setData(arrayList);
        } catch (JSONException e) {
                Log.e(TAG,e.getMessage());
            }
    }*/
}