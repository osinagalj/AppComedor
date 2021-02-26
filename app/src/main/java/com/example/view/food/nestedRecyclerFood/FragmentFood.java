package com.example.view.food.nestedRecyclerFood;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.view.BackEnd;
import com.example.view.databinding.FragmentFoodBinding;
import com.example.view.food.cart.ActivityCart;

import java.util.ArrayList;
import java.util.List;

import dataBase.Restaurant;
import model.Product;

public class FragmentFood extends Fragment {

    MainRecyclerAdapter mainRecyclerAdapter;
    private FragmentFoodBinding binding;

    private List<Product> foods1;
    FoodViewModel viewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((AppCompatActivity) getContext()).getSupportActionBar().setTitle("Comidas");
        super.onCreate(savedInstanceState);
        binding = FragmentFoodBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        viewModel = ViewModelProviders.of(this).get(FoodViewModel.class); //ViewModel para la DB



        viewModel.setDailyMenuFoods();
        viewModel.setFoods();

        foods1 = new ArrayList<>();

        viewModel.list_foods.observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> list_foods) {
                foods1.clear();
                foods1 = new ArrayList<>(list_foods);
                //foods1.addAll(list_foods); se agregan repetidos si hago esto
                loadData();
            }
        });

        setUpButtons();

        return view;
    }


    public void loadData() {

        List<AllCategory> allCategoryList = new ArrayList<>();

        for (int category : Restaurant.getInstance().productsCategories){
            List<Product> catList = new ArrayList<>();

            for (Product product : foods1)
                if (product.getCategory() == category)
                    catList.add(product);

            if(!catList.isEmpty())
                allCategoryList.add(new AllCategory(getCategory(category), catList));
        }

        setMainCategoryRecycler(allCategoryList);
    }


    private void setUpButtons(){
        binding.openCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCarrito();
            }
        });
    }

    @Override
    public void onResume() { //Necesario para que se actualice la vista, y aparezca el boton de carrio si es neesario
        super.onResume();
        // Check should we need to refresh the fragment
        showCarrito();
    }

    private void showCarrito() {
        if (BackEnd.getProductsOrder().isEmpty()) {
            binding.layoutCart.setVisibility(View.GONE);
        } else {
            binding.layoutCart.setVisibility(View.VISIBLE);
        }
    }

    private void openCarrito() {
        Intent intent = new Intent(getContext(), ActivityCart.class);
        startActivity(intent);
    }

    private String getCategory(int category){
        switch (category){
            case 1:
                return "Menu del Dia";
            case 2:
                return "Buffet";
            default:
                return "Kiosko";

        }
    }

    private void setMainCategoryRecycler(List<AllCategory> allCategoryList){

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.mainRecycler.setLayoutManager(layoutManager);
        mainRecyclerAdapter = new MainRecyclerAdapter(getContext(), allCategoryList);
        binding.mainRecycler.setAdapter(mainRecyclerAdapter);

    }

}