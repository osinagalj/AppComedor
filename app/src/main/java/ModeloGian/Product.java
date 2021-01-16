package ModeloGian;

import java.io.Serializable;
import java.util.List;

public abstract class Product implements Serializable {
    private int id;
    private String name;
    private String description;
    private int imgId;
    private ProductCategory category;

    protected Product(){}

    protected Product(int id, String name, String description, int imgId, ProductCategory category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imgId = imgId;
        this.category = category;
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

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imgId=" + imgId +
                ", category=" + category +
                '}';
    }

    public abstract List<String> getIngredients();
    public abstract List<Product> getProducts();
    public abstract float getPrice();
    public abstract int getStock();
    public abstract void addStock(int stock);
    public abstract int getDailyLimit();
}
