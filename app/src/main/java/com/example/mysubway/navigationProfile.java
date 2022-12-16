package com.example.mysubway;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mysubway.views.CartActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class navigationProfile extends AppCompatActivity {
    TextView aboutus , contactus,order,logout;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    BottomNavigationView bottomNavigationView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_profile);

        mAuth =FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        aboutus = findViewById(R.id.profile_aboutus);
        contactus = findViewById(R.id.profile_contactus);
        order = findViewById(R.id.profile_order);
        logout = findViewById(R.id.profile_logout);
        bottomNavigationView= findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        startActivity(new Intent(navigationProfile.this, dashboard.class));
                        item.setIcon(getResources().getDrawable(R.drawable.ic_baseline_home_grey));
                        return true;
                    case R.id.navigation_cart:
                        startActivity(new Intent(navigationProfile.this, CartActivity.class));
                        return true;
                    case R.id.navigation_profile:
                        startActivity(new Intent(navigationProfile.this, navigationProfile.class));
                        item.setIcon(getResources().getDrawable(R.drawable.ic_baseline_person_blue));
                        return true;

                }
                return false;

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(navigationProfile.this,MainActivity.class));
                Toast.makeText(navigationProfile.this,"logout succesfully",Toast.LENGTH_LONG).show();
            }
        });
        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(navigationProfile.this, aboutus.class));
            }
        });
        contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(navigationProfile.this, contactus.class));
            }
        });
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(navigationProfile.this, order.class));
            }
        });
    }
}