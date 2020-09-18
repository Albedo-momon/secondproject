package com.inphynousplay.groceryappeasy.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.inphynousplay.groceryappeasy.CategoryActivity.Backery_Cat_Activity;
import com.inphynousplay.groceryappeasy.CategoryActivity.Fruit_And_Vegetable_Cat_Activity;
import com.inphynousplay.groceryappeasy.CategoryActivity.Grocery_Cat_Activity;
import com.inphynousplay.groceryappeasy.CategoryActivity.HomeAndKitchen_Activity;
import com.inphynousplay.groceryappeasy.CategoryActivity.Milk_Product_Cat_Activity;
import com.inphynousplay.groceryappeasy.CategoryActivity.Packaged_Food;
import com.inphynousplay.groceryappeasy.CategoryActivity.Personal_Care_Activity;
import com.inphynousplay.groceryappeasy.CategoryActivity.SubCatActivity.PackagedSubActivity;
import com.inphynousplay.groceryappeasy.HomeActivity;
import com.inphynousplay.groceryappeasy.Models.BannerModel;
import com.inphynousplay.groceryappeasy.R;
import com.inphynousplay.groceryappeasy.adapters.BannerViewHolder;
import com.inphynousplay.groceryappeasy.adapters.CategoryAdapter;
import com.inphynousplay.groceryappeasy.adapters.GridGroceryAdapter;
import com.inphynousplay.groceryappeasy.adapters.gridCategory;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Home_Fragment extends Fragment {


    public Home_Fragment() {
        // Required empty public constructor
    }

    RecyclerView recyclerViewBanner, recyclerViewCategory;
    DatabaseReference databaseReference;
    GridView gridView;
    private static Bundle mBundleRecyclerViewState;
    private final String KEY_RECYCLER_STATE = "recycler_state";
    View v;
    List<String> titles, title;
    List<String> marTitles;
    List<Integer> images;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_, container, false);


        //recyclerViewCategory = view.findViewById(R.id.productCategory);
        gridView = view.findViewById(R.id.gridview);


        titles = new ArrayList<>();
        images = new ArrayList<>();
        marTitles = new ArrayList<>();

        titles.add("Grocery");
        titles.add("Packaged Food");
        titles.add("Fruits & Vegetables");
        titles.add("Dairy & Beverages");
        titles.add("Backery");
        titles.add("Home & Kitchen");
        titles.add("Personal Care");
//        marTitles.add("किराणा");
//        marTitles.add("सब्जिया");
//        marTitles.add("फल");
//        marTitles.add("दूध उत्पाद");

        images.add(R.drawable.kirana);
        images.add(R.drawable.packagedfood);
        images.add(R.drawable.vege);
        images.add(R.drawable.milk);
        images.add(R.drawable.bakeryproducts);
        images.add(R.drawable.homeandkitchen);
        images.add(R.drawable.personalcare);
//        CategoryAdapter adapter = new CategoryAdapter(titles, marTitles, images, getContext());
//        GridLayoutManager gridLayout = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
//        recyclerViewCategory.setLayoutManager(gridLayout);
//        recyclerViewCategory.setAdapter(adapter);
        gridCategory adapter = new gridCategory(images, titles, getContext());
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Intent intent;
                switch (i) {
                    case 0:
                        intent = new Intent(getActivity(), Grocery_Cat_Activity.class);
                        break;
                    case 1:
                        intent = new Intent(getActivity(), Packaged_Food.class);
                        break;

                    case 2:
                        intent = new Intent(getActivity(), Fruit_And_Vegetable_Cat_Activity.class);
                        break;
                    case 3:
                        intent = new Intent(getActivity(), Milk_Product_Cat_Activity.class);
                        break;
                    case 4:
                        intent = new Intent(getActivity(), Backery_Cat_Activity.class);
                        break;
                    case 5:
                        intent = new Intent(getActivity(), HomeAndKitchen_Activity.class);
                        break;
                    case 6:
                        intent = new Intent(getActivity(), Personal_Care_Activity.class);
                        break;


                    default:
                        intent = new Intent(getActivity(), HomeActivity.class);
                        break;
                }
                startActivity(intent);
            }
        });


        return view;


    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.v = view;
        init();
        loadData();
    }

    private void loadData() {

        databaseReference = FirebaseDatabase.getInstance().getReference().child("car");

        FirebaseRecyclerOptions<BannerModel> options =
                new FirebaseRecyclerOptions.Builder<BannerModel>()
                        .setQuery(databaseReference, BannerModel.class)
                        .build();

        FirebaseRecyclerAdapter<BannerModel, BannerViewHolder> adpater =
                new FirebaseRecyclerAdapter<BannerModel, BannerViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull BannerViewHolder holder, int position, @NonNull BannerModel model) {
                        Picasso.get().load(model.getImageUrl()).into(holder.imageView);

                    }

                    @NonNull
                    @Override
                    public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_banner, parent, false);
                        BannerViewHolder holder = new BannerViewHolder(view);
                        return holder;
                    }
                };
        recyclerViewBanner.setAdapter(adpater);
        adpater.startListening();
    }

    private void init() {
        recyclerViewBanner = v.findViewById(R.id.bannerOffer);
        recyclerViewBanner.setHasFixedSize(true);
        recyclerViewBanner.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));

    }

    @Override
    public void onStart() {
        super.onStart();
        recyclerViewBanner.setHasFixedSize(true);
        recyclerViewBanner.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));


    }


}