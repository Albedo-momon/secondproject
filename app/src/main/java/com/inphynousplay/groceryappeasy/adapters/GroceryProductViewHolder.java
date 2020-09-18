package com.inphynousplay.groceryappeasy.adapters;

import android.graphics.Paint;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.inphynousplay.groceryappeasy.Interface.itemClickListener;
import com.inphynousplay.groceryappeasy.R;

public class GroceryProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView productName,productDisc,productPrice,productUnit,textView,textViewmrp;
    public ImageView imageView;
    public itemClickListener listener;
    public GroceryProductViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.productimage);
        productName = itemView.findViewById(R.id.productname);
        productDisc = itemView.findViewById(R.id.productdisc);
        productPrice = itemView.findViewById(R.id.productprice);
        productUnit = itemView.findViewById(R.id.productunit);
        textView =itemView.findViewById(R.id.productprice2);
        textViewmrp =itemView.findViewById(R.id.mrp);

        textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        textViewmrp.setPaintFlags(textViewmrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);



    }
public void setItemClickListener(itemClickListener listener){
        this.listener = listener;
}

    @Override
    public void onClick(View v) {
        listener.onClick(v, getAdapterPosition(),false);

    }
}
