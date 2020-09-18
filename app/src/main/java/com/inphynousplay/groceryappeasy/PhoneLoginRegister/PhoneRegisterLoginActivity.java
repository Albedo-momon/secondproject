package com.inphynousplay.groceryappeasy.PhoneLoginRegister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.inphynousplay.groceryappeasy.MainActivity;
import com.inphynousplay.groceryappeasy.R;

import java.util.HashMap;

public class PhoneRegisterLoginActivity extends AppCompatActivity {

    Button btnRegister;
    EditText enterName,enterLastname,enterPhoneNumber,EnterPassword;
    private ProgressDialog loadingBar;
    int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_register_login);

        btnRegister=findViewById(R.id.buttonregister);
        enterName=findViewById(R.id.firstname);
        enterLastname=findViewById(R.id.lastname);
        enterPhoneNumber=findViewById(R.id.phonenumber);

        EnterPassword=findViewById(R.id.password);
        loadingBar = new ProgressDialog(this);

        //////////////status bar hide start/////////////
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        //////////////status bar hide end//////////////////////

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creatAccount();
            }
        });

    }

    private void creatAccount() {
        String name_first= enterName.getText().toString();
        String name_last= enterLastname.getText().toString();
        String phone= enterPhoneNumber.getText().toString();
        String password= EnterPassword.getText().toString();

        if(TextUtils.isEmpty(name_first) || TextUtils.isEmpty(name_last) || TextUtils.isEmpty(phone)||TextUtils.isEmpty(password) || phone.length()<10){
            Toast.makeText(this, "Please fill all the credential first", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Creat Account");
            loadingBar.setMessage("Wait registration is in process");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            validate(name_first,name_last,phone,password);
        }
    }
    ///////////////////validating phone number and password////////////////////////////
    private void validate(String name_first, String name_last, String phone, String password) {

        final DatabaseReference rootRef;
        rootRef = FirebaseDatabase.getInstance().getReference();
        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!(snapshot.child("Users").child(phone).exists())){
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("phone",phone);
                    userdataMap.put("password",password);
                    userdataMap.put("firstname",name_first);
                    userdataMap.put("lastname",name_last);
                    rootRef.child("Users").child(phone).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(PhoneRegisterLoginActivity.this, "Hurray!! you have successfully registered", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();
                                        Intent intent = new Intent(PhoneRegisterLoginActivity.this,PhoneLoginActivity.class);
                                        startActivity(intent);
                                    }else {
                                        loadingBar.dismiss();
                                        Toast.makeText(PhoneRegisterLoginActivity.this, "Network error: Please try again after some time.....", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }else {
                    Toast.makeText(PhoneRegisterLoginActivity.this, "This" + phone +"already exists", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



    ///////////////////validating phone number and password////////////////////////////


    public void gotologin2(View view) {
        Intent intent = new Intent(this,PhoneLoginActivity.class);
        startActivity(intent);
        finish();
        Animatoo.animateSlideLeft(this);
    }

    public void gotomainactivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        Animatoo.animateSwipeRight(this);
        finish();
    }



    }

