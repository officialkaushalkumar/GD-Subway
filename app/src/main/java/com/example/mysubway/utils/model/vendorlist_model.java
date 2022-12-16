package com.example.mysubway.utils.model;

import android.os.Parcel;
import android.os.Parcelable;

public class vendorlist_model implements Parcelable {

    private String vendor_name;
    private int vendor_image;

    protected vendorlist_model(Parcel in) {
        vendor_name = in.readString();
        vendor_image = in.readInt();
    }

    public static final Creator<vendorlist_model> CREATOR = new Creator<vendorlist_model>() {
        @Override
        public vendorlist_model createFromParcel(Parcel in) {
            return new vendorlist_model(in);
        }

        @Override
        public vendorlist_model[] newArray(int size) {
            return new vendorlist_model[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(vendor_name);
        parcel.writeInt(vendor_image);
    }

    public vendorlist_model(String vendor_name, int vendor_image) {
        this.vendor_name = vendor_name;
        this.vendor_image = vendor_image;
    }

    public String getVendor_name() {
        return vendor_name;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }

    public int getVendor_image() {
        return vendor_image;
    }

    public void setVendor_image(int vendor_image) {
        this.vendor_image = vendor_image;
    }
}
