package com.example.mysubway.utils.model;

import android.os.Parcel;
import android.os.Parcelable;

public class FoodItem implements Parcelable {

    private String food_item_name, food_item_vendor_name;
    private int food_item_image;
    private double food_item_price;

    public FoodItem(String food_item_name, String food_item_vendor_name, int food_item_image, double food_item_price) {
        this.food_item_name = food_item_name;
        this.food_item_vendor_name = food_item_vendor_name;
        this.food_item_image = food_item_image;
        this.food_item_price = food_item_price;
    }

    protected FoodItem(Parcel in) {
        food_item_name = in.readString();
        food_item_vendor_name = in.readString();
        food_item_image = in.readInt();
        food_item_price = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(food_item_name);
        dest.writeString(food_item_vendor_name);
        dest.writeInt(food_item_image);
        dest.writeDouble(food_item_price);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FoodItem> CREATOR = new Creator<FoodItem>() {
        @Override
        public FoodItem createFromParcel(Parcel in) {
            return new FoodItem(in);
        }

        @Override
        public FoodItem[] newArray(int size) {
            return new FoodItem[size];
        }
    };

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
}
