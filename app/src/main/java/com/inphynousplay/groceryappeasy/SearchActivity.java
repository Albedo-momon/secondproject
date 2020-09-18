package com.inphynousplay.groceryappeasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.inphynousplay.groceryappeasy.Models.GroceryProducts;
import com.inphynousplay.groceryappeasy.ProductDetail.ProductDetailActivity;
import com.inphynousplay.groceryappeasy.adapters.GroceryProductViewHolder;
import com.inphynousplay.groceryappeasy.adapters.PackagedProductViewHolder;
import com.inphynousplay.groceryappeasy.adapters.SearchViewHolder;
import com.squareup.picasso.Picasso;

public class SearchActivity extends AppCompatActivity {

    RecyclerView recyclerSearch;
    FirebaseRecyclerOptions<GroceryProducts> options;
    FirebaseRecyclerAdapter<GroceryProducts, PackagedProductViewHolder> adapter;
    DatabaseReference dataRef;
    EditText searchView;
    Button button;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        recyclerSearch=findViewById(R.id.recyclersearch);
        searchView = findViewById(R.id.searchviewmain);

        button = findViewById(R.id.searchbutton);
        dataRef = FirebaseDatabase.getInstance().getReference().child("Products");
        recyclerSearch.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerSearch.setHasFixedSize(true);


      button.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              String data = searchView.getText().toString();
              if(TextUtils.isEmpty(data)){
                  recyclerSearch.setVisibility(View.GONE);
                  Toast.makeText(SearchActivity.this, "Enter Product", Toast.LENGTH_SHORT).show();
              }else {
                  LoadData(data);
                  recyclerSearch.setVisibility(View.VISIBLE);
              }

          }
      });



    }
    private void LoadData(String data) {
        Query query = dataRef.orderByChild("pname").startAt(data).endAt(data+"\uf8ff");


        options = new FirebaseRecyclerOptions.Builder<GroceryProducts>().setQuery(query,GroceryProducts.class).build();
        adapter=new FirebaseRecyclerAdapter<GroceryProducts, PackagedProductViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull PackagedProductViewHolder holder, final int position, @NonNull GroceryProducts model) {
                holder.vegetableName.setText(model.getPname());
                holder.vegetableDisc.setText(model.getDisc());
                holder.vegetableUnit.setText(model.getUnit());
                holder.vegetableMRP.setText(model.getMrp());
                holder.vegetableSavings.setText(model.getSavings());
                holder.vegetablePrice.setText(model.getPrice() + "₹");
                Picasso.get().load(model.getImage()).fit().placeholder(R.drawable.image).into(holder.vegetableView);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(SearchActivity.this, ProductDetailActivity.class);
                        intent.putExtra("pid",getRef(position).getKey());
                        startActivity(intent);
                        finish();
                    }
                });
            }

            @NonNull
            @Override
            public PackagedProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_grocery_products,parent,false);

                return new PackagedProductViewHolder(v);
            }
        };
        adapter.startListening();
        recyclerSearch.setAdapter(adapter);
    }
}