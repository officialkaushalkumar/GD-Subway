package com.example.mysubway.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mysubway.repository.CartRepo;
import com.example.mysubway.utils.model.FoodCart;

import java.util.List;

public class CartViewModel extends AndroidViewModel {

    private CartRepo cartRepo;
    public CartViewModel(@NonNull Application application) {
        super(application);

        cartRepo = new CartRepo(application);

    }

    public LiveData<List<FoodCart> > getAllCartItems(){
        return cartRepo.getAllCartItemsLiveData();
    }

    public void insertCartItem(FoodCart foodCart_item){
        cartRepo.insertCartItem(foodCart_item);
    }
    public void updateQuantity(int id, int quantity){
        cartRepo.updateQuantity(id,quantity);
    }

    public void updatePrice(int id, double price){
        cartRepo.updatePrice(id,price);
    }
    public void deleteCartItem(FoodCart foodCart_item){
        cartRepo.deleteCartItem(foodCart_item);
    }
    public void deleteAllCartItems(){
        cartRepo.deleteAllCartItems();
    }
}
