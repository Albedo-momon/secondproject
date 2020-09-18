package com.inphynousplay.groceryappeasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.inphynousplay.groceryappeasy.Cart.CartActivity;
import com.inphynousplay.groceryappeasy.Models.YourOrders;

public class OrderActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    private RecyclerView recyclerViewYourOrder;
    private DatabaseReference newOrderRef, userOrderRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        bottomNavigation = findViewById(R.id.bottomnevigation);
        recyclerViewYourOrder=findViewById(R.id.recyclerViewOrder);
        recyclerViewYourOrder.setLayoutManager(new LinearLayoutManager(this));
        newOrderRef = FirebaseDatabase.getInstance().getReference().child("Customer Order View");
        userOrderRef = FirebaseDatabase.getInstance().getReference().child("Cart List").child("Customer Order View");




        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                switch (item.getItemId()) {

                    case R.id.home:
                        startActivity(new Intent(OrderActivity.this, HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                        finish();
                        break;

                    case R.id.zcart:
                        startActivity(new Intent(OrderActivity.this, CartActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                        finish();
                        break;


                }
                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<YourOrders> options =
                new FirebaseRecyclerOptions.Builder<YourOrders>()
                        .setQuery(newOrderRef, YourOrders.class)
                        .build();

        FirebaseRecyclerAdapter<YourOrders, AdminOrdersViewHolder> adapter =
                new FirebaseRecyclerAdapter<YourOrders, AdminOrdersViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull AdminOrdersViewHolder holder, final int position, @NonNull final YourOrders model) {
                        holder.userName.setText( model.getName());
                        holder.userPhoneNumber.setText( model.getPhone());
                        holder.userDateTime.setText(model.getDate() + " " + model.getTime());
                        holder.userDieliveryTime.setText( model.getDelievery_time());
                        holder.userTotalPrice.setText(model.getTotalAmount());
                        holder.userAddress.setText( model.getAddress());

                        holder.buttonShowProduct.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String uID = getRef(position).getKey();

                               Intent intent = new Intent(OrderActivity.this, CustomerProduct.class);
                               intent.putExtra("uid", uID);
                              startActivity(intent);
                            }
                        });

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                CharSequence options[] = new CharSequence[]{

                                        "Yes",
                                        "No"
                                };
                                AlertDialog.Builder builder = new AlertDialog.Builder(OrderActivity.this);
                                builder.setTitle("Delete order history");
                                builder.setItems(options, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        if (i == 0) {

                                            String uID = getRef(position).getKey();
                                            RemoveOrder(uID);

                                        } else {
                                            finish();
                                        }
                                    }
                                });
                                builder.show();


                            }
                        });


                    }

                    @NonNull
                    @Override
                    public AdminOrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_order, parent, false);
                        return new AdminOrdersViewHolder(view);
                    }
                };


        recyclerViewYourOrder.setAdapter(adapter);
        adapter.startListening();


    }


    public static class AdminOrdersViewHolder extends RecyclerView.ViewHolder {

        public TextView userName, userPhoneNumber, userTotalPrice, userDateTime, userAddress, userDieliveryTime;
        public Button buttonShowProduct;

        public AdminOrdersViewHolder(@NonNull View itemView) {
            super(itemView);

            userName = itemView.findViewById(R.id.username);
            userPhoneNumber = itemView.findViewById(R.id.usermobilenumber);
            userTotalPrice = itemView.findViewById(R.id.producttotalprice);
            userDateTime = itemView.findViewById(R.id.dateandtime);
            userAddress = itemView.findViewById(R.id.useraddress);
            userDieliveryTime = itemView.findViewById(R.id.delieverytime);
            buttonShowProduct = itemView.findViewById(R.id.show_all_products);

        }
    }

    private void RemoveOrder(String uID) {
        newOrderRef.child(uID).removeValue();
        userOrderRef.child(uID).removeValue();

    }

    }
