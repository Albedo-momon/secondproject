package com.inphynousplay.groceryappeasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.widget.SearchView;

import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.inphynousplay.groceryappeasy.Cart.CartActivity;
import com.inphynousplay.groceryappeasy.Fragments.Home_Fragment;
import com.inphynousplay.groceryappeasy.Prevelent.Prevelent;
import com.squareup.picasso.Picasso;

import io.paperdb.Paper;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigation;
    FrameLayout frameLayout;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    View headerView;
    TextView searchtext;
    ImageView searchView;
    Button mbutton, mlogin;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    boolean doubleBackToExitPressedOnce = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavigation = findViewById(R.id.bottomnevigation);

        frameLayout = findViewById(R.id.framelayout);
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer);
        searchView = findViewById(R.id.searchview);
        searchtext = findViewById(R.id.search);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        headerView = navigationView.getHeaderView(0);
        TextView userNameTextView = headerView.findViewById(R.id.profile_name);
        TextView log_out = headerView.findViewById(R.id.tv_logout);
        Paper.init(this);
        ImageView profileImage = headerView.findViewById(R.id.profile_picture);
        setSupportActionBar(toolbar);
        mlogin = findViewById(R.id.login);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.framelayout, new Home_Fragment());
        fragmentTransaction.commit();

        userNameTextView.setText(Prevelent.currentOnlineUser.getFirstname());
        Picasso.get().load(Prevelent.currentOnlineUser.getImage()).into(profileImage);

        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Paper.book().destroy();
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });


        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                switch (item.getItemId()) {

                    case R.id.home:
                        selectedFragment = new Home_Fragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, selectedFragment).commit();
                        break;
                    case R.id.order:
                        startActivity(new Intent(HomeActivity.this, OrderActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));

                        break;

                    case R.id.zcart:
                        startActivity(new Intent(HomeActivity.this, CartActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));

                        break;


                }
                return false;
            }
        });


        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();


        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, SearchActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));

            }
        });
        searchtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, SearchActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.framelayout, new Home_Fragment());
                fragmentTransaction.commit();
                break;
            case R.id.order:
                startActivity(new Intent(HomeActivity.this, OrderActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                break;
            case R.id.setting:
                startActivity(new Intent(HomeActivity.this, SettingActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));

                break;
            case R.id.cart:
                startActivity(new Intent(HomeActivity.this, CartActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));

                break;
            case R.id.about:
                startActivity(new Intent(HomeActivity.this, AboutUsActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));

                break;
            case R.id.developer:
                break;


        }
        drawerLayout.closeDrawers();
        return true;

    }

    @Override
    public void onBackPressed() {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            finishAffinity();
            finish();
        }




    }


}