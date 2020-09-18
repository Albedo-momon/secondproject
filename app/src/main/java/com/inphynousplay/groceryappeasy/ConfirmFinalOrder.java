package com.inphynousplay.groceryappeasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.inphynousplay.groceryappeasy.Models.Users;
import com.inphynousplay.groceryappeasy.Models.YourOrders;
import com.inphynousplay.groceryappeasy.PhoneLoginRegister.PhoneLoginActivity;
import com.inphynousplay.groceryappeasy.Prevelent.Prevelent;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ConfirmFinalOrder extends AppCompatActivity {

    Button confirmShipment;
    EditText enterName, enterMobilenumber, enterAddress, enterpin;
    Spinner mSpinner;
    private String DBname = "Pin";
    private String totalAmount = "";
    String item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_final_order);
        enterName = findViewById(R.id.customername);
        enterMobilenumber = findViewById(R.id.mobileno);


        enterAddress = findViewById(R.id.customerAddress);
        enterpin = findViewById(R.id.pincode);
        confirmShipment = findViewById(R.id.confirmshipment);
        mSpinner = findViewById(R.id.spinnerchoice);
        totalAmount = getIntent().getStringExtra("Total Price");


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Spinner_items,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);

        cliconspinner();

        confirmShipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check();
            }
        });
    }


    private void cliconspinner() {
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i).equals("Select Delievery Preferences")) {

                } else {
                    item = adapterView.getItemAtPosition(i).toString();
                    Toast.makeText(ConfirmFinalOrder.this, "Selected" + item, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });
    }


    private void check() {
        String ed1 = enterMobilenumber.getText().toString();
        String pin = enterpin.getText().toString();

        if (TextUtils.isEmpty(enterName.getText().toString())) {
            Toast.makeText(this, "Please provide full name", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(enterMobilenumber.getText().toString()) || ed1.length() < 10) {
            Toast.makeText(this, "Please provide Mobile  number", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(enterAddress.getText().toString())) {
            Toast.makeText(this, "Please provide your address", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(enterpin.getText().toString()) || pin.length() < 6) {
            Toast.makeText(this, "Please provide your Pin code", Toast.LENGTH_SHORT).show();
        } else{
            checkPinCode(pin);
        }

    }


    private void checkPinCode(final String pin) {
        final DatabaseReference rootRef;
        rootRef = FirebaseDatabase.getInstance().getReference();
        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(DBname).child(pin).exists()) {
                    YourOrders userData = snapshot.child(DBname).child(pin).getValue(YourOrders.class);
                    if (userData.getPincode().equals(pin)) {
                        Toast.makeText(ConfirmFinalOrder.this, "your order will be shipped soon", Toast.LENGTH_SHORT).show();
                        confrimorder();
                    }

                } else {
                    Toast.makeText(ConfirmFinalOrder.this, "We are not availbale in this area", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ConfirmFinalOrder.this, "error", Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void confrimorder() {

        String saveCurrentDate, saveCurrentTime;
        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final DatabaseReference orderRef = FirebaseDatabase.getInstance().getReference()
                .child("Orders")
                .child(Prevelent.currentOnlineUser.getPhone());
        HashMap<String, Object> orderMap = new HashMap<>();
        orderMap.put("totalAmount", totalAmount);
        orderMap.put("name", enterName.getText().toString());
        orderMap.put("phone", enterMobilenumber.getText().toString());
        orderMap.put("address", enterAddress.getText().toString());
        orderMap.put("Pin", enterpin.getText().toString());
        orderMap.put("date", saveCurrentDate);
        orderMap.put("time", saveCurrentTime);
        orderMap.put("delievery_time", item);
        orderMap.put("state", "not shipped");
        orderRef.updateChildren(orderMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                final DatabaseReference orderRef = FirebaseDatabase.getInstance().getReference()
                        .child("Customer Order View")
                        .child(Prevelent.currentOnlineUser.getPhone());
                HashMap<String, Object> orderMap = new HashMap<>();
                orderMap.put("totalAmount", totalAmount);
                orderMap.put("name", enterName.getText().toString());
                orderMap.put("phone", enterMobilenumber.getText().toString());
                orderMap.put("address", enterAddress.getText().toString());
                orderMap.put("Pin", enterpin.getText().toString());
                orderMap.put("date", saveCurrentDate);
                orderMap.put("time", saveCurrentTime);
                orderMap.put("delievery_time", item);
                orderMap.put("state", "not shipped");
                orderRef.updateChildren(orderMap)

                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                FirebaseDatabase.getInstance().getReference().child("Cart List")
                                        .child("User View")
                                        .child(Prevelent.currentOnlineUser.getPhone())
                                        .removeValue()
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(ConfirmFinalOrder.this, "your final order is placed successfully", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(ConfirmFinalOrder.this, HomeActivity.class);
                                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                    startActivity(intent);
                                                    finish();
                                                }
                                            }
                                        });
                            }
                        });
            }
        });


    }


}
