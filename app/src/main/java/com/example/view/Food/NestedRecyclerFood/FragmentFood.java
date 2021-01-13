package com.example.view.Food.NestedRecyclerFood;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.view.R;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import ModeloGian.CommonUser;
import ModeloGian.Condition;
import ModeloGian.Discount;
import ModeloGian.Product;
import ModeloGian.ProductCategory;
import model.Restaurant;

public class FragmentFood extends Fragment {

    RecyclerView mainCategoryRecycler;
    MainRecyclerAdapter mainRecyclerAdapter;
    View view2;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_food,container,false);
        view2 = view;
        //setContentView(R.layout.);

        // here we will add some dummy data to our model class
        // here we will add data to category item model class
        // added in first category
        ((AppCompatActivity) getContext()).getSupportActionBar().setTitle("Comidas");
        loadData();
        return view;
    }

    public void loadData(){

        List<AllCategory> allCategoryList = new ArrayList<>();
        //allCategoryList.add(new AllCategory("Menu del Dia", Restaurant.restaurant.getDailyMenu()));
        //allCategoryList.add(new AllCategory("Buffet", Restaurant.restaurant.getProducts()));
        //allCategoryList.add(new AllCategory("Kiosko", Restaurant.restaurant.getProductsKiosko()));

        CommonUser user = new CommonUser("aaa","aaa","Juan", "Perez", LocalDate.of(2000,1,15), 11111111,new Condition("Celiaco",new HashSet<>()),new Discount(10));

        List<Product> consumables = ModeloGian.Restaurant.getInstance().getUserConsumableProducts(user);
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
                mainCategoryRecycler = view2.findViewById(R.id.main_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mainCategoryRecycler.setLayoutManager(layoutManager);
        mainRecyclerAdapter = new MainRecyclerAdapter(getContext(), allCategoryList);
        mainCategoryRecycler.setAdapter(mainRecyclerAdapter);

    }

    // Hi today we are gonna make a nested recyclerview
    // one is horizontal and 2nd is vertical and we will place then together.
    // before getting started make sure to subscribe and hit the bell icon to get update when i post video.
    // so 1st we will setup verticle recyclerview.
    // so now we will setup a horizontal recyclerview. which having category elements
    // now lets goto the all category Model
    // similarly u can add many types
    // so this tutorial has been completed if u have any question and doubt plz comment
    // see you in the next tutorial
}
