package com.example.mysubway;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.RelativeLayout;
//
//import com.example.mysubway.views.brijwasiexpress;
//import com.example.mysubway.views.foodtales;
//import com.example.mysubway.views.fruitsandjuice;
//import com.example.mysubway.views.fusionbistro;
//import com.example.mysubway.views.mastertandoor;
//import com.example.mysubway.views.thegreenside;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_dashboard, container, false);
    }

}