package com.inphynousplay.groceryappeasy.Cart;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.inphynousplay.groceryappeasy.AboutUsActivity;
import com.inphynousplay.groceryappeasy.ConfirmFinalOrder;
import com.inphynousplay.groceryappeasy.HomeActivity;
import com.inphynousplay.groceryappeasy.MainActivity;
import com.inphynousplay.groceryappeasy.Models.Cart;
import com.inphynousplay.groceryappeasy.OrderActivity;
import com.inphynousplay.groceryappeasy.Prevelent.Prevelent;
import com.inphynousplay.groceryappeasy.ProductDetail.ProductDetailActivity;
import com.inphynousplay.groceryappeasy.R;
import com.inphynousplay.groceryappeasy.adapters.CartViewHolder;

public class CartActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private  RecyclerView.LayoutManager layoutManager;
    private TextView cardViewbutton;
    private TextView txttotalamount,txtmessage;
    private float overAllTotalPrice = 0;
    private BottomNavigationView bottomNavigation;
    private ImageView backToHome;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        txttotalamount = findViewById(R.id.totalamount);
        backToHome = findViewById(R.id.backtohome);
        recyclerView = findViewById(R.id.recyclerViewCart);
        txtmessage = findViewById(R.id.finalordermessage);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        cardViewbutton = findViewById(R.id.cardView2);
        bottomNavigation = findViewById(R.id.bottomnevigationcart);





        backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this, HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                finish();
            }
        });









        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                switch (item.getItemId()) {

                    case R.id.home:
                        startActivity(new Intent(CartActivity.this, HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                        finish();
                        break;

                    case R.id.order:
                        startActivity(new Intent(CartActivity.this, OrderActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                        finish();
                        break;


                }
                return false;
            }
        });














        cardViewbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txttotalamount.setText("Total Amount = " +String.valueOf(overAllTotalPrice));
                Intent intent = new Intent(CartActivity.this, ConfirmFinalOrder.class);
                intent.putExtra("Total Price",String.valueOf(overAllTotalPrice));
                startActivity(intent);
                finish();
            }
        });





    }

    @Override
    protected void onStart() {
        super.onStart();
        checkOrderState();


        final DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child("Cart List").child("User View").child(Prevelent.currentOnlineUser.getPhone());

        cartListRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild("Products")) {
                    final DatabaseReference cartListRef2 = FirebaseDatabase.getInstance().getReference().child("Cart List");

                    FirebaseRecyclerOptions<Cart> options =
                            new FirebaseRecyclerOptions.Builder<Cart>()
                                    .setQuery(cartListRef2.child("User View")
                                            .child(Prevelent.currentOnlineUser.getPhone()).child("Products"), Cart.class).build();
                    FirebaseRecyclerAdapter<Cart, CartViewHolder> adapter =
                            new FirebaseRecyclerAdapter<Cart, CartViewHolder>(options) {
                                @Override
                                protected void onBindViewHolder(@NonNull CartViewHolder holder, int position, @NonNull Cart model) {
                                    holder.txtProductName.setText(model.getPname());
                                    holder.txtProductPrice.setText(model.getPrice());
                                    holder.txtProductQuantity.setText(model.getQuantity());
                                    holder.txtProductUnit.setText(model.getUnit());

                                    float oneTypeProductTPrice = ((Float.valueOf(model.getPrice())) * (Float.valueOf(model.getQuantity())));
                                     overAllTotalPrice = overAllTotalPrice + oneTypeProductTPrice;
                                    float y = (float) Math.round(overAllTotalPrice * 100.0) / 100;
                                    txttotalamount.setText("Total Amount = " + String.valueOf(y));
                                    cardViewbutton.setVisibility(View.VISIBLE);
                                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            CharSequence options[] = new CharSequence[]{
                                                    "Edit",
                                                    "Remove"
                                            };
                                            AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
                                            builder.setTitle("Cart Options");
                                            builder.setItems(options, new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    if (i == 0) {
                                                        Intent intent = new Intent(CartActivity.this, ProductDetailActivity.class);
                                                        intent.putExtra("pid", model.getPid());
                                                        startActivity(intent);
                                                    }
                                                    if (i == 1) {
                                                        cartListRef2.child("User View")
                                                                .child(Prevelent.currentOnlineUser.getPhone())
                                                                .child("Products")
                                                                .child(model.getPid())
                                                                .removeValue()
                                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                        cartListRef2.child("Admin View")
                                                                                .child(Prevelent.currentOnlineUser.getPhone())
                                                                                .child("Products")
                                                                                .child(model.getPid())
                                                                                .removeValue()
                                                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                    @Override
                                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                                        cartListRef.child("Customer Order View")
                                                                                                .child(Prevelent.currentOnlineUser.getPhone())
                                                                                                .child("Products")
                                                                                                .child(model.getPid())
                                                                                                .removeValue()
                                                                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                    @Override
                                                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                                                        if (task.isSuccessful()) {
                                                                                                            Toast.makeText(CartActivity.this, "Item Removed Successfully", Toast.LENGTH_SHORT).show();
                                                                                                            Intent intent = new Intent(CartActivity.this, HomeActivity.class);
                                                                                                            startActivity(intent);
                                                                                                        }
                                                                                                    }
                                                                                                });
                                                                                    }
                                                                                });
                                                                    }
                                                                });
                                                    }
                                                }
                                            });
                                            builder.show();
                                        }

                                    });


                                }

                                @NonNull
                                @Override
                                public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cart, parent, false);
                                    CartViewHolder holder = new CartViewHolder(view);
                                    return holder;
                                }
                            };
                    recyclerView.setAdapter(adapter);
                    adapter.startListening();


                } else {
                    Toast.makeText(CartActivity.this, "You dont have product in cart", Toast.LENGTH_SHORT).show();
                    cardViewbutton.setVisibility(View.INVISIBLE);



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
        private void checkOrderState() {
            DatabaseReference orderRef;
            orderRef = FirebaseDatabase.getInstance().getReference().child("Orders").child(Prevelent.currentOnlineUser.getPhone());
            orderRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        String shipingState = snapshot.child("state").getValue().toString();
                        String userName = snapshot.child("name").getValue().toString();

                        if (shipingState.equals("shipped")) {
                            txttotalamount.setText("Dear" + userName + "\n order is shipped successfully.");
                            recyclerView.setVisibility(View.GONE);
                            txtmessage.setVisibility(View.VISIBLE);
                            txtmessage.setText("Congratulations, your final order has been shipped successfully. Soon you will receive your order at door step");
                            cardViewbutton.setVisibility(View.GONE);
                        } else if (shipingState.equals("not shipped")) {
                            txttotalamount.setText("Shipping State = Not Shipped");
                            recyclerView.setVisibility(View.GONE);
                            txtmessage.setVisibility(View.VISIBLE);
                            cardViewbutton.setVisibility(View.GONE);
                            Toast.makeText(CartActivity.this, "You can purchase more product, once you recieved your first final order", Toast.LENGTH_SHORT).show();
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
