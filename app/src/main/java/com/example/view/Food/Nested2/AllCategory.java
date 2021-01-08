package com.example.view.Food.Nested2;

import java.util.List;

import model.Food;

public class AllCategory {

    String categoryTitle;
    List<Food> categoryItemList;

    public AllCategory(String categoryTitle, List<Food> categoryItemList) {
        this.categoryTitle = categoryTitle;
        this.categoryItemList = categoryItemList;
    }

    public List<Food> getCategoryItemList() {
        return categoryItemList;
    }

    public void setCategoryItemList(List<Food> categoryItemList) {
        this.categoryItemList = categoryItemList;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }
}
