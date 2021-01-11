package DataBase;

import java.io.Serializable;

public class Order implements Serializable {
    private String nro;
    private String price;
    private int imagenid;
    private int amount;

    private int id;

    public Order(){}
    //TODO ESTA ORDEN TENDRIA QUE SER LA ORDEN DEL MODELO
    public Order(int id,int amount, String nombre, String price, int imagenid) {
        this.id = id;
        this.nro = nombre;
        this.amount=amount;
        this.price = price;
        this.imagenid = imagenid;
    }

    public int getId(){
        return this.id;
    }

    public String getProductName() {
        return nro;
    }

    public String getProductPrice() {
        return price;
    }

    public int getProductAmount() {
        return amount;
    }

    public String getProductDescription() {
        return price;
    }

    public int getProductImg() {
        return imagenid;
    }

}
