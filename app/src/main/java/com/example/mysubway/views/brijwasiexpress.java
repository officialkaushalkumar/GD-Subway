package com.example.mysubway.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.mysubway.R;
import com.example.mysubway.utils.adapter.foodItemAdapter;
import com.example.mysubway.utils.model.FoodCart;
import com.example.mysubway.utils.model.FoodItem;
import com.example.mysubway.viewmodel.CartViewModel;

import java.util.ArrayList;
import java.util.List;

public class brijwasiexpress extends AppCompatActivity implements foodItemAdapter.FoodClickedListener {

    private RecyclerView recyclerView;
    private List<FoodItem> foodItemList;
    private foodItemAdapter adapter;
    CartViewModel cartViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brijwasiexpress);

        initializeVariables();
        setUplist();
        adapter.setFoodItemList(foodItemList);
        recyclerView.setAdapter(adapter);
    }

    private void setUplist() {
        foodItemList.add(new FoodItem("Garlic Bread","brijwasiexpress",R.drawable.garlic,40));
        foodItemList.add(new FoodItem("Noodles","brijwasiexpress",R.drawable.noodle,50));
        foodItemList.add(new FoodItem("Chilla","brijwasiexpress",R.drawable.chilla,50));
        foodItemList.add(new FoodItem("Badam Milk","brijwasiexpress",R.drawable.badam,20));
        foodItemList.add(new FoodItem("Chocolate","brijwasiexpress",R.drawable.choclate,60));
//        foodItemList.add(new FoodItem("Purger","brijwasiexpress",R.drawable.food,50));

    }

    private  void initializeVariables(){

        adapter = new foodItemAdapter(this);
        foodItemList = new ArrayList<>();
        recyclerView = findViewById(R.id.mainRecyclerView3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);


    }

    @Override
    public void onCardClicked(FoodItem foodItem) {
//        Intent intent = new Intent(brijwasiexpress.this, detailedActivity.class );
//        intent.putExtra("foodItem",foodItem);
//        startActivity(intent);
    }

    @Override
    public void onAddToCartBtnClicked(FoodItem foodItem) {
        FoodCart foodCart = new FoodCart();
        foodCart.setFood_item_name(foodItem.getFood_item_name());
        foodCart.setFood_item_price(foodItem.getFood_item_price());
        foodCart.setFood_item_image(foodItem.getFood_item_image());

        cartViewModel.insertCartItem(foodCart);
    }
}