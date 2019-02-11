package com.example.ammuccadelivery.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ammuccadelivery.R;
import com.example.ammuccadelivery.dataModels.Prodotto;

import java.util.ArrayList;

public class ProductAdapters extends RecyclerView.Adapter {


    private LayoutInflater mInflter;
    private ArrayList<Prodotto> data;


    public ProductAdapters(Context context, ArrayList<Prodotto> data) {

        this.data = data;
        mInflter = LayoutInflater.from(context);


    }

    public interface OnQuanityChangedListener {
        void onChange(float price);

    }

    public OnQuanityChangedListener getOnQuanityChangedListener() {
        return onQuanityChangedListener;
    }

    public void setOnQuanityChangedListener(OnQuanityChangedListener onQuanityChangedListener) {
        this.onQuanityChangedListener = onQuanityChangedListener;
    }

    private OnQuanityChangedListener onQuanityChangedListener;


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = mInflter.inflate(R.layout.item_product, viewGroup, false);
        return new ProductViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Prodotto product = data.get(i);
        ProductViewHolder vh = (ProductViewHolder) viewHolder;

        vh.productName.setText(product.getNome());
        vh.productPrice.setText(String.valueOf(product.getPrezzo()));
        vh.productQty.setText(String.valueOf(product.getQuantita()));


    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView productName, productPrice, productQty;
        public Button addBtn, removeBtn;


        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.item_name);
            productPrice = itemView.findViewById(R.id.item_price);


            productQty = itemView.findViewById(R.id.item_picker).findViewById(R.id.quantity_tv);

            addBtn = itemView.findViewById(R.id.item_picker).
                    findViewById(R.id.add_btn);

            removeBtn = itemView.findViewById(R.id.item_picker)
                    .findViewById(R.id.remove_btn);

            addBtn.setOnClickListener(this);
            removeBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Prodotto product = data.get(getAdapterPosition());


            if (view.getId() == R.id.add_btn) {
                product.increaseQuantita();
                notifyItemChanged(getAdapterPosition());
                onQuanityChangedListener.onChange(product.getPrezzo());

            } else if (view.getId() == R.id.remove_btn) {
                if(product.getQuantita() == 0)return;
                product.decreaseQuantita();
                notifyItemChanged(getAdapterPosition());
                onQuanityChangedListener.onChange(product.getPrezzo() * -1);


            }


        }
    }
}
