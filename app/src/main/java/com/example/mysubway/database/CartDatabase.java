package com.example.mysubway.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mysubway.dao.CartDao;
import com.example.mysubway.utils.model.FoodCart;

@Database(entities ={FoodCart.class},version=1)
public abstract class CartDatabase extends RoomDatabase {
    public abstract CartDao cartDao();
    private static CartDatabase instance;
    public static synchronized  CartDatabase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),CartDatabase.class,"food_itemDatabase").fallbackToDestructiveMigration().build();

        }
        return instance;
    }
}
