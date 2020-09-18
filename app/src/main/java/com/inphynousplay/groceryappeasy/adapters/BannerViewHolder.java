package com.inphynousplay.groceryappeasy.adapters;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.inphynousplay.groceryappeasy.R;

public class BannerViewHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;
    public BannerViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.bannerimage);
    }
}
