package dataBase.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dataBase.Restaurant;
import model.Combo;
import model.DiscountCalculator;
import model.DiscountMax;
import model.FixedDiscount;
import model.Product;

public class ComboDB {

    int id_discount = 0;
    int imgId;
    int id;
    String description;
    int productCategory;
    String name;
    ArrayList<Integer> conditions;
    ArrayList<Integer> products;
    HashMap<String,Object> discount_attributes;

    public ComboDB(){}
    public ComboDB(Combo combo){

        conditions = new ArrayList<>(combo.getConditions());
        imgId = combo.getImgId();
        id = combo.getId();
        description = combo.getDescription();
        productCategory = combo.getProductCategory();
        name = combo.getName();


        discount_attributes = new HashMap<>();
        products = new ArrayList<>();
        for(Product p: combo.getComboItems())
            products.add(p.getId());

        try{
            DiscountMax c = (DiscountMax) combo.getDiscount();
            id_discount = 1;
            discount_attributes.put("limit",c.getLimit());
            discount_attributes.put("discount",c.getDiscount());
        }catch (Exception ios){}

        try{
            FixedDiscount c = (FixedDiscount) combo.getDiscount();
            discount_attributes.put("percentage",c.getDiscountPercentage());
            id_discount = 2;
        }catch (Exception ios){}
    }


    public Combo convertToModel(){
        DiscountCalculator discountCalculator;
        List<Product> items = new ArrayList<>();

        for(Integer id: products){
            items.add(Restaurant.getInstance().getProduct(id));
        }

        switch (id_discount){
            case 1:
                discountCalculator = new DiscountMax(
                        Float.parseFloat(discount_attributes.get("limit").toString()),
                        Float.parseFloat(discount_attributes.get("discount").toString())
                );
                break;
            case 2:
                discountCalculator = new FixedDiscount(
                        Float.parseFloat(discount_attributes.get("percentage").toString())
                );
                break;
            default:
                discountCalculator = new FixedDiscount(
                        0
                );
        }

        return new Combo(id,name,description,imgId,productCategory,items,discountCalculator);

    }

    public int getId_discount() {
        return id_discount;
    }

    public void setId_discount(int id_discount) {
        this.id_discount = id_discount;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(int productCategory) {
        this.productCategory = productCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Integer> getConditions() {
        return conditions;
    }

    public void setConditions(ArrayList<Integer> conditions) {
        this.conditions = conditions;
    }

    public ArrayList<Integer> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Integer> products) {
        this.products = products;
    }

    public HashMap<String, Object> getDiscount_attributes() {
        return discount_attributes;
    }

    public void setDiscount_attributes(HashMap<String, Object> discount_attributes) {
        this.discount_attributes = discount_attributes;
    }

}
