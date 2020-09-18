package com.inphynousplay.groceryappeasy.ProductDetail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.inphynousplay.groceryappeasy.HomeActivity;
import com.inphynousplay.groceryappeasy.Models.FruitProduct;
import com.inphynousplay.groceryappeasy.Models.GroceryProducts;
import com.inphynousplay.groceryappeasy.Prevelent.Prevelent;
import com.inphynousplay.groceryappeasy.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ProductDetailActivity extends AppCompatActivity {
    Button addToCart;
    private ImageView productImage;

    private TextView productPrice, productDescription, productName, finalPriceText, productMRP, productSavings,productUnit;
    private String productID = "", state = "Normal";
    TextView mvalue;
    int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        addToCart = findViewById(R.id.addtocart);
        productImage = findViewById(R.id.productdetailimage);

        productPrice = findViewById(R.id.productpricedetail);
        productDescription = findViewById(R.id.productdiscdetail);
        productName = findViewById(R.id.productnamedetail);
        productMRP = findViewById(R.id.productmrpdetail);
        productSavings = findViewById(R.id.productsavingdetail);
        productUnit = findViewById(R.id.productunitdetail);
        finalPriceText = findViewById(R.id.finalpricetext);
        productID = getIntent().getStringExtra("pid");
        mvalue = findViewById(R.id.value);


        getProductDetails(productID);


        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (state.equals("Order Placed") || state.equals("Order Sipped")) {
                    Toast.makeText(ProductDetailActivity.this, "you can add to cart once your previous order is placed", Toast.LENGTH_SHORT).show();


                } else {
                    addingtocartlist();
                }
            }
        });

        /////////////////////experiment////////////////////////


        /////////////////////////////////////////////////////


    }

    @Override
    protected void onStart() {
        super.onStart();
        checkOrderState();

    }

    private void addingtocartlist() {

        String saveCurrentDate, saveCurrentTime;
        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentDate.format(calForDate.getTime());
        final DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child("Cart List");
        final DatabaseReference cartListRef2 = FirebaseDatabase.getInstance().getReference().child("Customer Order View");
        final HashMap<String, Object> cartMap = new HashMap<>();
        cartMap.put("pid", productID);
        cartMap.put("pname", productName.getText().toString());
        cartMap.put("price", productPrice.getText().toString());
        cartMap.put("date", saveCurrentDate);
        cartMap.put("time", saveCurrentTime);
        cartMap.put("quantity", mvalue.getText().toString());
        cartMap.put("unit",productUnit.getText().toString());
        cartListRef.child("User View").child(Prevelent.currentOnlineUser.getPhone()).child("Products").child(productID)
                .updateChildren(cartMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            cartListRef.child("Admin View").child(Prevelent.currentOnlineUser.getPhone()).child("Products").child(productID)
                                    .updateChildren(cartMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        cartListRef.child("Customer Order View").child(Prevelent.currentOnlineUser.getPhone()).child("Products").child(productID)
                                                .updateChildren(cartMap)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            Toast.makeText(ProductDetailActivity.this, "Added To Cart List", Toast.LENGTH_SHORT).show();
                                                            Intent intent = new Intent(ProductDetailActivity.this, HomeActivity.class);
                                                            startActivity(intent);
                                                        }

                                                    }
                                                });
                                    }
                                }
                            });
                        }
                    }
                });
    }

    private void getProductDetails(String productID) {
        DatabaseReference productsRef = FirebaseDatabase.getInstance().getReference().child("Products");
        productsRef.child(productID).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    GroceryProducts products = snapshot.getValue(GroceryProducts.class);
                    productName.setText(products.getPname());
                    productPrice.setText(products.getPrice());
                    productMRP.setText(products.getMrp());
                    productSavings.setText(products.getSavings());
                    productDescription.setText(products.getDisc());
                    productUnit.setText(products.getUnit());
                    Picasso.get().load(products.getImage()).placeholder(R.drawable.image).into(productImage);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    /////////////////////////////////////////button/////////////

    public void increment(View v) {
        if (count >= 5) {
            if (count == 5) {
                Toast.makeText(this, "You have reached maximum limit", Toast.LENGTH_SHORT).show();
            }
            ;
        } else count++;

        mvalue.setText("" + count);
        float price = Float.parseFloat(mvalue.getText().toString());
        float priceproduct = Float.parseFloat(productPrice.getText().toString());
        float x = price * priceproduct;
        float y = (float) Math.round(x * 100.0) / 100;
        finalPriceText.setText("" + y);

    }

    public void decrement(View v) {
        if (count <= 1) count = 1;
        else count--;
        mvalue.setText("" + count);
        float price = Float.parseFloat(mvalue.getText().toString());
        float priceproduct = Float.parseFloat(productPrice.getText().toString());
        float x = price * priceproduct;
        float y = (float) Math.round(x * 100.0) / 100;
        finalPriceText.setText("" + y);
    }
    /////////////////////////////////////////button/////////////

    private void checkOrderState() {
        DatabaseReference orderRef;
        orderRef = FirebaseDatabase.getInstance().getReference().child("Orders").child(Prevelent.currentOnlineUser.getPhone());
        orderRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String shipingState = snapshot.child("state").getValue().toString();


                    if (shipingState.equals("shipped")) {
                        state = "Order Shipped";

                    } else if (shipingState.equals("not shipped")) {
                        state = "Order Placed";

                    }
                    ;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}