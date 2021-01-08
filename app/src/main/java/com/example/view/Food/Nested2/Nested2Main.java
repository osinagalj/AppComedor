package com.example.view.Food.Nested2;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import model.Food;

public class Nested2Main extends Fragment {

    RecyclerView mainCategoryRecycler;
    MainRecyclerAdapter mainRecyclerAdapter;
    View view2;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.activity_main67,container,false);
        view2 = view;
        //setContentView(R.layout.);

        // here we will add some dummy data to our model class
        // here we will add data to category item model class
        // added in first category
        ((AppCompatActivity) getContext()).getSupportActionBar().setTitle("Tus comidas pa");
        loadData();
        return view;
    }

    public void loadData(){

        List<Food> categoryItemList = new ArrayList<>();
        categoryItemList.add(new Food(0001,"Alfajor Pepitos",6, 20.2f, new Vector<String>(), R.drawable.food_milanesas_con_fritas));

        // added in second category
        List<Food> categoryItemList2 = new ArrayList<>();
        categoryItemList2.add(new Food(0001,"Alfajor Pepitos",6, 20.2f, new Vector<String>(), R.drawable.food_milanesas_con_fritas));
        categoryItemList2.add(new Food(0001,"Alfajor Pepitos",6, 20.2f, new Vector<String>(), R.drawable.food_milanesas_con_fritas));
        categoryItemList2.add(new Food(0001,"Alfajor Pepitos",6, 20.2f, new Vector<String>(), R.drawable.food_milanesas_con_fritas));
        categoryItemList2.add(new Food(0001,"Alfajor Pepitos",6, 20.2f, new Vector<String>(), R.drawable.food_milanesas_con_fritas));
        categoryItemList2.add(new Food(0001,"Alfajor Pepitos",6, 20.2f, new Vector<String>(), R.drawable.food_milanesas_con_fritas));


        // added in 3rd category
        List<Food> categoryItemList3 = new ArrayList<>();
        categoryItemList3.add(new Food(0001,"Alfajor Pepitos",6, 20.2f, new Vector<String>(), R.drawable.food_milanesas_con_fritas));


        List<AllCategory> allCategoryList = new ArrayList<>();
        allCategoryList.add(new AllCategory("Menu del Dia", categoryItemList));
        allCategoryList.add(new AllCategory("Buffet", categoryItemList2));
        allCategoryList.add(new AllCategory("Kiosko", categoryItemList3));


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
