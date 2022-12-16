package com.example.mysubway.utils.model;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "food_table")
public class FoodCart {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String food_item_name,food_item_vendor_name;
    private int food_item_image;
    private double food_item_price;
    private int quantity;
    private double totalItemPrice;


    public String getFood_item_name() {
        return food_item_name;
    }

    public void setFood_item_name(String food_item_name) {
        this.food_item_name = food_item_name;
    }

    public String getFood_item_vendor_name() {
        return food_item_vendor_name;
    }

    public void setFood_item_vendor_name(String food_item_vendor_name) {
        this.food_item_vendor_name = food_item_vendor_name;
    }

    public int getFood_item_image() {
        return food_item_image;
    }

    public void setFood_item_image(int food_item_image) {
        this.food_item_image = food_item_image;
    }

    public double getFood_item_price() {
        return food_item_price;
    }

    public void setFood_item_price(double food_item_price) {
        this.food_item_price = food_item_price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalItemPrice() {
        return totalItemPrice;
    }

    public void setTotalItemPrice(double totalItemPrice) {
        this.totalItemPrice = totalItemPrice;
    }
}
