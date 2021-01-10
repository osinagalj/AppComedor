package com.example.view.Food.NestedRecyclerFood;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.view.Food.FoodDetail;
import com.example.view.R;

import java.util.List;

import model.Food;

public class CategoryItemRecyclerAdapter extends RecyclerView.Adapter<CategoryItemRecyclerAdapter.CategoryItemViewHolder> {

    private Context context;
    private List<Food> categoryItemList;

    public CategoryItemRecyclerAdapter(Context context, List<Food> categoryItemList) {
        this.context = context;
        this.categoryItemList = categoryItemList;
    }

    @NonNull
    @Override
    public CategoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryItemViewHolder(LayoutInflater.from(context).inflate(R.layout.row_category_food, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryItemViewHolder holder, final int position) {

        holder.itemImage.setImageResource(categoryItemList.get(position).getImagenId());
        holder.itemView.setOnClickListener(new View.OnClickListener() { //cuando se hace un click en algun item(Food)
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, FoodDetail.class);

                i.putExtra("food_picked", categoryItemList.get(position)); //put the Movie object inside Intent which was clicked
                context.startActivity(i); //start a
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryItemList.size();
    }

    public static final class CategoryItemViewHolder extends RecyclerView.ViewHolder{

        ImageView itemImage;

        public CategoryItemViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.item_image);

        }
    }

}
