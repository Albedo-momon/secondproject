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
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.inphynousplay.groceryappeasy.CategoryActivity.SubCatActivity.FruitAndVegetableSubActivity;
import com.inphynousplay.groceryappeasy.CategoryActivity.SubCatActivity.GrocerySubActivity;
import com.inphynousplay.groceryappeasy.CategoryActivity.SubCatActivity.HomeAndKitchenSubActivity;
import com.inphynousplay.groceryappeasy.HomeActivity;
import com.inphynousplay.groceryappeasy.Models.FruitProduct;
import com.inphynousplay.groceryappeasy.Models.GroceryProducts;
import com.inphynousplay.groceryappeasy.ProductDetail.ProductDetailActivity;
import com.inphynousplay.groceryappeasy.R;
import com.inphynousplay.groceryappeasy.adapters.FruitProductViewHolder;
import com.inphynousplay.groceryappeasy.adapters.GridGroceryAdapter;
import com.inphynousplay.groceryappeasy.adapters.GroceryProductViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Grocery_Cat_Activity extends AppCompatActivity {



    TextView textView;
    GridView gridView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery__cat_);

        gridView = findViewById(R.id.gridview);



        List<Integer> images = new ArrayList<>();
        List<String> names = new ArrayList<>();

        images.add(R.drawable.dal);
        images.add(R.drawable.pulses);
        images.add(R.drawable.dryfruits);
        images.add(R.drawable.oil);
        images.add(R.drawable.flours);
        images.add(R.drawable.rice);
        images.add(R.drawable.masale);
        images.add(R.drawable.saltsugar);
        names.add("Dal's");
        names.add("Pulses");
        names.add("Dry Fruits");
        names.add("Cooking Oil");
        names.add("Flours");
        names.add("Rice");
        names.add("Masala");
        names.add("Salt/Sugar/Jaggery");


        GridGroceryAdapter adapter = new GridGroceryAdapter(images, names, this);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Intent intent;
                switch (i) {
                    case 0:
                        intent = new Intent(Grocery_Cat_Activity.this, GrocerySubActivity.class);
                        intent.putExtra("type","Dal");
                        break;
                    case 1:
                        intent = new Intent(Grocery_Cat_Activity.this, GrocerySubActivity.class);
                        intent.putExtra("type","Pulses");
                        break;

                    case 2:
                        intent = new Intent(Grocery_Cat_Activity.this, GrocerySubActivity.class);
                        intent.putExtra("type","DryFruits");
                        break;
                    case 3:
                        intent = new Intent(Grocery_Cat_Activity.this, GrocerySubActivity.class);
                        intent.putExtra("type","CookingOil");
                        break;
                    case 4:
                        intent = new Intent(Grocery_Cat_Activity.this, GrocerySubActivity.class);
                        intent.putExtra("type","Flours");
                        break;
                    case 5:
                        intent = new Intent(Grocery_Cat_Activity.this, GrocerySubActivity.class);
                        intent.putExtra("type","Rice");
                        break;
                    case 6:
                        intent = new Intent(Grocery_Cat_Activity.this, GrocerySubActivity.class);
                        intent.putExtra("type","Masale");
                        break;
                    case 7:
                        intent = new Intent(Grocery_Cat_Activity.this, GrocerySubActivity.class);
                        intent.putExtra("type","SaltSugarJaggery");
                        break;

                    default:
                        intent = new Intent(Grocery_Cat_Activity.this, HomeActivity.class);
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