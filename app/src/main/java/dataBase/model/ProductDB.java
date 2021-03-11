package dataBase.model;

import java.util.ArrayList;
import java.util.List;

public class ProductDB {

    private int id;
    private String name;
    private String description;
    private int imgId;
    private int productCategory;
    private List<Integer> conditions = new ArrayList<>();

    public ProductDB(){}

    public ProductDB(int id, String name, String description, int imgId, int productCategory, List<Integer> conditions){
        this.id = id;
        this.name = name;
        this.description = description;
        this.imgId = imgId;
        this.productCategory = productCategory;
        this.conditions = conditions;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public int getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(int productCategory) {
        this.productCategory = productCategory;
    }

    public List<Integer> getConditions() {
        return conditions;
    }

    public void setConditions(List<Integer> conditions) {
        this.conditions = conditions;
    }


}
