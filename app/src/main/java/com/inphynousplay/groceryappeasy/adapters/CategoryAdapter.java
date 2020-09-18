package com.inphynousplay.groceryappeasy.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.inphynousplay.groceryappeasy.CategoryActivity.Backery_Cat_Activity;
import com.inphynousplay.groceryappeasy.CategoryActivity.Fruit_And_Vegetable_Cat_Activity;
import com.inphynousplay.groceryappeasy.CategoryActivity.Grocery_Cat_Activity;
import com.inphynousplay.groceryappeasy.CategoryActivity.HomeAndKitchen_Activity;
import com.inphynousplay.groceryappeasy.CategoryActivity.Milk_Product_Cat_Activity;
import com.inphynousplay.groceryappeasy.CategoryActivity.Packaged_Food;
import com.inphynousplay.groceryappeasy.CategoryActivity.Personal_Care_Activity;
import com.inphynousplay.groceryappeasy.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    List <String> Titles;
    //List<String> Martitles;
    List<Integer> Images;

    LayoutInflater layoutInflater;

    public CategoryAdapter(List<String> titles, List<String> martitles, List<Integer> images, Context context) {
        Titles = titles;
        Images = images;
       // Martitles = martitles;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.layout_grid_category,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.EngText.setText(Titles.get(position));
        //holder.MarText.setText(Martitles.get(position));
        holder.CatImg.setImageResource(Images.get(position));

    }

    @Override
    public int getItemCount() {
        return Titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView CatImg;
        TextView EngText, MarText;
        private final Context context;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            CatImg = itemView.findViewById(R.id.catimage);
            EngText = itemView.findViewById(R.id.grocery);
           // MarText = itemView.findViewById(R.id.kirana);
            context = itemView.getContext();

            itemView.setClickable(true);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {


           Intent intent = null;
            switch (getAdapterPosition()){
                case 0:
                    intent =  new Intent(context, Grocery_Cat_Activity.class);

                    break;

                case 1:
                    intent =  new Intent(context, Packaged_Food.class);
                    intent.putExtra("Category","fruit");
                    break;
                case 2:
                    intent =  new Intent(context, Fruit_And_Vegetable_Cat_Activity.class);
                    break;
                case 3:
                    intent =  new Intent(context, Milk_Product_Cat_Activity.class);
                    break;
                case 4:
                    intent =  new Intent(context, Backery_Cat_Activity.class);
                    break;
                case 5:
                    intent =  new Intent(context, HomeAndKitchen_Activity.class);
                    break;
                case 6:
                    intent =  new Intent(context, Personal_Care_Activity.class);
                    break;



            }
            context.startActivity(intent);

        }
    }
}
