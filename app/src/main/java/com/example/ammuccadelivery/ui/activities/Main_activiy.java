package com.example.ammuccadelivery.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.ammuccadelivery.R;
import com.example.ammuccadelivery.dataModels.Restaurant;
import com.example.ammuccadelivery.ui.adapters.RestaurantAdapters;

import java.util.ArrayList;

public class Main_activiy extends AppCompatActivity {

    RecyclerView restaurantRV;
    RecyclerView.LayoutManager layoutManager;
    RestaurantAdapters adapters;
    ArrayList<Restaurant> arrayList;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        restaurantRV = findViewById(R.id.places_rv);
        layoutManager = new LinearLayoutManager(this);
        adapters = new RestaurantAdapters(this,getData());

        restaurantRV.setLayoutManager(layoutManager);
        restaurantRV.setAdapter(adapters);
    }

    private ArrayList<Restaurant> getData(){
        arrayList = new ArrayList<>();
        Restaurant mc = new Restaurant(8,"Ristorante: McDonald's","Via Tiburtina", R.drawable.mcdonalds);
        Restaurant bk = new Restaurant(8,"Ristorante: Burger King","Via tiburtina",R.drawable.burgerking);
        Restaurant mond = new Restaurant(10,"Ristorante: 100 Mondadidos","Via Tiburtina",R.drawable.mondadidoss);
        arrayList.add(mc);
        arrayList.add(bk);
        arrayList.add(mond);
        return arrayList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.login_menu){
            startActivity(new Intent(this, Login_activity.class));
            return true;
        }else if(item.getItemId()==R.id.checkout_menu){
            startActivity(new Intent(this, CheckoutActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
