package com.example.ammuccadelivery.ui.adapters;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ammuccadelivery.R;
import com.example.ammuccadelivery.dataModels.Restaurant;

import java.util.ArrayList;

public class RestaurantAdapters extends RecyclerView.Adapter {
    private LayoutInflater inflater;
    private ArrayList<Restaurant> data;
    private boolean isGrid;

    public RestaurantAdapters(Context context,ArrayList<Restaurant> data){
       inflater = LayoutInflater.from(context);
       this.data = data;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        int layout = isGrid ? R.layout.item_restaurant_griglia : R.layout.item_restaurant;
        View view = inflater.inflate(layout,viewGroup,false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        RestaurantViewHolder vh = (RestaurantViewHolder) viewHolder;
        vh.restaurantName.setText(data.get(position).getNome());
        vh.via.setText(data.get(position).getVia());
        vh.ordine.setText("Ordine minimo "+data.get(position).getMinOrdine());
        vh.logo.setImageResource(data.get(position).getLogo());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public boolean isGrid() {
        return isGrid;
    }

    public void setGrid(boolean grid) {
        isGrid = grid;
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder{
        public TextView restaurantName;
        public TextView via;
        public TextView ordine;
        public ImageView logo;
        public RestaurantViewHolder(View itemView){
            super(itemView);
            restaurantName=itemView.findViewById(R.id.nome_ristorante);
            via = itemView.findViewById(R.id.via_ristorante);
            ordine = itemView.findViewById(R.id.ordine_minimo);
            logo = itemView.findViewById(R.id.logo_restaurant);

        }
    }
}
