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

public class thegreenside extends AppCompatActivity implements foodItemAdapter.FoodClickedListener {

    private RecyclerView recyclerView;
    private List<FoodItem> foodItemList;
    private foodItemAdapter adapter;
    CartViewModel cartViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thegreenside);

        initializeVariables();
        setUplist();
        adapter.setFoodItemList(foodItemList);
        recyclerView.setAdapter(adapter);
    }

    private void setUplist() {
        foodItemList.add(new FoodItem("Cream Roll","thegreenside",R.drawable.creamroll,20));
        foodItemList.add(new FoodItem("Cup Cake","thegreenside",R.drawable.cupcake,30));
        foodItemList.add(new FoodItem("Mix Pattie","thegreenside",R.drawable.mixpattie,40));
        foodItemList.add(new FoodItem("Brownie","thegreenside",R.drawable.brownie,50));
        foodItemList.add(new FoodItem("Hot Dog","thegreenside",R.drawable.hotdog,70));
//        foodItemList.add(new FoodItem("Purger","thegreenside",R.drawable.food,50));

    }

    private  void initializeVariables(){

        adapter = new foodItemAdapter(this);
        foodItemList = new ArrayList<>();
        recyclerView = findViewById(R.id.mainRecyclerView5);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);


    }

    @Override
    public void onCardClicked(FoodItem foodItem) {
//        Intent intent = new Intent(thegreenside.this, detailedActivity.class );
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