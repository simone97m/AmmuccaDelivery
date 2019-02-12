package com.example.ammuccadelivery.ui.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ammuccadelivery.R;
import com.example.ammuccadelivery.dataModels.Ordine;
import com.example.ammuccadelivery.dataModels.Prodotto;



import java.util.ArrayList;

public class CheckoutAdapters extends RecyclerView.Adapter<CheckoutAdapters.OrderProductViewHolder>{

    private Ordine order;
    private Context context;
    private LayoutInflater inflater;


    public  CheckoutAdapters(Context context, Ordine order){

        this.order = order;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public interface onItemRemovedListener{
        void onItemRemoved(float subtotal);

    }


    private onItemRemovedListener onItemRemovedListener;


    public CheckoutAdapters.onItemRemovedListener getOnItemRemovedListener() {
        return onItemRemovedListener;
    }

    public void setOnItemRemovedListener(CheckoutAdapters.onItemRemovedListener onItemRemovedListener) {
        this.onItemRemovedListener = onItemRemovedListener;
    }

    @NonNull
    @Override
    public OrderProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new OrderProductViewHolder(inflater.inflate(R.layout.item_checkout ,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderProductViewHolder orderProductViewHolder, int i) {
        Prodotto product = order.getProducts().get(i);
        orderProductViewHolder.productNameTv.setText(product.getNome());
        orderProductViewHolder.quantityTv.setText("1x");
        orderProductViewHolder.subtotalTv.setText(String.valueOf(5));
        //orderProductViewHolder.subtotalTv.setText(String.valueOf(product.getSubtotal()));
    }

    @Override
    public int getItemCount() {
        return order.getProducts().size();
    }

    private void removeItem(int index){
        order.getProducts().remove(index);
        notifyItemRemoved(index);

    }


    public class OrderProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView quantityTv,productNameTv,subtotalTv;
        public ImageButton removeBtn;


        public OrderProductViewHolder(@NonNull View itemView) {
            super(itemView);
            quantityTv = itemView.findViewById(R.id.quantity_tv);
            productNameTv = itemView.findViewById(R.id.product_name_tv);
            subtotalTv = itemView.findViewById(R.id.subtotal_tv);
            removeBtn = itemView.findViewById(R.id.remove_btn);
            removeBtn.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            AlertDialog.Builder removeAlert = new AlertDialog.Builder(context);
            removeAlert.setTitle(R.string.be_careful)
                    .setMessage(R.string.remove_title)
                    .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if((order.getTotal() - 5) < order.getRestaurant().getMinOrdine())return;
                            onItemRemovedListener.onItemRemoved(order.getProducts().get(getAdapterPosition()).getPrezzo());
                            removeItem(getAdapterPosition());

                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    })
                    .create()
                    .show();

        }
    }
}
