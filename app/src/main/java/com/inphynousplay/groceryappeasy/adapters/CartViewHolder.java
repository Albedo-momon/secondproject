package com.inphynousplay.groceryappeasy.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.inphynousplay.groceryappeasy.Interface.itemClickListener;
import com.inphynousplay.groceryappeasy.R;

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txtProductName,txtProductPrice, txtProductQuantity,txtProductUnit;
    private itemClickListener itemClickListener;

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);

        txtProductName = itemView.findViewById(R.id.productnamecart);
        txtProductPrice = itemView.findViewById(R.id.productpricecart);
        txtProductQuantity = itemView.findViewById(R.id.productquantitycart);
        txtProductUnit = itemView.findViewById(R.id.productunitcart);

    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);

    }
    public void setItemClickListener(itemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
}
