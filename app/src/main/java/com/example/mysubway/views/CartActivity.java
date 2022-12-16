package com.example.mysubway.views;

import static android.widget.Toast.LENGTH_SHORT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mysubway.MainActivity;
import com.example.mysubway.R;
import com.example.mysubway.dashboard;
import com.example.mysubway.navigationProfile;
import com.example.mysubway.utils.adapter.CartAdapter;
import com.example.mysubway.utils.model.FoodCart;
import com.example.mysubway.viewmodel.CartViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.util.List;

public class CartActivity extends AppCompatActivity implements CartAdapter.CartClickedListeners , PaymentResultListener {

    private RecyclerView recyclerView;
    private CartViewModel  cartViewModel;
    private TextView totalCartPricetv,textView;
    private AppCompatButton checkoutBtn;
    private CardView cardView;
    private CartAdapter cartAdapter;
    String razorpaypricetxt;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        initializeVariables();
        bottomNavigationView = findViewById(R.id.bottom_navigation);


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()){
                    case R.id.navigation_home:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,homeFragment).commit();
                        item.setIcon(getResources().getDrawable(R.drawable.ic_baseline_home_grey));
                        startActivity(new Intent(CartActivity.this,dashboard.class));
                        return true;
                    case R.id.navigation_cart:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,cartFragment).commit();
                        startActivity(new Intent(CartActivity.this,CartActivity.class));
                        item.setIcon(getResources().getDrawable(R.drawable.ic_baseline_shopping_cart_blue));
                        return true;
                    case R.id.navigation_profile:
                        startActivity(new Intent(CartActivity.this, navigationProfile.class));
//                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,profile).commit();
                        return true;

                }
                return false;
            }
        });


        cartViewModel.getAllCartItems().observe(this, new Observer<List<FoodCart>>() {
            @Override
            public void onChanged(List<FoodCart> foodCarts) {
                double price =0;
                cartAdapter.setFoodCartList(foodCarts);
                for(int i=0;i<foodCarts.size();i++){
                    price = price + foodCarts.get(i).getTotalItemPrice();
                }
                razorpaypricetxt=String.valueOf(price*100);
                totalCartPricetv.setText(String.valueOf(price));
            }
        });

        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(Integer.parseInt(razorpaypricetxt)>100) {
//
//                }
//                else {
//                    Toast.makeText(CartActivity.this, "There is no item in the cart", Toast.LENGTH_LONG).show();
////                    Toast.makeText(MainActivity.this, ""+task.getException() , Toast.LENGTH_SHORT).show();
//                }
                cartViewModel.deleteAllCartItems();
                cardView.setVisibility(View.VISIBLE);
                textView.setVisibility(View.INVISIBLE);
                checkoutBtn.setVisibility(View.INVISIBLE);
                totalCartPricetv.setVisibility(View.INVISIBLE);
                startPayment();


            }
        });


    }

    private void initializeVariables(){
        textView =findViewById(R.id.thistextView2);
        cardView = findViewById(R.id.cartActivityCardView);
        totalCartPricetv = findViewById(R.id.cartActivityTotalPriceTv);
        checkoutBtn = findViewById(R.id.cartActivityCheckoutBtn);
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        recyclerView = findViewById(R.id.cartRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartAdapter = new CartAdapter(this);

        recyclerView.setAdapter(cartAdapter);
    }

    @Override
    public void onDeleteClicked(FoodCart foodCart) {
        cartViewModel.deleteCartItem(foodCart);
    }

    @Override
    public void onPlusClicked(FoodCart foodCart) {
        int quantity =foodCart.getQuantity()+1;
        cartViewModel.updateQuantity(foodCart.getId(),quantity);
        cartViewModel.updatePrice(foodCart.getId(),quantity*foodCart.getFood_item_price());
        cartAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMinusClicked(FoodCart foodCart) {
        int quantity =foodCart.getQuantity() - 1;
        if(quantity!=0){
            cartViewModel.updateQuantity(foodCart.getId(),quantity);
            cartViewModel.updatePrice(foodCart.getId(),quantity*foodCart.getFood_item_price());
            cartAdapter.notifyDataSetChanged();
        }else{
            cartViewModel.deleteCartItem(foodCart);
        }

    }
        public void startPayment(){
            Checkout checkout = new Checkout();
            checkout.setImage(R.mipmap.ic_launcher);
            final Activity activity = this;
            try{
                JSONObject options = new JSONObject();
                options.put("name", R.string.app_name);
                options.put("description","payment for anything");
                options.put("send_sms_hash",true);
                options.put("allow_rotation",false);
                options.put("currency","INR");
                options.put("amount",razorpaypricetxt);
                JSONObject preFill = new JSONObject();
                preFill.put("email","");
                preFill.put("contact","");
                options.put("preFill",preFill);
                checkout.open(activity,options);
            }
            catch(Exception e){
                Toast.makeText(activity, "error in payment" + e.getMessage(), LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }

        @Override
        public void onPaymentSuccess(String s) {

//        Toast.makeText(this, "payment successful" + s, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(CartActivity.this, CartActivity.class);
            startActivity(intent);
        }

        @Override
        public void onPaymentError(int i, String s) {
            Toast.makeText(this, "payment fail"+s, LENGTH_SHORT).show();
//            Intent intent = new Intent(CartActivity.this,paymentfail.class);
//            startActivity(intent);
        }
}