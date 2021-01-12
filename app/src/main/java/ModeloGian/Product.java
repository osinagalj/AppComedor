package ModeloGian;

import java.io.Serializable;
import java.util.List;

public abstract class Product implements Serializable {
    private int id;
    private String name;
    private String description;
    private int imgId;

    protected Product(){}

    protected Product(int id, String name, String description,int imgId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imgId = imgId;
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

    public abstract List<String> getIngredients();
    public abstract List<Product> getProducts();
    public abstract float getPrice();
    public abstract int getStock();
    public abstract int getDailyLimit();
}
