package com.example.mysubway.utils.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysubway.R;
import com.example.mysubway.utils.model.FoodItem;

import java.util.List;

public class foodItemAdapter extends RecyclerView.Adapter<foodItemAdapter.FoodItemViewHolder> {

    private List<FoodItem> foodItemList;
    private FoodClickedListener foodClickedListener;
    public foodItemAdapter(FoodClickedListener foodClickedListener){
            this.foodClickedListener=foodClickedListener;
    }
    public void setFoodItemList(List<FoodItem> foodItemList){
        this.foodItemList=foodItemList;
    }

    @NonNull
    @Override
    public FoodItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.eachfooditem, parent, false);
        return new FoodItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodItemViewHolder holder, int position) {
        FoodItem foodItem = foodItemList.get(position);
        holder.foodNameTv.setText(foodItem.getFood_item_name());
        holder.foodPriceTv.setText(String.valueOf(foodItem.getFood_item_price()));
        holder.foodImageView.setImageResource(foodItem.getFood_item_image());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foodClickedListener.onCardClicked(foodItem);
            }
        });
        holder.addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foodClickedListener.onAddToCartBtnClicked(foodItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(foodItemList==null){
            return 0;
        }
        else{
            return foodItemList.size();
        }
    }

    public class FoodItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView foodImageView, addToCartBtn;
        private TextView foodNameTv, foodPriceTv;
        private CardView cardView;

        public FoodItemViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.eachFoodCardView);
            addToCartBtn = itemView.findViewById(R.id.eachFoodAddToCartBtn);
            foodNameTv = itemView.findViewById(R.id.eachfoodName);
            foodImageView = itemView.findViewById(R.id.eachFoodIv);
            foodPriceTv = itemView.findViewById(R.id.eachFoodPriceTv);
        }
    }
    public interface FoodClickedListener{
        void onCardClicked(FoodItem foodItem);
        void onAddToCartBtnClicked(FoodItem foodItem);
    }
}
