package com.example.view.Food.NestedRecyclerFood;

import java.util.List;
import Model.Product;

public class AllCategory {

    String categoryTitle;
    List<Product> categoryItemList;

    public AllCategory(String categoryTitle, List<Product> categoryItemList) {
        this.categoryTitle = categoryTitle;
        this.categoryItemList = categoryItemList;
    }

    public List<Product> getCategoryItemList() {
        return categoryItemList;
    }
    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryItemList(List<Product> categoryItemList) {
        this.categoryItemList = categoryItemList;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }
}
