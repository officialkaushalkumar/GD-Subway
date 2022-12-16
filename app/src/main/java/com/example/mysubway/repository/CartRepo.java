package com.example.mysubway.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.mysubway.dao.CartDao;
import com.example.mysubway.database.CartDatabase;
import com.example.mysubway.utils.model.FoodCart;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CartRepo {

    private CartDao cartDao;
    private LiveData<List<FoodCart>> allCartItemsLiveData;
    private Executor executor = Executors.newSingleThreadExecutor();

    public LiveData<List<FoodCart>> getAllCartItemsLiveData() {
        return allCartItemsLiveData;
    }

    public CartRepo(Application application){
        cartDao = CartDatabase.getInstance(application).cartDao();
        allCartItemsLiveData = cartDao.getAllCartItems();
    }

    public void insertCartItem(FoodCart foodCart_item){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cartDao.insetCartItem(foodCart_item);
            }
        });
    }
    public void deleteCartItem(FoodCart foodCart_item){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cartDao.deleteCartItem(foodCart_item);
            }
        });
    }
    public void updateQuantity(int id,int quantity){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cartDao.updateQuantity(id,quantity);
            }
        });
    }

    public void updatePrice(int id,double price){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cartDao.updatePrice(id,price);
            }
        });
    }
    public void deleteAllCartItems(){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cartDao.deleteAllItems();
            }
        });
    }

}