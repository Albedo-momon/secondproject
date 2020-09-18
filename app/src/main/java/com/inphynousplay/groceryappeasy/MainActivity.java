package com.inphynousplay.groceryappeasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.inphynousplay.groceryappeasy.Models.PlateModel;
import com.inphynousplay.groceryappeasy.Models.Users;
import com.inphynousplay.groceryappeasy.PhoneLoginRegister.PhoneLoginActivity;
import com.inphynousplay.groceryappeasy.Prevelent.Prevelent;
import com.inphynousplay.groceryappeasy.adapters.PlateAdapters;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<PlateModel> plateModelList;
    private PlateAdapters plateAdapters;
    private LinearLayout emailContinue, phoneContinue;
    private LinearLayout emailContinueBtn;
    private ProgressDialog loadingBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /////////////App update checker start///////////
       /* try{
            AppUpdateChecker appUpdateChecker = new AppUpdateChecker(this);
            appUpdateChecker.checkForUpdate(false);
        } catch (Exception e){
            e.printStackTrace();
        }*/
        /////////App update checker end here////////////

        //////////////status bar hide start/////////////
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        //////////////status bar hide end//////////////////////

        phoneContinue=findViewById(R.id.linear1);

        recyclerView = findViewById(R.id.recyclerView);
        Paper.init(MainActivity.this);
        loadingBar= new ProgressDialog(MainActivity.this);

        String userPhoneKey = Paper.book().read(Prevelent.userPhoneNumber);
        String userPasswordKey = Paper.book().read(Prevelent.userPhonePassword);

        if(userPhoneKey != null && userPasswordKey != null){
            if(!TextUtils.isEmpty(userPhoneKey) && !TextUtils.isEmpty(userPasswordKey)){
                AllowAccess(userPhoneKey,userPasswordKey);
                loadingBar.setTitle("Already Logged In");
                loadingBar.setMessage("Please wait.....While we check internet connection");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();


            }
        }



        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        plateModelList = new ArrayList<>();
        plateModelList.add(new PlateModel(R.drawable.groceryimage));
        plateModelList.add(new PlateModel(R.drawable.grocery2));
        plateModelList.add(new PlateModel(R.drawable.grocery3));
        plateModelList.add(new PlateModel(R.drawable.groceryimage));
        plateModelList.add(new PlateModel(R.drawable.grocery2));
        plateModelList.add(new PlateModel(R.drawable.grocery2));
        plateModelList.add(new PlateModel(R.drawable.grocery3));
        plateModelList.add(new PlateModel(R.drawable.grocery2));
        plateModelList.add(new PlateModel(R.drawable.groceryimage));

        plateAdapters = new PlateAdapters(plateModelList, this);
        recyclerView.setAdapter(plateAdapters);
        plateAdapters.notifyDataSetChanged();

        /////////call autoscroll///////////
        autoScroll();

        //////////////////autoScroll end/////////

        phoneContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PhoneLoginActivity.class);
                startActivity(intent);
                Animatoo.animateSlideDown(MainActivity.this);
            }
        });

    }

    private void AllowAccess(final String phone,final String password) {
        final DatabaseReference rootRef;
        rootRef = FirebaseDatabase.getInstance().getReference();
        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child("Users").child(phone).exists()){
                    Users userData = snapshot.child("Users").child(phone).getValue(Users.class);
                    if(userData.getPhone().equals(phone)){
                        if(userData.getPassword().equals(password)){
                            Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            Prevelent.currentOnlineUser = userData;
                            startActivity(intent);
                            finish();

                        }
                    }

                }else {
                    Toast.makeText(MainActivity.this, "User with this" + phone + "does not exists", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(MainActivity.this, "Please create new account", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                loadingBar.dismiss();

            }
        });

    }


    public void autoScroll(){

            final int speedScroll = 0;
            final Handler handler = new Handler();
            final Runnable runnable = new Runnable(){
                int count = 0;
                @Override
                public void run(){
                    if(count == plateAdapters.getItemCount())
                        count =0;
                    if(count < plateAdapters.getItemCount()){
                        recyclerView.smoothScrollToPosition(++count);;
                        handler.postDelayed(this,speedScroll);
                    }

                }
            };
            handler.postDelayed(runnable,speedScroll);
        }


    public void gotohome(View view) {

        Intent intent = new Intent(MainActivity.this, HomeActivity.class);

        startActivity(intent);
        Animatoo.animateSlideLeft(getApplicationContext());
    }




}
