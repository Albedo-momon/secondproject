package com.inphynousplay.groceryappeasy.CategoryActivity.SubCatActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.inphynousplay.groceryappeasy.CategoryActivity.Milk_Product_Cat_Activity;
import com.inphynousplay.groceryappeasy.Models.GroceryProducts;
import com.inphynousplay.groceryappeasy.ProductDetail.ProductDetailActivity;
import com.inphynousplay.groceryappeasy.R;
import com.inphynousplay.groceryappeasy.adapters.MilkProductViewHolder;
import com.inphynousplay.groceryappeasy.adapters.PackagedProductViewHolder;
import com.squareup.picasso.Picasso;

public class MilkSubActivity extends AppCompatActivity {
    private RecyclerView recyclerViewMilk;
    RecyclerView.LayoutManager layoutManager;
    private DatabaseReference groceryRef;
    String CategoryName;
    Query query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milk_sub);
        recyclerViewMilk  = findViewById(R.id.recyclerViewmilk);
        recyclerViewMilk.setItemViewCacheSize(20);
        recyclerViewMilk.setDrawingCacheEnabled(true);
        recyclerViewMilk.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        layoutManager = new LinearLayoutManager(this);
        recyclerViewMilk.setLayoutManager(layoutManager);
        CategoryName = getIntent().getExtras().get("type").toString();
        groceryRef = FirebaseDatabase.getInstance().getReference().child("Products");
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (CategoryName.equals("Beverages") && CategoryName != null) {

            query = groceryRef.orderByChild("type").equalTo(CategoryName);
            LoadDataIntoRecycler();

        } else if (CategoryName.equals("Dairy") && CategoryName != null) {
            query = groceryRef.orderByChild("type").equalTo(CategoryName);
            LoadDataIntoRecycler();
        } else if (CategoryName.equals("SoftDrinks") && CategoryName != null) {

            query = groceryRef.orderByChild("type").equalTo(CategoryName);
            LoadDataIntoRecycler();

        }

    }

    private void LoadDataIntoRecycler() {
        FirebaseRecyclerOptions<GroceryProducts> options =
                new FirebaseRecyclerOptions.Builder<GroceryProducts>()
                        .setQuery(query, GroceryProducts.class)
                        .build();


        FirebaseRecyclerAdapter<GroceryProducts, PackagedProductViewHolder> adapter =
                new FirebaseRecyclerAdapter<GroceryProducts, PackagedProductViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull PackagedProductViewHolder holder, int position, @NonNull GroceryProducts model) {
                        if(model.getStatus().equals("true")){
                            holder.vegetableName.setText(model.getPname());
                            holder.vegetableDisc.setText(model.getDisc());
                            holder.vegetableUnit.setText(model.getUnit());
                            holder.vegetableMRP.setText(model.getMrp());
                            holder.vegetableSavings.setText(model.getSavings());
                            holder.vegetablePrice.setText(model.getPrice() + "₹");
                            holder.outOfStock.setVisibility(View.VISIBLE);
                            holder.buyProductNow.setVisibility(View.INVISIBLE);
                            Picasso.get().load(model.getImage()).placeholder(R.drawable.image).into(holder.vegetableView);

                        }else {
                            holder.vegetableName.setText(model.getPname());
                            holder.vegetableDisc.setText(model.getDisc());
                            holder.vegetableUnit.setText(model.getUnit());
                            holder.vegetableMRP.setText(model.getMrp());
                            holder.vegetableSavings.setText(model.getSavings());
                            holder.vegetablePrice.setText(model.getPrice() + "₹");

                            Picasso.get().load(model.getImage()).placeholder(R.drawable.image).into(holder.vegetableView);
                        }
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {


                                if (model.getStatus().equals("true")) {
                                    Intent intent = new Intent(MilkSubActivity.this, ProductDetailActivity.class);
                                    intent.putExtra("pid", model.getPid());
                                    startActivity(intent);

                                } else {
                                    Toast.makeText(MilkSubActivity.this, "Product is out of stock", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

                    }

                    @NonNull
                    @Override
                    public PackagedProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_grocery_products, parent, false);
                        PackagedProductViewHolder holder = new PackagedProductViewHolder(view);
                        return holder;
                    }
                };
        recyclerViewMilk.setAdapter(adapter);
        adapter.startListening();

    }
}