package com.example.mysubway;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.mysubway.views.CartActivity;
import com.example.mysubway.views.brijwasiexpress;
import com.example.mysubway.views.foodtales;
import com.example.mysubway.views.fruitsandjuice;
import com.example.mysubway.views.fusionbistro;
import com.example.mysubway.views.mastertandoor;
import com.example.mysubway.views.thegreenside;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class dashboard extends AppCompatActivity {

    RelativeLayout rel_foodtales,rel_fruitsandjuices,rel_brijwasiexpress,rel_mastertandoor,rel_thegreenside,rel_fusionbistro;
    BottomNavigationView bottomNavigationView;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        rel_foodtales = findViewById(R.id.rel_foodtales);
        rel_fruitsandjuices = findViewById(R.id.rel_fruitsandjuice);
        rel_brijwasiexpress = findViewById(R.id.rel_brijwasiexpress);
        rel_mastertandoor = findViewById(R.id.rel_mastertandoor);
        rel_thegreenside = findViewById(R.id.rel_thegreenside);
        rel_fusionbistro = findViewById(R.id.rel_fusionbistro);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                                                           @Override
                                                           public boolean onNavigationItemSelected(MenuItem item) {

                                                               switch (item.getItemId()) {
                                                                   case R.id.navigation_home:
                                                                       startActivity(new Intent(dashboard.this, dashboard.class));
                                                                       item.setIcon(getResources().getDrawable(R.drawable.ic_baseline_home_blue));
                                                                       return true;
                                                                   case R.id.navigation_cart:
                                                                       startActivity(new Intent(dashboard.this, CartActivity.class));
//                                                                       item.setIcon(getResources().getDrawable(R.drawable.ic_baseline_home_blue));
                                                                       return true;
                                                                   case R.id.navigation_profile:
                                                                       startActivity(new Intent(dashboard.this, navigationProfile.class));
                                                                       return true;

                                                               }
                                                               return false;

                                                           }
                                                       });


        rel_foodtales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dashboard.this, foodtales.class);
                startActivity(intent);
            }
        });
        rel_fruitsandjuices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dashboard.this, fruitsandjuice.class);
                startActivity(intent);
            }
        });
        rel_brijwasiexpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dashboard.this, brijwasiexpress.class);
                startActivity(intent);
            }
        });
        rel_mastertandoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dashboard.this, mastertandoor.class);
                startActivity(intent);
            }
        });
        rel_thegreenside.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dashboard.this, thegreenside.class);
                startActivity(intent);
            }
        });
        rel_fusionbistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dashboard.this, fusionbistro.class);
                startActivity(intent);
            }
        });
    }
}