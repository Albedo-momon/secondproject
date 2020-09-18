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
import com.inphynousplay.groceryappeasy.CategoryActivity.SubCatActivity.GrocerySubActivity;
import com.inphynousplay.groceryappeasy.CategoryActivity.SubCatActivity.PackagedSubActivity;
import com.inphynousplay.groceryappeasy.HomeActivity;
import com.inphynousplay.groceryappeasy.Models.GroceryProducts;
import com.inphynousplay.groceryappeasy.ProductDetail.ProductDetailActivity;
import com.inphynousplay.groceryappeasy.R;
import com.inphynousplay.groceryappeasy.adapters.GridGroceryAdapter;
import com.inphynousplay.groceryappeasy.adapters.PackagedProductViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Packaged_Food extends AppCompatActivity {

    GridView gridView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packaged_food);
        gridView = findViewById(R.id.gridview);



        List<Integer> images = new ArrayList<>();
        List<String> names = new ArrayList<>();

        images.add(R.drawable.buscuits);
        images.add(R.drawable.snacksandfarsan);
        images.add(R.drawable.breakfast);
        images.add(R.drawable.chocolates);
        images.add(R.drawable.readytocook);
        images.add(R.drawable.ketchup);
        images.add(R.drawable.honeyandjam);
        images.add(R.drawable.pastaandnoodles);
        images.add(R.drawable.soups);
        images.add(R.drawable.pickles);
        names.add("Buscuits & Cookies");
        names.add("Snacks & Farsan");
        names.add("Breakfast Cereals");
        names.add("Chocolates & Sweets");
        names.add("Ready to Cook");
        names.add("Ketchup & Sauce");
        names.add("Honey & Jam");
        names.add("Pasta & Noodle");
        names.add("Soups");
        names.add("Pickles");

        GridGroceryAdapter adapter = new GridGroceryAdapter(images, names, this);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Intent intent;
                switch (i) {
                    case 0:
                        intent = new Intent(Packaged_Food.this, PackagedSubActivity.class);
                        intent.putExtra("type","BuscuitsAndCookies");
                        break;
                    case 1:
                        intent = new Intent(Packaged_Food.this, PackagedSubActivity.class);
                        intent.putExtra("type","SnacksAndFarsan");
                        break;

                    case 2:
                        intent = new Intent(Packaged_Food.this, PackagedSubActivity.class);
                        intent.putExtra("type","BreakfastCereals");
                        break;
                    case 3:
                        intent = new Intent(Packaged_Food.this, PackagedSubActivity.class);
                        intent.putExtra("type","ChocolatesAndSweets");
                        break;
                    case 4:
                        intent = new Intent(Packaged_Food.this, PackagedSubActivity.class);
                        intent.putExtra("type","ReadyToCook");
                        break;
                    case 5:
                        intent = new Intent(Packaged_Food.this, PackagedSubActivity.class);
                        intent.putExtra("type","KetchupAndSauce");
                        break;
                    case 6:
                        intent = new Intent(Packaged_Food.this, PackagedSubActivity.class);
                        intent.putExtra("type","HoneyAndJam");
                        break;
                    case 7:
                        intent = new Intent(Packaged_Food.this, PackagedSubActivity.class);
                        intent.putExtra("type","PastaAndNoodles");
                        break;
                    case 8:
                        intent = new Intent(Packaged_Food.this, PackagedSubActivity.class);
                        intent.putExtra("type","Soups");
                        break;
                    case 9:
                        intent = new Intent(Packaged_Food.this, PackagedSubActivity.class);
                        intent.putExtra("type","Pickles");
                        break;

                    default:
                        intent = new Intent(Packaged_Food.this, HomeActivity.class);
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
