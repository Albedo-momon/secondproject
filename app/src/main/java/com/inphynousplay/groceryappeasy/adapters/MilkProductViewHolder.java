package com.inphynousplay.groceryappeasy.adapters;

import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.inphynousplay.groceryappeasy.R;

public class MilkProductViewHolder extends RecyclerView.ViewHolder {

    public TextView milkName,milkDisc,milkPrice,milkUnit,textView;
    public ImageView milkView;

    public MilkProductViewHolder(@NonNull View itemView) {
        super(itemView);

        milkView =  itemView.findViewById(R.id.productimage);
        milkName = itemView.findViewById(R.id.productname);
        milkDisc = itemView.findViewById(R.id.productdisc);
        milkPrice = itemView.findViewById(R.id.productprice);
        milkUnit = itemView.findViewById(R.id.productunit);
        textView =itemView.findViewById(R.id.productprice2);
        textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }
}
