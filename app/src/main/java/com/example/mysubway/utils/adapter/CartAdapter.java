package com.example.mysubway.utils.adapter;

import android.icu.text.DateIntervalFormat;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysubway.R;
import com.example.mysubway.utils.model.FoodCart;
import com.example.mysubway.views.CartActivity;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewholder> {


    private CartClickedListeners cartClickedListeners;
    private List<FoodCart> foodCartList;

    public CartAdapter(CartClickedListeners cartClickedListeners){
        this.cartClickedListeners = cartClickedListeners;
    }

    public void setFoodCartList(List<FoodCart> foodCartList){
        this.foodCartList = foodCartList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_cart_item,parent,false);
        return new CartViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewholder holder, int position) {

        FoodCart foodCart = foodCartList.get(position);
        holder.foodImageView.setImageResource(foodCart.getFood_item_image());
        holder.foodNameTv.setText(foodCart.getFood_item_name());
        holder.foodQuantity.setText(foodCart.getQuantity()+"");
        holder.foodPriceTv.setText(foodCart.getTotalItemPrice()+"");

        holder.deleteFoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartClickedListeners.onDeleteClicked(foodCart);
            }
        });
        holder.addQuantityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartClickedListeners.onPlusClicked(foodCart);
            }
        });
        holder.minusQuantityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartClickedListeners.onMinusClicked(foodCart);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(foodCartList==null){
            return 0;
        }
        else{
            return foodCartList.size();
        }
    }

    public class CartViewholder extends RecyclerView.ViewHolder{

        private TextView foodNameTv, foodPriceTv,foodQuantity;
        private ImageView deleteFoodBtn;
        private ImageView foodImageView;
        private ImageButton addQuantityBtn, minusQuantityBtn;


        public CartViewholder(@NonNull View itemView) {
            super(itemView);

            foodNameTv = itemView.findViewById(R.id.eachCartItemName);
            foodPriceTv = itemView.findViewById(R.id.eachCartItemPriceTv);
            deleteFoodBtn = itemView.findViewById(R.id.eachCartItemDeleteBtn);
            foodImageView = itemView.findViewById(R.id.eachCartItemIV);
            foodQuantity = itemView.findViewById(R.id.eachCartItemQuantityTV);
            addQuantityBtn = itemView.findViewById(R.id.eachCartItemAddQuantityBtn);
            minusQuantityBtn = itemView.findViewById(R.id.eachCartItemMinusQuantityBtn);
        }
    }

    public interface CartClickedListeners{

        void onDeleteClicked(FoodCart foodCart);
        void onPlusClicked(FoodCart foodCart);
        void onMinusClicked(FoodCart foodCart);

    }
}
