package com.inphynousplay.groceryappeasy.adapters;

import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.inphynousplay.groceryappeasy.R;

public class PackagedProductViewHolder extends RecyclerView.ViewHolder {
    public TextView vegetableName, vegetableDisc, vegetablePrice,vegetableUnit,textView,vegetableMRP,vegetableSavings,outOfStock,buyProductNow;
    public ImageView vegetableView;

    public PackagedProductViewHolder(@NonNull View itemView) {
        super(itemView);
        vegetableView = itemView.findViewById(R.id.productimage);
        vegetableName = itemView.findViewById(R.id.productname);
        vegetableDisc = itemView.findViewById(R.id.productdisc);
        vegetablePrice = itemView.findViewById(R.id.productprice);
        vegetableUnit = itemView.findViewById(R.id.productunit);
        vegetableMRP = itemView.findViewById(R.id.productprice2);
        vegetableSavings = itemView.findViewById(R.id.saving);
        outOfStock = itemView.findViewById(R.id.outofstock);
        buyProductNow = itemView.findViewById(R.id.buyproductnow);
        textView =itemView.findViewById(R.id.productprice2);
        //for text strike through
        textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }
}
