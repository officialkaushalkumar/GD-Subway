package com.example.mysubway.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mysubway.R;
import com.example.mysubway.utils.model.FoodCart;
import com.example.mysubway.utils.model.FoodItem;
import com.example.mysubway.viewmodel.CartViewModel;

import java.util.ArrayList;
import java.util.List;

public class detailedActivity extends AppCompatActivity {

    private ImageView foodImageview;
    private TextView foodNameTv,foodPriceTv;
    private AppCompatButton addtocartbtn;
    private FoodItem foodItem;
    CartViewModel cartViewModel;
    private List<FoodCart> foodCartList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        foodItem=getIntent().getParcelableExtra("foodI tem");

        initializeVariables();

        cartViewModel.getAllCartItems().observe(this, new Observer<List<FoodCart>>() {
            @Override
            public void onChanged(List<FoodCart> foodCarts) {
                foodCartList.addAll(foodCarts);
            }
        });

        if(foodItem!=null){
            setDataToWidgets();
        }

        addtocartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertToRoom();
            }
        });
    }

    private void insertToRoom(){
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

        startActivity(new Intent(detailedActivity.this,CartActivity.class));

    }

    private void setDataToWidgets(){
        foodNameTv.setText(foodItem.getFood_item_name());
        foodPriceTv.setText(String.valueOf(foodItem.getFood_item_price()));
        foodImageview.setImageResource(foodItem.getFood_item_image());
    }

    private void initializeVariables(){

        foodImageview = findViewById(R.id.detailActivityFoodIV);
        foodNameTv = findViewById(R.id.detailActivityFoodNameTv);
        foodPriceTv=findViewById(R.id.detailActivityShoePriceTv);
        addtocartbtn=findViewById(R.id.detailActivityAddToCartBtn);

        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        foodCartList = new ArrayList<>();
    }
}