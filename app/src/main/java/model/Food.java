package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

public class Food implements Serializable {
    private int barcode;
    private String name;
    private int stock;
    private float price;
    private Vector<String> ingredients;

    private int imagenId;

    public Food(int barcode, String name, int stock, float price, Vector<String> ingredients,int imagenId){
        this.barcode = barcode;
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.ingredients = ingredients;
        this.imagenId = imagenId;
    }

    public int getImagenId() {
        return imagenId;
    }

    public int getBarcode() {
        return barcode;
    }

    public void setBarcode(int barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Vector<String> getIngredients() {

        Vector<String> r_ingredients = new Vector<String>();
        for(int i = 0; i < this.ingredients.size(); i++){
            r_ingredients.add(this.ingredients.elementAt(i));
        }
        return r_ingredients;
    }


}
