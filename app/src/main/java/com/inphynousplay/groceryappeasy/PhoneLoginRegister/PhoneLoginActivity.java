package com.inphynousplay.groceryappeasy.PhoneLoginRegister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.inphynousplay.groceryappeasy.HomeActivity;
import com.inphynousplay.groceryappeasy.MainActivity;
import com.inphynousplay.groceryappeasy.Models.Users;
import com.inphynousplay.groceryappeasy.Prevelent.Prevelent;
import com.inphynousplay.groceryappeasy.R;
import com.rey.material.widget.CheckBox;

import io.paperdb.Paper;

public class PhoneLoginActivity extends AppCompatActivity {

    EditText enterno,enterpass;
    Button loginintoaccount;
    private ProgressDialog loadingBar;
    private String DBname = "Users";
    private TextView forgotPassword;
    private CheckBox chkbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_login);


        loginintoaccount=findViewById(R.id.login);
        enterno=findViewById(R.id.phoneenter);

        enterpass=findViewById(R.id.passenter);
        //forgotPassword = findViewById(R.id.forgot_password);
        chkbox = findViewById(R.id.remember_me_checkbox);
        loadingBar = new ProgressDialog(this);
        Paper.init(this);

        //////////////status bar hide start/////////////
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        //////////////status bar hide end//////////////////////


        loginintoaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });



    }

    private void loginUser() {
        String phone= enterno.getText().toString();
        String password= enterpass.getText().toString();

        if( TextUtils.isEmpty(phone)||TextUtils.isEmpty(password) || phone.length()<9){
            Toast.makeText(this, "Please fill all the correct credential first", Toast.LENGTH_SHORT).show();
        }else{
            loadingBar.setTitle("login Account");
            loadingBar.setMessage("Wait login is in process");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            allowAccessToUser(phone,password);
        }
    }

    private void allowAccessToUser(final String phone, final String password) {
        if(chkbox.isChecked()){
            Paper.book().write(Prevelent.userPhoneNumber,phone);
            Paper.book().write(Prevelent.userPhonePassword,password);
        }

        final DatabaseReference rootRef;
        rootRef = FirebaseDatabase.getInstance().getReference();
        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(DBname).child(phone).exists()){
                    Users userData = snapshot.child(DBname).child(phone).getValue(Users.class);
                    if(userData.getPhone().equals(phone)){
                        if(userData.getPassword().equals(password)){
                            Toast.makeText(PhoneLoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                            Intent intent = new Intent(PhoneLoginActivity.this, HomeActivity.class);
                            Prevelent.currentOnlineUser = userData;
                            startActivity(intent);
                            finish();

                        }
                    }

                }else {
                    Toast.makeText(PhoneLoginActivity.this, "User with this" + phone + "does not exists", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(PhoneLoginActivity.this, "Please create new account", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    public void gotoregister(View view) {
        Intent intent = new Intent(this,PhoneRegisterLoginActivity.class);
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