package com.example.view.MyOrders.Fragment.Pendientes;

import java.io.Serializable;

public class Order implements Serializable {
    private String nombre;
    private String price;
    private int imagenid;

    public Order(){}

    public Order(String nombre, String price, int imagenid) {
        this.nombre = nombre;
        this.price = price;
        this.imagenid = imagenid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechanacimiento() {
        return price;
    }

    public void setFechanacimiento(String price) {
        this.price = price;
    }

    public int getImagenid() {
        return imagenid;
    }

    public void setImagenid(int imagenid) {
        this.imagenid = imagenid;
    }
}
