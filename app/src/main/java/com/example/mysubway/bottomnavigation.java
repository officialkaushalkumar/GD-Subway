package com.example.mysubway;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.mysubway.views.CartActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class bottomnavigation extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Profile profile = new Profile();
    HomeFragment homeFragment = new HomeFragment();
    CartFragment cartFragment = new CartFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottomnavigation);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
         getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,homeFragment).commit();

         bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
             @Override
             public boolean onNavigationItemSelected(MenuItem item) {

                 switch (item.getItemId()){
                     case R.id.navigation_home:
                         getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,homeFragment).commit();
                         return true;
                     case R.id.navigation_cart:
                         getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,cartFragment).commit();
                         return true;
                     case R.id.navigation_profile:
                         getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,profile).commit();
                         return true;

                 }
                 return false;
             }
         });
    }
}