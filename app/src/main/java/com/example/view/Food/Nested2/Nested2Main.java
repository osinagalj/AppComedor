package com.example.view.Food.Nested2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.view.R;

import java.util.ArrayList;
import java.util.List;

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
        List<CategoryItem> categoryItemList = new ArrayList<>();
        categoryItemList.add(new CategoryItem(1, R.drawable.food_alfajor_pepitos));
        categoryItemList.add(new CategoryItem(1, R.drawable.food_alfajor_pepitos));
        categoryItemList.add(new CategoryItem(1, R.drawable.food_alfajor_pepitos));
        categoryItemList.add(new CategoryItem(1, R.drawable.food_alfajor_pepitos));
        categoryItemList.add(new CategoryItem(1, R.drawable.food_alfajor_pepitos));
        categoryItemList.add(new CategoryItem(1, R.drawable.food_alfajor_pepitos));

        // added in second category
        List<CategoryItem> categoryItemList2 = new ArrayList<>();
        categoryItemList2.add(new CategoryItem(1, R.drawable.food_alfajor_pepitos));
        categoryItemList2.add(new CategoryItem(1, R.drawable.food_alfajor_pepitos));
        categoryItemList2.add(new CategoryItem(1, R.drawable.food_alfajor_pepitos));
        categoryItemList2.add(new CategoryItem(1, R.drawable.food_alfajor_pepitos));
        categoryItemList2.add(new CategoryItem(1, R.drawable.food_alfajor_pepitos));
        categoryItemList2.add(new CategoryItem(1, R.drawable.food_alfajor_pepitos));

        // added in 3rd category
        List<CategoryItem> categoryItemList3 = new ArrayList<>();
        categoryItemList3.add(new CategoryItem(1, R.drawable.food_alfajor_pepitos));
        categoryItemList3.add(new CategoryItem(1, R.drawable.food_alfajor_pepitos));
        categoryItemList3.add(new CategoryItem(1, R.drawable.food_alfajor_pepitos));
        categoryItemList3.add(new CategoryItem(1, R.drawable.food_alfajor_pepitos));
        categoryItemList3.add(new CategoryItem(1, R.drawable.food_alfajor_pepitos));
        categoryItemList3.add(new CategoryItem(1, R.drawable.food_alfajor_pepitos));

        // added in 4th category
        List<CategoryItem> categoryItemList4 = new ArrayList<>();
        categoryItemList4.add(new CategoryItem(1, R.drawable.food_alfajor_pepitos));
        categoryItemList4.add(new CategoryItem(1, R.drawable.food_alfajor_pepitos));
        categoryItemList4.add(new CategoryItem(1, R.drawable.food_alfajor_pepitos));
        categoryItemList4.add(new CategoryItem(1, R.drawable.food_alfajor_pepitos));
        categoryItemList4.add(new CategoryItem(1, R.drawable.food_alfajor_pepitos));
        categoryItemList4.add(new CategoryItem(1, R.drawable.food_alfajor_pepitos));


        // added in 5th category
        List<CategoryItem> categoryItemList5 = new ArrayList<>();
        categoryItemList5.add(new CategoryItem(1, R.drawable.food_alfajor_pepitos));
        categoryItemList5.add(new CategoryItem(1, R.drawable.food_alfajor_pepitos));
        categoryItemList5.add(new CategoryItem(1, R.drawable.food_alfajor_pepitos));
        categoryItemList5.add(new CategoryItem(1, R.drawable.food_alfajor_pepitos));
        categoryItemList5.add(new CategoryItem(1, R.drawable.food_alfajor_pepitos));
        categoryItemList5.add(new CategoryItem(1, R.drawable.food_alfajor_pepitos));
        List<AllCategory> allCategoryList = new ArrayList<>();
        allCategoryList.add(new AllCategory("Hollywood", categoryItemList));
        allCategoryList.add(new AllCategory("Best of Oscars", categoryItemList2));
        allCategoryList.add(new AllCategory("Movies Dubbed in Hindi", categoryItemList3));
        allCategoryList.add(new AllCategory("Category 4th", categoryItemList4));
        allCategoryList.add(new AllCategory("Category 5th", categoryItemList5));

        setMainCategoryRecycler(allCategoryList);

        return view;
    }

    private void setMainCategoryRecycler(List<AllCategory> allCategoryList){

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
