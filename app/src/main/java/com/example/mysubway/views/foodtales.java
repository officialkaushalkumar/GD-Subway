package com.example.mysubway.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.mysubway.R;
import com.example.mysubway.utils.adapter.foodItemAdapter;
import com.example.mysubway.utils.model.FoodCart;
import com.example.mysubway.utils.model.FoodItem;
import com.example.mysubway.viewmodel.CartViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class foodtales extends AppCompatActivity implements foodItemAdapter.FoodClickedListener {

    private RecyclerView recyclerView;
    private List<FoodItem> foodItemList;
    private foodItemAdapter adapter;
    private CartViewModel  cartViewModel;
    private List<FoodCart> foodCartList;
    private CoordinatorLayout coordinatorLayout;
    private ImageView cartImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodtales);

        initializeVariables();
        setUplist();

        adapter.setFoodItemList(foodItemList);
        recyclerView.setAdapter(adapter);
        cartImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(foodtales.this, CartActivity.class));
            }
        });
    }
    @Override
   protected void onResume(){
        super.onResume();

        cartViewModel.getAllCartItems().observe(this, new Observer<List<FoodCart>>() {
            @Override
            public void onChanged(List<FoodCart> foodCarts) {
                foodCartList.addAll(foodCarts);
            }
        });
   }

    private void setUplist() {
        foodItemList.add(new FoodItem("Samosa","FoodTales",R.drawable.samosa,25));
        foodItemList.add(new FoodItem("Kachodi","FoodTales",R.drawable.kachodi,30));
        foodItemList.add(new FoodItem("Bedmi Pudi","FoodTales",R.drawable.bedai,50));
        foodItemList.add(new FoodItem("Namkin Chaat","FoodTales",R.drawable.namkeen,70));
        foodItemList.add(new FoodItem("Paapdi Chaat","FoodTales",R.drawable.papdi,60));
//        foodItemList.add(new FoodItem("Purger","FoodTales",R.drawable.food,50));

    }

    private  void initializeVariables(){

        adapter = new foodItemAdapter(this);
        foodItemList = new ArrayList<>();
        recyclerView = findViewById(R.id.mainRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        foodCartList = new ArrayList<>();
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        cartImageView = findViewById(R.id.cartIv);


    }

    @Override
    public void onCardClicked(FoodItem foodItem) {
//        Intent intent = new Intent(foodtales.this, com.example.mysubway.views.detailedActivity.class );
//        intent.putExtra("foodItem",foodItem);
//        startActivity(intent);
    }

    @Override
    public void onAddToCartBtnClicked(FoodItem foodItem) {
        FoodCart foodCart = new FoodCart();
        foodCart.setFood_item_name(foodItem.getFood_item_name());
        foodCart.setFood_item_price(foodItem.getFood_item_price());
        foodCart.setFood_item_image(foodItem.getFood_item_image());

        final int[] quantity = {1};
        final int[] id = new int[1];
        if(!foodCartList.isEmpty()){
            for(int i=0;i<foodCartList.size();i++){
                if(foodCart.getFood_item_name().equals(foodCartList.get(i).getFood_item_name()));{
                    quantity[0] = foodCartList.get(i).getQuantity();
                    quantity[0]++;
                    id[0]= foodCartList.get(i).getId();
                }
            }
        }

        if(quantity[0]==1){
            foodCart.setQuantity(quantity[0]);
            foodCart.setTotalItemPrice(quantity[0]*foodCart.getFood_item_price());
            cartViewModel.insertCartItem(foodCart);
        }
        else{
            cartViewModel.updateQuantity(id[0],quantity[0]);
            cartViewModel.updatePrice(id[0],quantity[0]*foodCart.getFood_item_price());
        }

        makeSnackbar("Item Added to Cart");

//        cartViewModel.insertCartItem(foodCart);
    }

    private void makeSnackbar(String msg) {
        Snackbar.make(coordinatorLayout,msg,Snackbar.LENGTH_SHORT).setAction("GO to Cart", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(foodtales.this, CartActivity.class));
            }
        }).show();
    }
}