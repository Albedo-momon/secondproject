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

public class GridPackagedFoodAdapter extends BaseAdapter {
    private List<Integer> images;
    private  List<String> names;
    private Context mContext;
    private LayoutInflater layoutInflater;

    public GridPackagedFoodAdapter(List<Integer> images, List<String> names, Context mContext, LayoutInflater layoutInflater) {
        this.images = images;
        this.names = names;
        this.mContext = mContext;
        this.layoutInflater = layoutInflater;
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
        View view1 = layoutInflater.inflate(R.layout.layout_grid_subgrocery,null);
        ImageView img = view1.findViewById(R.id.gridimage);
        img.setImageResource(images.get(i));
        TextView name= view1.findViewById(R.id.gridtext);
        name.setText(names.get(i));
        return view1;
    }
}
