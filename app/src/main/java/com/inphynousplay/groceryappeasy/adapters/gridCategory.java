package com.inphynousplay.groceryappeasy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.inphynousplay.groceryappeasy.R;

import java.util.List;

public class gridCategory extends BaseAdapter {
    private List<Integer> images;
    private  List<String> names;
    private Context mContext;
    private LayoutInflater layoutInflater;

    public gridCategory(List<Integer> images, List<String> names, Context mContext) {
        this.images = images;
        this.names = names;
        this.mContext = mContext;
        layoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1 = layoutInflater.inflate(R.layout.layout_grid_category,null);
        ImageView img = view1.findViewById(R.id.catimage);
        img.setImageResource(images.get(i));
        TextView name= view1.findViewById(R.id.grocery);
        name.setText(names.get(i));
        return view1;
    }
}
