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

public class fusionbistro extends AppCompatActivity implements foodItemAdapter.FoodClickedListener {

    private RecyclerView recyclerView;
    private List<FoodItem> foodItemList;
    private foodItemAdapter adapter;
    CartViewModel cartViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fusionbistro);

        initializeVariables();
        setUplist();
        adapter.setFoodItemList(foodItemList);
        recyclerView.setAdapter(adapter);
    }

    private void setUplist() {
        foodItemList.add(new FoodItem("Mendu Bada","fusionbistro",R.drawable.medu,20));
        foodItemList.add(new FoodItem("Idli","fusionbistro",R.drawable.idli,30));
        foodItemList.add(new FoodItem("Veg Utpam","fusionbistro",R.drawable.utpam,40));
        foodItemList.add(new FoodItem("Appe","fusionbistro",R.drawable.appe,40));
        foodItemList.add(new FoodItem("Dosa","fusionbistro",R.drawable.dosa,60));
//        foodItemList.add(new FoodItem("Purger","fusionbistro",R.drawable.food,50));

    }

    private  void initializeVariables(){

        adapter = new foodItemAdapter(this);
        foodItemList = new ArrayList<>();
        recyclerView = findViewById(R.id.mainRecyclerView6);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);


    }

    @Override
    public void onCardClicked(FoodItem foodItem) {
//        Intent intent = new Intent(fusionbistro.this, detailedActivity.class );
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