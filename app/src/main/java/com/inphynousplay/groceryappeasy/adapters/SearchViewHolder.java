package com.inphynousplay.groceryappeasy.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.inphynousplay.groceryappeasy.R;

public class SearchViewHolder extends RecyclerView.ViewHolder {
    public TextView productSearchName,productSearchDisc,productSearchPrice,producSearchtUnit;
    public ImageView imageView;
    public SearchViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.milkimage);
        productSearchName = itemView.findViewById(R.id.milkname);
        productSearchDisc = itemView.findViewById(R.id.milkdisc);
        productSearchPrice = itemView.findViewById(R.id.milkprice);
        producSearchtUnit = itemView.findViewById(R.id.milkunit);

    }
}
