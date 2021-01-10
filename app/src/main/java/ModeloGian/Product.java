package ModeloGian;

import java.util.List;

public abstract class Product {
    private int id;
    private String name;
    private String description;
    private int imgId; //TODO la imagen de cada producto/combo

    protected Product(){}

    protected Product(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public abstract List<String> getIngredients();
    public abstract List<Product> getProducts();
    public abstract float getPrice();
    public abstract int getStock();
    public abstract int getDailyLimit();
}
