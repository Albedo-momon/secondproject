package com.inphynousplay.groceryappeasy.adapters;

import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.inphynousplay.groceryappeasy.R;

public class FruitProductViewHolder extends RecyclerView.ViewHolder {
    public TextView fruitName,fruitDisc,fruitPrice,fruitUnit,textView;
    public ImageView fruitView;

    public FruitProductViewHolder(@NonNull View itemView) {
        super(itemView);
        fruitView = itemView.findViewById(R.id.productimage);
        fruitName = itemView.findViewById(R.id.productname);
        fruitDisc = itemView.findViewById(R.id.productdisc);
        fruitPrice = itemView.findViewById(R.id.productprice);
        fruitUnit = itemView.findViewById(R.id.productunit);
        textView =itemView.findViewById(R.id.productprice2);
        textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

    }
}
