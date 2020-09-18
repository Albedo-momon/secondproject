package com.inphynousplay.groceryappeasy.CategoryActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.inphynousplay.groceryappeasy.CategoryActivity.SubCatActivity.GrocerySubActivity;
import com.inphynousplay.groceryappeasy.R;
import com.inphynousplay.groceryappeasy.adapters.GridGroceryAdapter;

import java.util.ArrayList;
import java.util.List;

public class Personal_Care_Activity extends AppCompatActivity {
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal__care_);
        gridView = findViewById(R.id.gridview);
        List<Integer> images = new ArrayList<>();
        List<String> names = new ArrayList<>();

        images.add(R.drawable.skincare);
        images.add(R.drawable.facecare);
        images.add(R.drawable.soaps);
        images.add(R.drawable.hairoil);
        images.add(R.drawable.kirana);
        images.add(R.drawable.shampoo);
        images.add(R.drawable.hairconditioner);
        images.add(R.drawable.toothpaste);
        images.add(R.drawable.sanitorynapkins);
        images.add(R.drawable.deos);
        images.add(R.drawable.shavingcream);
        images.add(R.drawable.menrazor);
        images.add(R.drawable.womenrazor);
        names.add("Skin Care");
        names.add("Face Care");
        names.add("Soaps");
        names.add("Hair Oil");
        names.add("Hair Color");
        names.add("Shampoo");
        names.add("Hair Conditioner");
        names.add("Toothpaste");
        names.add("Sanitory Napkins");
        names.add("Deos");
        names.add("Shaving Cream");
        names.add("Men Razor");
        names.add("Women Razor");


        GridGroceryAdapter adapter = new GridGroceryAdapter(images, names, this);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Intent intent;
                switch (i) {
                    case 0:
                        intent = new Intent(Personal_Care_Activity.this, GrocerySubActivity.class);
                        intent.putExtra("type", "SkinCare");
                        break;
                    case 1:
                        intent = new Intent(Personal_Care_Activity.this, GrocerySubActivity.class);
                        intent.putExtra("type", "FaceCare");
                        break;

                    case 2:
                        intent = new Intent(Personal_Care_Activity.this, GrocerySubActivity.class);
                        intent.putExtra("type", "Soaps");
                        break;
                    case 3:
                        intent = new Intent(Personal_Care_Activity.this, GrocerySubActivity.class);
                        intent.putExtra("type", "HairOil");
                        break;
                    case 4:
                        intent = new Intent(Personal_Care_Activity.this, GrocerySubActivity.class);
                        intent.putExtra("type", "HairColor");
                        break;
                    case 5:
                        intent = new Intent(Personal_Care_Activity.this, GrocerySubActivity.class);
                        intent.putExtra("type", "Shampoo");
                        break;
                    case 6:
                        intent = new Intent(Personal_Care_Activity.this, GrocerySubActivity.class);
                        intent.putExtra("type", "HairConditioner");
                        break;
                    case 7:
                        intent = new Intent(Personal_Care_Activity.this, GrocerySubActivity.class);
                        intent.putExtra("type", "Toothpaste");
                        break;
                    case 8:
                        intent = new Intent(Personal_Care_Activity.this, GrocerySubActivity.class);
                        intent.putExtra("type", "SanitoryNapkins");
                        break;
                    case 9:
                        intent = new Intent(Personal_Care_Activity.this, GrocerySubActivity.class);
                        intent.putExtra("type", "Deos");
                        break;
                    case 10:
                        intent = new Intent(Personal_Care_Activity.this, GrocerySubActivity.class);
                        intent.putExtra("type", "ShavingCream");
                        break;
                    case 11:
                        intent = new Intent(Personal_Care_Activity.this, GrocerySubActivity.class);
                        intent.putExtra("type", "MenRazor");
                        break;
                    case 12:
                        intent = new Intent(Personal_Care_Activity.this, GrocerySubActivity.class);
                        intent.putExtra("type", "WomenRazor");
                        break;


                    default:
                        intent = new Intent(Personal_Care_Activity.this, Grocery_Cat_Activity.class);
                        break;
                }
                startActivity(intent);
            }
        });
    }
}