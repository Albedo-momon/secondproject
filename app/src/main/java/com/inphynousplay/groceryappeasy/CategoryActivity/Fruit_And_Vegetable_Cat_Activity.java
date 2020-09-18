package com.inphynousplay.groceryappeasy.CategoryActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.inphynousplay.groceryappeasy.CategoryActivity.SubCatActivity.FruitAndVegetableSubActivity;
import com.inphynousplay.groceryappeasy.Models.FruitProduct;
import com.inphynousplay.groceryappeasy.ProductDetail.ProductDetailActivity;
import com.inphynousplay.groceryappeasy.R;
import com.inphynousplay.groceryappeasy.adapters.FruitProductViewHolder;
import com.inphynousplay.groceryappeasy.adapters.GridGroceryAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Fruit_And_Vegetable_Cat_Activity extends AppCompatActivity {


    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit__cat_);
        gridView = findViewById(R.id.gridview);

        List<Integer> images = new ArrayList<>();
        List<String> names = new ArrayList<>();

        images.add(R.drawable.fruit);
        images.add(R.drawable.vege);

        names.add("Fresh Fruits");
        names.add("Vegetables");



        GridGroceryAdapter adapter = new GridGroceryAdapter(images, names, this);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Intent intent;
                switch (i){
                    case 0:
                        intent = new Intent(Fruit_And_Vegetable_Cat_Activity.this, FruitAndVegetableSubActivity.class);
                        intent.putExtra("type","Fruits");
                        break;
                    case 1:
                        intent = new Intent(Fruit_And_Vegetable_Cat_Activity.this, FruitAndVegetableSubActivity.class);
                        intent.putExtra("type","Vegetables");
                        break;
                    default:
                        intent = new Intent(Fruit_And_Vegetable_Cat_Activity.this, Fruit_And_Vegetable_Cat_Activity.class);
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