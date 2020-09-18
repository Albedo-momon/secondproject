package com.inphynousplay.groceryappeasy.CategoryActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.inphynousplay.groceryappeasy.CategoryActivity.SubCatActivity.BackerySubActivity;
import com.inphynousplay.groceryappeasy.CategoryActivity.SubCatActivity.GrocerySubActivity;
import com.inphynousplay.groceryappeasy.R;
import com.inphynousplay.groceryappeasy.adapters.GridGroceryAdapter;

import java.util.ArrayList;
import java.util.List;

public class Backery_Cat_Activity extends AppCompatActivity {
    private GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backery__cat_);
        gridView = findViewById(R.id.gridview);
        List<Integer> images = new ArrayList<>();
        List<String> names = new ArrayList<>();

        images.add(R.drawable.toast);
        images.add(R.drawable.khari);
        images.add(R.drawable.bread);
        images.add(R.drawable.kirana);
        names.add("Toast");
        names.add("Khari");
        names.add("Bread");
        names.add("Others");


        GridGroceryAdapter adapter = new GridGroceryAdapter(images, names, this);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Intent intent;
                switch (i){
                    case 0:
                        intent = new Intent(Backery_Cat_Activity.this, BackerySubActivity.class);
                        intent.putExtra("type","Toast");
                        break;
                    case 1:
                        intent = new Intent(Backery_Cat_Activity.this, BackerySubActivity.class);
                        intent.putExtra("type","Khari");
                        break;

                    case 2:
                        intent = new Intent(Backery_Cat_Activity.this, BackerySubActivity.class);
                        intent.putExtra("type","Bread");
                        break;
                    case 3:
                        intent = new Intent(Backery_Cat_Activity.this, BackerySubActivity.class);
                        intent.putExtra("type","Others");
                        break;


                    default:
                        intent = new Intent(Backery_Cat_Activity.this, BackerySubActivity.class);
                        break;
                }
                startActivity(intent);
                }

        });
    }
}