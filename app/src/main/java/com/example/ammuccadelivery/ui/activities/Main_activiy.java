package com.example.ammuccadelivery.ui.activities;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ammuccadelivery.R;
import com.example.ammuccadelivery.dataModels.Restaurant;
import com.example.ammuccadelivery.services.RestController;
import com.example.ammuccadelivery.ui.adapters.RestaurantAdapters;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class Main_activiy extends AppCompatActivity implements Response.Listener<String>,Response.ErrorListener{

    private static final String TAG = Main_activiy.class.getSimpleName();
    RecyclerView restaurantRV;
    RecyclerView.LayoutManager layoutManager;
    RestaurantAdapters adapters;
    ArrayList<Restaurant> arrayList;

    private RestController restController;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        restaurantRV = findViewById(R.id.places_rv);
        layoutManager = new LinearLayoutManager(this);
       adapters = new RestaurantAdapters(this,getData());

        restaurantRV.setLayoutManager(layoutManager);
        restaurantRV.setAdapter(adapters);

        restController = new RestController(this);
        restController.getRequest(Restaurant.ENDPOIN,this,this);





        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://5ba19290ee710f0014dd764c.mockapi.io/api/v1/restaurant";

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,// http metodo request0
                url, //Server link
                new Response.Listener<String>() {//Listener for successful response
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG,response);
                        //Start parsing
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i<jsonArray.length();i++){
                                Restaurant restaurant = new Restaurant(jsonArray.getJSONObject(i));
                                arrayList.add(restaurant);
                            }
                            adapters.setData(arrayList);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {//Listener for error response
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,error.getMessage() + " " + error.networkResponse.statusCode);
            }
        });

        queue.add(stringRequest);


    }

    private ArrayList<Restaurant> getData(){
        arrayList = new ArrayList<>();
        Restaurant mc = new Restaurant(8,"Ristorante: McDonald's","Via Tiburtina", R.drawable.mcdonalds_2018);
        Restaurant bk = new Restaurant(8,"Ristorante: Burger King","Via tiburtina",R.drawable.burgerking);
        Restaurant mond = new Restaurant(10,"Ristorante: 100 Mondadidos","Via Tiburtina",R.drawable.mondadidoss);
        Restaurant ck = new Restaurant(6,"Ristorante: Ciro Kebab","Via Tiburtina",R.drawable.cirokebab);
        Restaurant kfc = new Restaurant(12,"Ristorante: KFC","Centro commerciale Ponte di Nona",R.drawable.kfc);
        arrayList.add(mc);
        arrayList.add(bk);
        arrayList.add(mond);
        arrayList.add(kfc);
        arrayList.add(ck);
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
        }else if(item.getItemId()==R.id.register_menu ){
              startActivity(new Intent(this,Registrazione_activity.class));
        } else if (item.getItemId()==R.id.menu_griglia){
            adapters.setGrid(!adapters.isGrid());
            layoutManager =adapters.isGrid() ? new GridLayoutManager(this,2) : new LinearLayoutManager(this);
            restaurantRV.setLayoutManager(layoutManager);
            restaurantRV.setAdapter(adapters);
        }else if(item.getItemId()==R.id.menu_carello){
            startActivity(new Intent(this,ShopActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
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
            adapters.setData(arrayList);
        } catch (JSONException e) {
            Log.e(TAG,e.getMessage());
        }
    }
}
