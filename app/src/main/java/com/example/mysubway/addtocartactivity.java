package com.example.mysubway;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

public class addtocartactivity extends AppCompatActivity {
private RecyclerView.Adapter adapter;
private RecyclerView recyclerViewList;
TextView itemstotalamounttxt,deliveryamounttxt,taxamounttxt,totalamounttxt,emptytxt;
private double tax;
private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtocartactivity);

        intView();
    }

    private void intView() {

        itemstotalamounttxt=findViewById(R.id.itemstotalamountxt);
        deliveryamounttxt = findViewById(R.id.deliveryamounttxt);
        taxamounttxt = findViewById(R.id.taxamounttxt);
        totalamounttxt =findViewById(R.id.totalamounttxt);
        emptytxt = findViewById(R.id.emptytxt);
        scrollView = findViewById(R.id.scrollView2);
    }
    private void initList(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

    }
}