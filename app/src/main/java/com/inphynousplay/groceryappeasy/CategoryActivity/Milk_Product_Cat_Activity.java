package com.inphynousplay.groceryappeasy.CategoryActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.inphynousplay.groceryappeasy.CategoryActivity.SubCatActivity.GrocerySubActivity;
import com.inphynousplay.groceryappeasy.CategoryActivity.SubCatActivity.MilkSubActivity;
import com.inphynousplay.groceryappeasy.HomeActivity;
import com.inphynousplay.groceryappeasy.Models.GroceryProducts;
import com.inphynousplay.groceryappeasy.ProductDetail.ProductDetailActivity;
import com.inphynousplay.groceryappeasy.R;
import com.inphynousplay.groceryappeasy.adapters.GridGroceryAdapter;
import com.inphynousplay.groceryappeasy.adapters.MilkProductViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Milk_Product_Cat_Activity extends AppCompatActivity {

    GridView gridView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milk__product__cat_);
        gridView = findViewById(R.id.gridview);

        List<Integer> images = new ArrayList<>();
        List<String> names = new ArrayList<>();
        images.add(R.drawable.greentea);
        images.add(R.drawable.milk);
        images.add(R.drawable.beverages);

        names.add("Beverages");
        names.add("Dairy");
        names.add("Soft Drinks");
        GridGroceryAdapter adapter = new GridGroceryAdapter(images, names, this);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Intent intent;
                switch (i){
                    case 0:
                        intent = new Intent(Milk_Product_Cat_Activity.this, MilkSubActivity.class);
                        intent.putExtra("type", "Beverages");
                        break;
                    case 1:
                        intent = new Intent(Milk_Product_Cat_Activity.this, MilkSubActivity.class);
                        intent.putExtra("type", "Dairy");
                        break;

                    case 2:
                        intent = new Intent(Milk_Product_Cat_Activity.this, MilkSubActivity.class);
                        intent.putExtra("type", "SoftDrinks");
                        break;

                    default:
                        intent = new Intent(Milk_Product_Cat_Activity.this, HomeActivity.class);
                        break;
                }
                startActivity(intent);
            }


        });






    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}