package com.example.view.Food.NestedRecyclerFood;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.view.DataHolder;
import com.example.view.Food.Carrito.Carrito;
import com.example.view.R;

import java.util.ArrayList;
import java.util.List;

import DAO.ProductDAO;
import Model.Product;
import Model.ProductCategory;

public class FragmentFood extends Fragment {

    RecyclerView mainCategoryRecycler;
    MainRecyclerAdapter mainRecyclerAdapter;
    View view2;
    LinearLayout bottom;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_food, container, false);
        bottom = view.findViewById(R.id.fragment_food_layout_make_order);
        view2 = view;
        mainCategoryRecycler = view2.findViewById(R.id.main_recycler);

        ((AppCompatActivity) getContext()).getSupportActionBar().setTitle("Comidas");

        Button button_finish_order = view.findViewById(R.id.fragment_food_button_make_order);
        button_finish_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCarrito(view);
            }
        });

        showCarrito();
        loadData();
        return view;
    }

    @Override
    public void onResume() { //Necesario para que se actualice la vista, y aparezca el boton de carrio si es neesario
        super.onResume();
        // Check should we need to refresh the fragment
        showCarrito();

    }

    private void showCarrito() {
        if (DataHolder.getLoggedUser().cartIsEmpty()) {
            bottom.setVisibility(View.GONE);
        } else {
            bottom.setVisibility(View.VISIBLE);
        }
    }

    private void openCarrito(View view) {
        Intent intent = new Intent(getContext(), Carrito.class);
        startActivity(intent);

        //FragmantClass rSum = new FragmantClass(); getSupportFragmentManager().beginTransaction().remove(rSum).commit();
    }

    public void loadData() {

        List<AllCategory> allCategoryList = new ArrayList<>();

        List<Product> consumables = ProductDAO.avalaibleProducts(DataHolder.getLoggedUser());
        for (ProductCategory category : ProductCategory.values()){
            List<Product> catList = new ArrayList<>();
            for (Product product : consumables)
                if (product.getCategory().equals(category))
                    catList.add(product);
            allCategoryList.add(new AllCategory(category.toString(), catList));
        }

        setMainCategoryRecycler(allCategoryList);
    }


    private void setMainCategoryRecycler(List<AllCategory> allCategoryList){

        final List<AllCategory> a = allCategoryList;

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mainCategoryRecycler.setLayoutManager(layoutManager);
        mainRecyclerAdapter = new MainRecyclerAdapter(getContext(), allCategoryList);
        mainCategoryRecycler.setAdapter(mainRecyclerAdapter);

    }

}