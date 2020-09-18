package com.inphynousplay.groceryappeasy.CategoryActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.inphynousplay.groceryappeasy.CategoryActivity.SubCatActivity.GrocerySubActivity;
import com.inphynousplay.groceryappeasy.CategoryActivity.SubCatActivity.HomeAndKitchenSubActivity;
import com.inphynousplay.groceryappeasy.HomeActivity;
import com.inphynousplay.groceryappeasy.R;
import com.inphynousplay.groceryappeasy.adapters.GridGroceryAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeAndKitchen_Activity extends AppCompatActivity {
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_and_kitchen_);
        gridView = findViewById(R.id.gridview);
        List<Integer> images = new ArrayList<>();
        List<String> names = new ArrayList<>();
        images.add(R.drawable.detergentpowder);
        images.add(R.drawable.dentergentbar);
        images.add(R.drawable.detergentliquid);
        images.add(R.drawable.cleaners);
        images.add(R.drawable.utensilcleaner);
        images.add(R.drawable.repellents);

        names.add("Detergent Powder");
        names.add("Detergent Bar/Soap");
        names.add("Detergent Liquid");
        names.add("Cleaners");
        names.add("Utensil Cleaners");
        names.add("Repellents");

        GridGroceryAdapter adapter = new GridGroceryAdapter(images, names, this);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Intent intent;
                switch (i){
                    case 0:
                        intent = new Intent(HomeAndKitchen_Activity.this, HomeAndKitchenSubActivity.class);
                        intent.putExtra("type","DetergentPowder");
                        break;
                    case 1:
                        intent = new Intent(HomeAndKitchen_Activity.this, HomeAndKitchenSubActivity.class);
                        intent.putExtra("type","DetergentBarSoap");
                        break;

                    case 2:
                        intent = new Intent(HomeAndKitchen_Activity.this, HomeAndKitchenSubActivity.class);
                        intent.putExtra("type","DetergentLiquid");
                        break;
                    case 3:
                        intent = new Intent(HomeAndKitchen_Activity.this, HomeAndKitchenSubActivity.class);
                        intent.putExtra("type","Cleaners");
                        break;
                    case 4:
                        intent = new Intent(HomeAndKitchen_Activity.this, HomeAndKitchenSubActivity.class);
                        intent.putExtra("type","UtensilCleaner");
                        break;
                    case 5:
                        intent = new Intent(HomeAndKitchen_Activity.this, HomeAndKitchenSubActivity.class);
                        intent.putExtra("type","Replellents");
                        break;

                    default:
                        intent = new Intent(HomeAndKitchen_Activity.this, HomeActivity.class);
                        break;
                }
                startActivity(intent);
                }

        });
    }
}